package com.thirdpart.jpt.test;

/**
 * Created by haiyang@sph.com.sg on 2024/07/11 08:46
 */
public class Test2 {
    public void test2() {
        System.out.println("这是进入第二层");
        new Test3().test3("heelo");
    }
}
