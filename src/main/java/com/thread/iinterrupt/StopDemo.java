package com.thread.iinterrupt;

/**
 * @description: 中断线程执行 示例 demo
 * @author: Yeds
 * @create: 2020-04-01 16:41
 **/
public class StopDemo implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new StopDemo());
        thread.start();
        Thread.sleep(1000);
        //
        thread.stop();
        System.out.println("主线程结束");
    }
}
