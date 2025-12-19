package org.multithreading.threadcommunication;

public class Consumer implements Runnable{

    final private SharedData sharedData;

    public Consumer(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        for(int i=1; i<=10; i++) {
            try {
                this.sharedData.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
