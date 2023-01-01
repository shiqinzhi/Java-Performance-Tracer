package com.thirdpart.jpt.test;

import com.thirdpart.jpt.test.xxx.TestEntry;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class JPT_CoreTester_Application {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Begin............. " + JPT_CoreTester_Application.class);
        TestEntry te = new TestEntry();
        te.sayHello0("");

        for (int i = 0; i < 5; i++) {
            te.sayHello1();
        }

        {
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                threads.add( new Thread( ()->{
                    te.sayHello1();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } ));
            }
            threads.forEach(t -> t.start());
        }

        te.sayHello4();

        Thread.sleep(10000);

        System.out.println("JPT_ClientDemo_Application Over!!!!!!");
        System.out.println("End " + JPT_CoreTester_Application.class);
    }
}
