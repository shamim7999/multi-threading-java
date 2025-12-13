package org.multithreading.threads;

public class ThreadA implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
