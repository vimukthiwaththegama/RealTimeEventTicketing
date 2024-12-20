package com.cw_oop.RealTimeEventTicketing.backend.controller;

import com.cw_oop.RealTimeEventTicketing.backend.entity.Config;
import com.cw_oop.RealTimeEventTicketing.backend.service.CustomerService;
import com.cw_oop.RealTimeEventTicketing.backend.service.LogCollector;
import com.cw_oop.RealTimeEventTicketing.backend.service.VendorService;
import com.cw_oop.RealTimeEventTicketing.cli.Configuration;
import com.cw_oop.RealTimeEventTicketing.cli.Customer;
import com.cw_oop.RealTimeEventTicketing.cli.TicketPool;
import com.cw_oop.RealTimeEventTicketing.cli.Vendor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/execute")
public class ExecuteController {
    @Autowired
    private LogCollector logCollector;
    public static Boolean isStopped=true;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{id}")
    public void start(@PathVariable("id") Integer sessionId) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("config.json");
        List<Config> configList = new ArrayList<>();

        if (file.exists()) {
            try {
                configList = objectMapper.readValue(file, new TypeReference<List<Config>>() {});
            } catch (IOException e) {
                System.out.println("Error reading config.json");
            }
        }
        Optional<Config> configOptional = configList.stream()
                .filter(config -> config.getSessionId().equals(sessionId))
                .findFirst();

        if (configOptional.isPresent()) {
            Config config = configOptional.get();
            Configuration configuration = new Configuration(
                    config.getTotalTickets(),
                    config.getTicketReleaseRate(),
                    config.getTicketsRetrievalRate(),
                    config.getMaxPoolCapacity()

            );
            TicketPool ticketPool = new TicketPool(configuration.getTotalNumberOfTickets(),configuration.getMaxTicketCapacity());


            List<Thread> vendorThreads = new ArrayList<>();
            List<Thread> customerThreads = new ArrayList<>();

            for (int i = 1; i <= VendorService.vendorId; i++) {
                Vendor vendor = new Vendor(configuration, ticketPool);
                vendor.setVendorId(i);
                Thread thread = new Thread(vendor);
                vendorThreads.add(thread);
                thread.start();
            }
            for (int i = 1; i <= CustomerService.customerId; i++) {
                Customer customer = new Customer(configuration, ticketPool);
                customer.setCustomerId(i);
                Thread thread = new Thread(customer);
                customerThreads.add(thread);
                thread.start();
            }

            for (Thread thread : vendorThreads) {
                if(isStopped){
                    return;
                }
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("Vendor thread interrupted: " + e.getMessage());
                }
            }

            for (Thread thread : customerThreads) {
                if(isStopped){
                    return;
                }
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("Customer thread interrupted: " + e.getMessage());
                }
            }

        } else {
            System.out.println("Not found!");

        }

        System.out.println("Buying and Selling part has done!");

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/stop")
    public Boolean stop() {
        ConfigurationController.deleteAllConfigurations();
        VendorController.clearVendors();
        CustomerController.clearCustomers();

        return isStopped=false;
    }

    @GetMapping("/logs")
    public List<String> getRuntimeLogs() {
        return logCollector.getLogs();
    }
    }

