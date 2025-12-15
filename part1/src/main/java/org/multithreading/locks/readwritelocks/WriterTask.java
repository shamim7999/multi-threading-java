package org.multithreading.locks.readwritelocks;

public class WriterTask implements Runnable{

    private final ReadWriteLockExample readWriteLockExample;

    public WriterTask(ReadWriteLockExample readWriteLockExample) {
        this.readWriteLockExample = readWriteLockExample;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                readWriteLockExample.increment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
