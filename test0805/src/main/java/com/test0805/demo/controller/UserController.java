package com.test0805.demo.controller;

import com.test0805.demo.entity.User;
import com.test0805.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test1")
    public String getName1() {
        return "test1";
    }

//    返回格式是Animal的json对象，new了一个Animal构造函数
    @GetMapping("/test2")
    public Animal getName2() {
        return new Animal("dog",1);
    }

    @GetMapping("/test3")
    public List<User> getName3() {
        return userService.findAll();
    }

}
