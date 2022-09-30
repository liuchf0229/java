package com.javaPractice.demo.test;

public class Test2 {
    public void pupAge(){
        int age = 1;
        age = age + 7;
        System.out.println("小狗的年龄是 : " + age);
    }

    public static void main(String[] args){
        Test2 test = new Test2();
        test.pupAge();
    }
}

