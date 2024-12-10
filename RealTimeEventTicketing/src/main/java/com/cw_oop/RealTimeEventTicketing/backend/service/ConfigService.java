package com.cw_oop.RealTimeEventTicketing.backend.service;

import com.cw_oop.RealTimeEventTicketing.backend.entity.Config;
//import com.google.gson.Gson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigService {
    public static Integer sessionId=0;
    public static List<Thread> vendorThreads = new ArrayList<>();
    public static List<Thread> customerThreads = new ArrayList<>();

    public void saveToFile(Config config) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("config.json");
        List<Config> configList = new ArrayList<>();
        if (file.exists()) {
            try {
                configList = objectMapper.readValue(file, new TypeReference<List<Config>>() {});
            } catch (IOException e) {
                System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            }
        }
        configList.add(config);

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, configList);
            System.out.println("Configuration saved to config.json");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the configuration: " + e.getMessage());
        }
    }


}
