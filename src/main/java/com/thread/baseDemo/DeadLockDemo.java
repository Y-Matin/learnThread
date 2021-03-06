package com.thread.baseDemo;

/**
 * @description: 死锁演示demo
 * 背景：有两位小朋友打架，喜欢揪头发，所以一般都是先护住自己的头发，再去揪对方的头发
 * @author: Yeds
 * @create: 2020-03-31 16:06
 **/

public class DeadLockDemo {
    private static final Object HAIR_A = new Object();
    private static final Object HAIR_B = new Object();

    public static void main(String[] args) {
        // 分别创建两个线程。

        Thread thread_A = new Thread(() -> {
            synchronized (HAIR_A) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (HAIR_B) {
                    System.out.println("A成功抓住了B的头发");
                }
            }
        });

        Thread thread_B = new Thread(() -> {
            synchronized (HAIR_B) {
                synchronized (HAIR_A) {
                    System.out.println("B成功抓住了A的头发");
                }
            }
        });

        thread_A.start();
        thread_B.start();
    }
}
