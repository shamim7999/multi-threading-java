package org.multithreading.deadlock;

public class Paper implements Runnable {

    private Pen pen;

    public void setPen(Pen pen) {
        this.pen = pen;
    }

    @Override
    public void run() {
        writeWithPaper();
    }

    public synchronized void writeWithPaper() {
        System.out.println(Thread.currentThread().getName()
                + " acquired PAPER and trying to get PEN");

        try {
            Thread.sleep(1000); // just to increase deadlock chance
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName()
                + " finished writing with PAPER");
    }
}
