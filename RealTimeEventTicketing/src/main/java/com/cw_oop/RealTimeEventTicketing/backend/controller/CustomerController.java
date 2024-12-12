package com.cw_oop.RealTimeEventTicketing.backend.controller;

import com.cw_oop.RealTimeEventTicketing.backend.entity.CustomerInfo;
import com.cw_oop.RealTimeEventTicketing.backend.entity.VendorInfo;
import com.cw_oop.RealTimeEventTicketing.backend.service.CustomerService;
import com.cw_oop.RealTimeEventTicketing.backend.service.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addCustomer")
    public void setCustomer(@RequestBody CustomerInfo customerInfo) {
        customerInfo.setCustomerId(++CustomerService.customerId);
        System.out.println(customerInfo.getTotalTicketsBuy());
        customerService.saveToFile(customerInfo);

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/clearCustomers")
    public static void  clearCustomers() {
        File file = new File("customer.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, new Object[]{});
            System.out.println("deleted:All customers");
        } catch (IOException e) {
            System.out.println("deleteAll:failed:");
        }
    }
}

