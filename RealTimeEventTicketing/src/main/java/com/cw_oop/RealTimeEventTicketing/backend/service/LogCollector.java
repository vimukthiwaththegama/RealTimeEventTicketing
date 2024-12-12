package com.cw_oop.RealTimeEventTicketing.backend.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class LogCollector {
    public static List<String> logs = Collections.synchronizedList(new ArrayList<>());

    public static void addLog(String log) {
        logs.add(log);
    }

    public List<String> getLogs() {
        return new ArrayList<>(this.logs);
    }
}
