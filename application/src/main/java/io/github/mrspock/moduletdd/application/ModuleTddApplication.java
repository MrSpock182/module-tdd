package io.github.mrspock.moduletdd.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "io.github.mrspock.moduletdd")
public class ModuleTddApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleTddApplication.class, args);
    }
}