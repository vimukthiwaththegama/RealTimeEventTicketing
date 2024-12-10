package com.cw_oop.RealTimeEventTicketing.backend.service;

import com.cw_oop.RealTimeEventTicketing.backend.entity.Config;
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
public class VendorService {
    public static Integer vendorId=0;
    public void saveToFile(VendorInfo vendorInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("vendor.json");
        List<VendorInfo> configList = new ArrayList<>();
        if (file.exists()) {
            try {
                configList = objectMapper.readValue(file, new TypeReference<List<VendorInfo>>() {});
            } catch (IOException e) {
                System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            }
        }
        configList.add(vendorInfo);

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, configList);
            System.out.println("Vendor saved to vendor.json");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the vendor: " + e.getMessage());
        }
    }

}
