package com.thread.singleton;

/**
 * @description: 懒汉式单例 --最简单的写法是
 * 非线程安全的 ->synchronized修饰方法<性能不行> ->synchronized修饰new代码块<线程不安全> -> double check -> 添加 volatie 修饰符
 * @author: Yeds
 * @create: 2020-04-02 18:45
 **/
public class LazyHungerSingleton {

    // private ：只能通过方法获取，防止别人对singleton赋值
    // static ： 因为getInstance()方法是静态的，所以返回的数据只能是static
    // volatile : 作用是为了禁止指令重排序。对双重检测锁的补充
    private static volatile LazyHungerSingleton singleton = null;

    public static LazyHungerSingleton getInstance() throws InterruptedException {
        if (singleton == null) {
            synchronized (LazyHungerSingleton.class) {
                // double check 双重检测锁(可能会发生指令重排序，导致线程不安全)
                if (singleton == null) {
                    singleton = new LazyHungerSingleton();
                }
            }
        }
        return singleton;
    }

    private LazyHungerSingleton() {}

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(LazyHungerSingleton.getInstance());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
