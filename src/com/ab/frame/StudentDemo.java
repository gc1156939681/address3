package com.ab.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 郭翠
 * time 11-8
 */
public class StudentDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();

        Student student1 = new Student("1602753136","郭翠");
        Student student2 = new Student("1602753107","刘宇欣");
        Student student3 = new Student("1602753138","史冬阳");
        Student student4 = new Student("1602753140","徐文倩");
        Student student5 = new Student("1602753133","陈睿玥");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);

        List<Student> list1 = new ArrayList<>();

        Student student6 = new Student("1602753101","果果");
        Student student7 = new Student("1602753102","大美");
        Student student8 = new Student("1602753103","阳阳");
        Student student9 = new Student("1602753104","倩倩");
        Student student10 = new Student("1602753105","玥玥");
        list1.add(student6);
        list1.add(student7);
        list1.add(student8);
        list1.add(student9);
        list1.add(student10);

        Map<String,List<Student>> map1 = new HashMap<>();
        map1.put("强化班",list);
        map1.put("普通班",list1);
        map1.forEach((k,v) -> System.out.println(k + v));

    }
}

