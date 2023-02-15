package com.chinaunicom.sandboxie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SandboxieApplication {

    public static void main(String[] args) {

        System.out.println("进入application!");
        SpringApplication.run(SandboxieApplication.class, args);
    }

}
