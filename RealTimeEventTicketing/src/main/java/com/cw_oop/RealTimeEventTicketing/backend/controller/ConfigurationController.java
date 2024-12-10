package com.cw_oop.RealTimeEventTicketing.backend.controller;

import com.cw_oop.RealTimeEventTicketing.backend.entity.Config;
import com.cw_oop.RealTimeEventTicketing.backend.service.ConfigService;
import com.cw_oop.RealTimeEventTicketing.cli.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/config")
public class ConfigurationController {

    @Autowired
    private ConfigService configService;

    @PostMapping("/add")
    public void setConfiguration(@RequestBody Config config) {
        configService.saveToFile(config);
        Configuration configuration = new Configuration(config.getTotalTickets(),
                                                        config.getTicketReleaseRate(),
                                                        config.getTicketsRetrievalRate(),
                                                        config.getMaxPoolCapacity());
    }
//    @GetMapping("/get-config")
//    public Config getConfiguration() {
//
//    }

}
