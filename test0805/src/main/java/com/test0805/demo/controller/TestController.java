package com.test0805.demo.controller;

import com.test0805.demo.domain.Test;
import com.test0805.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }
}
