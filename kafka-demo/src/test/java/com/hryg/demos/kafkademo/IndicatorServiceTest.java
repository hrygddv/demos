package com.hryg.demos.kafkademo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndicatorServiceTest {
    @Autowired
    private IndicatorService service;

    @Test
    public void sendMessage() {
        for (int i = 0; i < 3; i++) {
            service.sendMessage("test1", "我就是简单测试一下");
        }
    }
}