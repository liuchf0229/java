package com.javaPractice.demo.controller;

import com.javaPractice.demo.domain.Test;
import com.javaPractice.demo.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/list")
    public List<Test> list() {
        return testService.list();
    }
}
