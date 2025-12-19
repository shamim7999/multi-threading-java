package org.multithreading.deadlock;

public class Pen implements Runnable {

    private Paper paper;

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @Override
    public void run() {
        writeWithPen();
    }

    public synchronized void writeWithPen() {
        System.out.println(Thread.currentThread().getName()
                + " acquired PEN and trying to get PAPER");

        try {
            Thread.sleep(1000); // just to increase deadlock chance
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName()
                + " finished writing with PEN");
    }
}


