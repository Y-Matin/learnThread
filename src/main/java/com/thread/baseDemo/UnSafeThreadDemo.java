package com.thread.baseDemo;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 线程不安全实例
 * <多线程对同一值进行修改，没有同步>:
 * 比如：有两个线程同时查询 number的值为10，并各自执行++操作，最后又将11赋给了number，这样就导致number的值只自加了一次。
 * 与实际执行逻辑不符。导致线程不安全。
 * @author: Yeds
 * @create: 2020-04-01 09:40
 **/
public class UnSafeThreadDemo {
    private static int number = 0;

    // 由于线程为执行完毕，sout已经执行。so 引入  CountDownLatch
    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void addNumber() {
        number++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    addNumber();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }

        // 等待线程执行结束，输出结果值
        while (true) {
            if (countDownLatch.getCount() == 0) {
                System.out.println(number);
                break;
            }
        }
    }
}
