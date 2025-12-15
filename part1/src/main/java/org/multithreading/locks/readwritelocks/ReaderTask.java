package org.multithreading.locks.readwritelocks;

public class ReaderTask implements Runnable{

    private final ReadWriteLockExample readWriteLockExample;

    public ReaderTask(ReadWriteLockExample readWriteLockExample) {
        this.readWriteLockExample = readWriteLockExample;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                readWriteLockExample.getCount();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
