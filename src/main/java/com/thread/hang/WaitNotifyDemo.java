package com.thread.hang;

/**
 * @description: 使用wait(), notify(), notifyAll() 完成线程挂起，恢复
 * 注意点： wait()，notify() ，锁住的对象和 wait()/notify()/notifyALl() 的调用的者要是同一个。
 * @author: Yeds
 * @create: 2020-04-01 14:47
 **/
public class WaitNotifyDemo implements Runnable {
    private static Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + ": 进入挂起，占用资源");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ": 恢复运行，释放资源");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WaitNotifyDemo(), "示例线程");
        thread.start();

        Thread thread2 = new Thread(new WaitNotifyDemo(),"示例线程2");
        thread2.start();

        Thread.sleep(1000L);
        synchronized (object) {
            object.notify();
        }
    }

/*    控制台结果： <可以看到线程先占用来资源，但由于调用了wait()释放了锁，然后线程2才能去占用资源>
        示例线程: 进入挂起，占用资源
        示例线程2: 进入挂起，占用资源
        示例线程: 恢复运行，释放资源
    */

}
