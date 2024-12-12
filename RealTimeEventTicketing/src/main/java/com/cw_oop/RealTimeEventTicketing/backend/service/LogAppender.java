package com.cw_oop.RealTimeEventTicketing.backend.service;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogAppender extends AppenderBase<ILoggingEvent> {

    @Autowired
    private LogCollector logCollector;

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (logCollector != null) {
            logCollector.addLog(eventObject.getFormattedMessage());
        }
    }
}
