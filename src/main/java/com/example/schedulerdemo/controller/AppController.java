package com.example.schedulerdemo.controller;

import com.example.schedulerdemo.domain.App;
import com.example.schedulerdemo.service.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/apps")
    public ResponseEntity<List<App>> getApps() {
        return ResponseEntity.ok(appService.getApps());
    }

    @PostMapping("/apps")
    public ResponseEntity<?> createApp(@RequestBody App app) {
        appService.saveApp(app);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/apps/{app_id}")
    public ResponseEntity<App> getApp(@PathVariable("app_id") String appId) {
        App app = appService.getApp(appId);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(app);
    }
}
