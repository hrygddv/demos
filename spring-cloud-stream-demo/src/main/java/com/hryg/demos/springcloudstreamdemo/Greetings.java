package com.hryg.demos.springcloudstreamdemo;

/**
 * @author hryg
 * Created on 2018-12-04
 */
public class Greetings {
    private long timestamp;
    private String message;

    public Greetings() {
    }

    public Greetings(long timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Greetings{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
