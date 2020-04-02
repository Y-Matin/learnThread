package com.thread.safe;

/**
 * @description: synchronized关键字的三种用法
 * @author: Yeds
 * @create: 2020-04-02 18:02
 **/
public class SynchronizedDemo {

    private  Object object = new Object();

    // 1. 修饰静态方法：锁住的是整个类，进去同步代码前要获得当前类对象的锁。
    public static synchronized void staticOut() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
    }
    // 2. 修饰实例代码，锁住的是类的实例，进入同步代码前要获得当前实例的锁
    public synchronized void out() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
    }
    // 3. 修饰代码块：锁住的是一个对象object "synchronized(object)"，进入同步代码前要获得指定对象的锁。
    public void sout() throws InterruptedException {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
        }
    }


    public static void main(String[] args) {
        // 使用 第1中，查看输出是否间隔了5秒
      /*  new Thread(() -> {
            try {
                staticOut();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1调用sync修饰的静态方法").start();
        new Thread(() -> {
            try {
                staticOut();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2调用sync修饰的静态方法").start();*/

        //使用第二种，查看输出是否间隔了5秒
        SynchronizedDemo demo1 = new SynchronizedDemo();
        SynchronizedDemo demo2= new SynchronizedDemo();

        /*new Thread(() -> {
            try {
                demo1.out();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程3调用sync修饰的实例方法").start();
        new Thread(() -> {
            try {
                demo2.out();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程4调用sync修饰的实例方法").start();*/

        // 使用第3中

        new Thread(() -> {
            try {
                demo1.sout();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程5调用sync修饰的实例方法").start();
        new Thread(() -> {
            try {
                demo2.sout();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程6调用sync修饰的实例方法").start();

    }
}
