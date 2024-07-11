package com.thirdpart.jpt.test;

import java.util.Random;

/**
 * Created by haiyang@sph.com.sg on 2024/07/11 08:46
 */
public class Test1 {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            System.out.println("这是在第一层");
            try {
                Thread.sleep(500 + new Random().nextInt(100));
            } catch (Exception e) {

            }
            new Test2().test2();
        }
        try {
            System.out.println("等====关闭===");
            Thread.sleep(3000 + new Random().nextInt(100));
        } catch (Exception e) {

        }
    }
}
