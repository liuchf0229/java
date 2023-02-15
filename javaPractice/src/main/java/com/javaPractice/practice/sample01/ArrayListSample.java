package com.javaPractice.practice.sample01;

import java.util.ArrayList;
import java.util.List;

public class ArrayListSample {
    public static void main(String[] args) {
        //泛型<类型>,用于规范列表中的数据
        List<String> bookList=new ArrayList<String>();//创建ArrayList对象
        bookList.add("红楼梦");//向列表末端追加数据
        bookList.add("西游记");
        bookList.add("水浒传");
        bookList.add("三国志");
        bookList.add("index:0，element“镜花缘");
        System.out.println(bookList);

        String bookName=bookList.get(2);//得到指定索引位置（从0开始）
        System.out.println(bookName);

        int size=bookList.size();//得到列表总数
        System.out.println(size);

        bookList.remove(2);
        bookList.remove(bookList.size()-1);
        System.out.println(bookList);

        for (String book:bookList){
            System.out.println("《"+book+"》");
        }
    }
}
