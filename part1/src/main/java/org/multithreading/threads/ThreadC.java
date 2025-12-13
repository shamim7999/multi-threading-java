package org.multithreading.threads;

import org.multithreading.helpers.Counter;

public class ThreadC implements Runnable{

    private final Counter counter;

    public ThreadC(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0; i<100000; i++)
            this.counter.setCount();
    }
}
