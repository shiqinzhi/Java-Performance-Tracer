package com.thirdpart.jpt.test;

import java.util.Random;

/**
 * Created by haiyang@sph.com.sg on 2024/07/11 08:46
 */
public class Test2 {
    public void test2() {
        System.out.println("这是进入第二层");
        try {
            Thread.sleep(500+new Random().nextInt(100));
        } catch (Exception e) {

        }
        new Test3().test3("heelo");
    }
}
