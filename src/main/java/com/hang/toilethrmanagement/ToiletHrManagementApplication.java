package com.hang.toilethrmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToiletHrManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToiletHrManagementApplication.class, args);
    }
}
