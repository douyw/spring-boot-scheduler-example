package com.example.schedulerdemo.service;

import com.example.schedulerdemo.domain.App;
import com.example.schedulerdemo.repository.AppRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    private final AppRepository appRepository;

    public AppService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public void saveApp(App app) {
        appRepository.save(app);
    }

    @Cacheable("apps")
    public App getApp(String appId) {
        return appRepository.findOne(appId);
    }

    public List<App> getApps() {
        return appRepository.findAll();
    }
}
