package com.javaPractice.practice.sample01;

import com.javaPractice.practice.util.Sample01;

public class MethodSample01 {

    public static void main(String[] args) {
        int a = 10;
        Sample01 s01 = new Sample01();
        String r1 = s01.isOddNumber(a);
        System.out.println(a + "是：" + r1);
    }
}