package com.hryg.demos.springcloudstreamdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreetingsServiceTest {
    @Autowired
    private GreetingsService service;

    @Test
    public void sendGreeting() {
        for (int i = 0; i < 3; i++) {
            Greetings greetings = new Greetings(System.currentTimeMillis(), "我是消息 " + i);
            service.sendGreeting(greetings);
        }
    }
}