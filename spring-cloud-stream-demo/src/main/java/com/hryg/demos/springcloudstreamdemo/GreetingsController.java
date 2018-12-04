package com.hryg.demos.springcloudstreamdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hryg
 * Created on 2018-12-04
 */
@RestController
public class GreetingsController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final GreetingsService greetingsService;

    public GreetingsController(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @GetMapping("/greetings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greetings(@RequestParam("message") String message) {
        LOG.info("收到请求 ===》{}", message);
        Greetings greetings = new Greetings(System.currentTimeMillis(), message);
        greetingsService.sendGreeting(greetings);
    }
}
