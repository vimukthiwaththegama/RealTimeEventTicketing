package com.cw_oop.RealTimeEventTicketing.backend.controller;

import com.cw_oop.RealTimeEventTicketing.backend.entity.Config;
import com.cw_oop.RealTimeEventTicketing.backend.service.ConfigService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/config")
public class ConfigurationController {

    @Autowired
    private ConfigService configService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addConfig")
    public void setConfiguration(@RequestBody Config config) {
        config.setSessionId(++ConfigService.sessionId);
        configService.saveToFile(config);
        System.out.println("Session Id"+ConfigService.sessionId+" added");
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAllConfigs")
    public List<Config> getAllConfigurations() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("config.json");
        List<Config> configList = new ArrayList<>();

        if (file.exists()) {
            try {
                configList = objectMapper.readValue(file, new TypeReference<List<Config>>() {});
            } catch (IOException e) {
                System.out.println("getAll:failed: " + e.getMessage());
            }
        }

        return configList;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/clearConfigs")
    public static void deleteAllConfigurations() {
        File file = new File("config.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter writer = new FileWriter(file)) {
            objectMapper.writeValue(writer, new Object[]{});
            System.out.println("deleted:All configurations");
        } catch (IOException e) {
            System.out.println("failed");
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public Object getConfigById(@PathVariable("id") Integer sessionId) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("config.json");
        List<Config> configList = new ArrayList<>();

        if (file.exists()) {
            try {
                configList = objectMapper.readValue(file, new TypeReference<List<Config>>() {});
            } catch (IOException e) {
                return "An error occurred while reading the configuration file: " + e.getMessage();
            }
        }
        Optional<Config> configOptional = configList.stream()
                .filter(config -> config.getSessionId().equals(sessionId))
                .findFirst();

        return configOptional;
    }

}
