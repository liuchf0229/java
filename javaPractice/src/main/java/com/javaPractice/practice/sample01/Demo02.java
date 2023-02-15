package com.javaPractice.practice.sample01;

import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        int classNum = 3; // 班级数目
        int stuNum = 4; // 学生数目
        double sum = 0; // 成绩总和
        double avg = 0; // 平均分
        Scanner input = new Scanner(System.in);
        for (int i = 1; i <= classNum; i++) { // 外层循环控制班级数
            sum = 0; // 成绩总和归0
            System.out.println("***请输入第" + i + "个班级的成绩***");
            for (int j = 1; j <= stuNum; j++) { // 内层循环控制每个班级学员数
                System.out.print("第" + j + "个学员的成绩：");
                int score = input.nextInt(); // 获取输入的成绩
                sum = sum + score; // 成绩累加求和
            }
            avg = sum / stuNum; // 计算平均分
            System.out.println("第" + i + "个班级学员的平均分是：" + avg + "\n");
        }
    }
}
