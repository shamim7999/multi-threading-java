package org.multithreading.miscellaneous;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class Printer implements Callable<String> {

    private final CountDownLatch countDownLatch;

    public Printer(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(200);
            this.countDownLatch.countDown();
        } finally {
            return Thread.currentThread().getName();
        }
    }
}
