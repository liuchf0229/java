package com.javaPractice.practice.sample01;
import java.util.Scanner;
/*
 * 功能：为指定的成绩加分，直到分数大于等于60为止
 * 输出加分前的成绩和加分后的成绩，并且统计加分的次数
 * 步骤：
 * 1.定义一个变量，用来统计加分的次数
 * 2.使用循环为成绩加分
 * 3.每次执行循环时加1分，并且统计加分的次数
 *
 * 使用Scanner工具类来获取用户输入的值
 * Scanner类位于java.util包中，使用时需要导入此包
 * 步骤：
 * 1.导入java.util.Scanner
 * 2.创建Scanner对象
 * 3.接收并保存用户输入的值
 */
public class Demo01 {
	public static void main(String[] args){
		Scanner input=new Scanner(System.in); //创建Scanner对象
		//print和println区别：println输出信息后会换行，而print不会换行
		System.out.println("请输入您的考试成绩：");
		int score=input.nextInt(); //获取成绩信息并保存在变量score中
		int count=0;  //统计次数
		System.out.println("加分前的成绩："+score);
		while(score<60){
			score++;//每次循环加1分
			count++;//统计加分的次数
		}
		System.out.println("加分后的成绩："+score);
		System.out.println("共加了"+count+"次！");
	}
}
