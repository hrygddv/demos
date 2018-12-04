package com.hryg.demos.springcloudstreamdemo;

import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author hryg
 * Created on 2018-12-04
 */
@EnableBinding(GreetingsStreams.class)
public class StreamConfig {
}
