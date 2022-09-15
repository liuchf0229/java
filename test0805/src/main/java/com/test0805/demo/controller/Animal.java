package com.test0805.demo.controller;

public class Animal {

    private String name;
    private int age;

//    构造函数
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

// get set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
