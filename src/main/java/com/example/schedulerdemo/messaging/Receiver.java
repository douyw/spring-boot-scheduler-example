package com.example.schedulerdemo.messaging;

import com.example.schedulerdemo.domain.App;
import com.example.schedulerdemo.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch;

    private final AppService appService;

    @Autowired
    public Receiver(CountDownLatch latch, AppService appService) {
        this.latch = latch;
        this.appService = appService;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");

        List<App> apps = appService.getApps();
        LOGGER.info("> receive message: app size = {}", apps.size());

        String app_id = "app122";
        App app = appService.getApp(app_id);
        if (app != null) {
            LOGGER.info("app found. app id: {} - {}", app_id, app.getName());
        } else {
            LOGGER.warn("target app id not found. app id: {}", app_id);
        }

        latch.countDown();
    }
}
