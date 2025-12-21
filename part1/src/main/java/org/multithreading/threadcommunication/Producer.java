package org.multithreading.threadcommunication;

public class Producer implements Runnable{

    final private SharedData sharedData;

    public Producer(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void run() {
        for(int i=1; i<=10; i++) {
            try {
                this.sharedData.produce(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
