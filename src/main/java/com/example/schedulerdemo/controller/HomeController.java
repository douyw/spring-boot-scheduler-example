package com.example.schedulerdemo.controller;

import com.example.schedulerdemo.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

    private final MessageService messageService;

    public HomeController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String hello() {
        return "hi, spring boot!";
    }

    @PostMapping("/send/{message}")
    public ResponseEntity<String > chat(@PathVariable("message") String message) throws Throwable {
        messageService.sendMessage(message);
        return ResponseEntity.ok("ok");
    }
}
