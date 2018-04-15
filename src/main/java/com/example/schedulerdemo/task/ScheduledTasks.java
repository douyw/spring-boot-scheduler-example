package com.example.schedulerdemo.task;

import com.example.schedulerdemo.domain.App;
import com.example.schedulerdemo.service.AppService;
import com.example.schedulerdemo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final AppService appService;

    private final MessageService messageService;

    public ScheduledTasks(AppService appService, MessageService messageService) {
        this.appService = appService;
        this.messageService = messageService;
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        try {
            messageService.sendMessage(dateFormat.format(new Date()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        testAppInfo();
    }

    private void testAppInfo() {
        App app = new App();
        app.setId("app122");
        app.setName("AppName");
        appService.saveApp(app);
        List<App> apps = appService.getApps();
        log.info("apps size: {}", apps.size());
    }
}
