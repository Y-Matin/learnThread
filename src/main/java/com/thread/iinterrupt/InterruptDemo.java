package com.thread.iinterrupt;

/**
 * @description: interrupt() 中断线程
 * @author: Yeds
 * @create: 2020-04-01 16:51
 **/
public class InterruptDemo implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptDemo());
        thread.start();
        Thread.sleep(1000L);
        System.out.println("中断thread");
        thread.interrupt();
        System.out.println("主线程结束");
    }
}
