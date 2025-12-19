package org.multithreading.threadcommunication;

public class SharedData {

    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) throws InterruptedException {

        // produce only when hasData is false
        while (hasData) {
            wait();
        }

        data = value;
        hasData = true;
        System.out.println("Produced: " + data);

        notify(); // wake up consumer
    }

    public synchronized void consume() throws InterruptedException {

        // consume only when hasData is true
        while (!hasData) {
            wait();
        }

        System.out.println("Consumed: " + data);
        hasData = false;

        notify(); // wake up producer
    }
}
