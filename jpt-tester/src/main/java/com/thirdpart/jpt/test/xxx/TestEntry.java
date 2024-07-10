package com.thirdpart.jpt.test.xxx;

import com.thirdpart.jpt.test.xxx.tp1.AAAA3;

import java.util.Random;

public class TestEntry {

    public String sayHello0(String memo) {
        System.out.println("now in " + this.getClass());
        return "rrrrr";
    }

    public final int sayHello1() {
        System.out.println("now in " + this.getClass());
        try {
            new TestEntry().sayHello2();
        } catch (Exception e1) {

        }
        final Random random = new Random();
        final int r = random.nextInt(100);
        if (r > 50) {
            throw new RuntimeException("随机异常！");
        }
        return 999;
    }

    public int sayHello2() throws Exception {
        System.out.println("now in " + this.getClass());
        new TestEntry().sayHello3();
        return 999;
    }

    public void sayHello3() throws Exception {
        System.out.println("now in " + this.getClass());
        AAAA3.main(null);
        Thread.sleep(1000);
        throw new Exception();
    }

    public void sayHello4() {
        for (int i = 0; i < 20; i++) {
            sayHello5();
        }
    }

    public void sayHello5() {
    }
}
