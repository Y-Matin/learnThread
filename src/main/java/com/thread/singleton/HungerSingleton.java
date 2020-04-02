package com.thread.singleton;


/**
 * @description: 饿汉式单例 -- 本身线程安全
 * @author: Yeds
 * @create: 2020-04-02 18:41
 **/
public class HungerSingleton {

    // 如果使用 public 来修饰 singleton，从访问层面来看，是没有问题的，但及其的不安全，
    // 别人可以通过这种方式来赋值：HungerSingleton.singleton= null。 因此不采用public修饰
    private static HungerSingleton singleton = new HungerSingleton();

    public static HungerSingleton getinstance() {
        return singleton;
    }

    private HungerSingleton() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(HungerSingleton.getinstance());
            }).start();
        }
    }

}
