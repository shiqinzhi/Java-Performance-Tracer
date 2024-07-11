package com.thirdpart.jpt.test;

import java.util.Random;

/**
 * Created by haiyang@sph.com.sg on 2024/07/11 08:46
 */
public class Test4 {
    public void test4(String str) {
        try {
            Thread.sleep(500+new Random().nextInt(100));
        } catch (Exception e) {

        }
        System.out.println("当前我在第四层:" + str);
    }
}
