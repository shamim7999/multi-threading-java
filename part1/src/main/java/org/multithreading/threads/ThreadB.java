package org.multithreading.threads;

public class ThreadB implements Runnable{
    @Override
    public void run() {
        for(int i=0; i<1000000; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield();
        }
    }
}
