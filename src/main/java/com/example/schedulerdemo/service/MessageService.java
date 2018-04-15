package com.example.schedulerdemo.service;

import com.example.schedulerdemo.config.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class MessageService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final StringRedisTemplate template;

    private final CountDownLatch latch;

    public MessageService(StringRedisTemplate template, CountDownLatch latch) {
        this.template = template;
        this.latch = latch;
    }

    public void sendMessage(String message) throws Throwable {

        LOGGER.info("Sending message...");
        template.convertAndSend(Const.TOPIC_DEMO, message);

        LOGGER.info("awaiting...");
        latch.await();
        LOGGER.info("awaiting done.");
    }
}
