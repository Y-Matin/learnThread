package com.thread.hang;

/**
 * @description: 使用suspend()导致死锁 示例  <resume()先于suspend()执行>
 * @author: Yeds
 * @create: 2020-04-01 14:31
 **/
public class DeadLockByHang implements Runnable {
    private static Object object = new Object();

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + ": 进入挂起，占用资源");
            Thread.currentThread().suspend();
        }
        System.out.println(Thread.currentThread().getName() + ": 恢复运行，释放资源");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DeadLockByHang(), "示例线程");
        thread.start();
        Thread.sleep(1000L);
        thread.resume();
        Thread thread2 = new Thread(new DeadLockByHang(), "死锁线程");
        thread2.start();
        // 如果main线程不休眠，就会导致 resume()先于suspend()执行，最终该线程会一直处于挂起状态。
//        Thread.sleep(1000L);
        thread2.resume();
    }
}
