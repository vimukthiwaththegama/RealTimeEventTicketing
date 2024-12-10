package com.cw_oop.RealTimeEventTicketing.backend.service;

import com.cw_oop.RealTimeEventTicketing.backend.entity.CustomerInfo;
import com.cw_oop.RealTimeEventTicketing.backend.entity.VendorInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    public static Integer customerId=0;
    public void saveToFile(CustomerInfo customerInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("customer.json");
        List<CustomerInfo> configList = new ArrayList<>();
        if (file.exists()) {
            try {
                configList = objectMapper.readValue(file, new TypeReference<List<CustomerInfo>>() {});
            } catch (IOException e) {
                System.out.println("An error occurred while reading the customer file: " + e.getMessage());
            }
        }
        configList.add(customerInfo);

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, configList);
            System.out.println("Customer saved to customer.json");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the customer: " + e.getMessage());
        }
    }
}
