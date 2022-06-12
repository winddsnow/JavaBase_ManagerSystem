package com.itheima.bean;

public class Student extends Person{
        //子类-即这个学生类的无参构造
        public Student() {
        }
        //super-继承自父类的成员变量
        public Student(String sid, String name, String birthday, int age, String sex) {
            super(sid, name, birthday, age, sex);
        }
        //重写了父类的describe方法，多态基础，相同父类方法，子类不同行为
        @Override
        public String describe() {
            return "我是一名学生,我的工作是:学习";
        }
    }

