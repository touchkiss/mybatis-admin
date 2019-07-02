package com.touchkiss.mybatis.demo;

/**
 * @Author Touchkiss
 * @create: 2019-06-19 15:36
 */
public class MainTes {
    static MainTes instance=new MainTes();
    static {
        System.out.println("这里是static方法");
    }
    {
        System.out.println("这里是构造块");
    }
    private MainTes(){
        System.out.println("这是初始化方法");
    }
    public static void main(String[]args){
        System.out.println("这里是主方法");
    }
}
