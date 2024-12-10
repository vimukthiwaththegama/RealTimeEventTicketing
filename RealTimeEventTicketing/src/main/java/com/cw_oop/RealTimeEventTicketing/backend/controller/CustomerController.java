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

    @PostMapping("/addCustomer")
    public void setCustomer(@RequestBody CustomerInfo customerInfo) {
        customerInfo.setCustomerId(++CustomerService.customerId);
        customerService.saveToFile(customerInfo);

    }
    @DeleteMapping("/clearCustomers")
    public String  clearCustomers() {
        File file = new File("customer.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, new Object[]{});
            return "deleted:All customers";
        } catch (IOException e) {
            return "deleteAll:failed: " + e.getMessage();
        }
    }
}

