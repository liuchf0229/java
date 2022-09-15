package com.liuchf.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping(value = "/hello2")
    public Animal getName() {
        return new Animal();
    }
}
