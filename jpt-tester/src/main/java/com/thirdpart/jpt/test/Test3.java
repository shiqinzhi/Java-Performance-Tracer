package com.thirdpart.jpt.test;

import java.util.Random;

/**
 * Created by haiyang@sph.com.sg on 2024/07/11 08:46
 */
public class Test3 {
    public void test3(String str) {
        System.out.println("第三层：有一个参数:" + str);
        if (new Random().nextInt(100) > 50) {
            throw new RuntimeException("Test3 throw exception");
        }
        new Test4().test4(str);
    }
}
