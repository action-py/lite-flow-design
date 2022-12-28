package com.example.demovalidate.controller;

/**
 * 项目名称：demo-validate
 * 类 名 称：ThreadDemo
 * 类 描 述：TODO
 * 创建时间：2022/12/9 10:19
 * 创 建 人：panyong
 */
public class ThreadDemo {
    public static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(1000);
                flag = false;
                System.err.println("flag:"+flag);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        while (flag) {
            System.out.println("～～～～～～无限循环～～～～～");
        }

    }
}
