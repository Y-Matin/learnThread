package com.thread.iinterrupt;

/**
 * @description: 通过 Volatile 修饰符 中断线程
 * @author: Yeds
 * @create: 2020-04-01 16:56
 **/
public class InterruptThreadByVolatile  implements Runnable{
    private static  volatile boolean  FLAG= true;


    @Override
    public void run() {
        while (FLAG) {
            System.out.println(Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptThreadByVolatile());
        thread.start();
        Thread.sleep(1000L);
        System.out.println("将volatile 修饰的boolean变量设置为 false");
        FLAG = false;
        System.out.println("主线程结束");
    }


}
