package org.multithreading.locks.readwritelocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    private int counter = 0;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public void increment() throws InterruptedException {
        boolean writeLockAcquired = writeLock.tryLock(600, TimeUnit.MILLISECONDS);

        if (!writeLockAcquired) {
            System.out.println(
                    Thread.currentThread().getName() +
                            " could NOT acquire write lock"
            );
            return;
        }

        try {
            counter++;
            System.out.println(
                    Thread.currentThread().getName() +
                            " acquired write lock, counter = " + counter
            );
        } finally {
            writeLock.unlock();
            System.out.println(
                    Thread.currentThread().getName() +
                            " released write lock when counter is incremented to = " + counter
            );
        }
    }


    public void getCount() throws InterruptedException {
        boolean readLockAcquired = readLock.tryLock(600, TimeUnit.MILLISECONDS);

        if (!readLockAcquired) {
            System.out.println(
                    Thread.currentThread().getName() +
                            " could NOT acquire read lock"
            );
            return; // indicates read failed
        }

        try {
            int value = counter;
            System.out.println(
                    Thread.currentThread().getName() +
                            " acquired read lock, counter = " + value
            );
        } finally {
            readLock.unlock();
            System.out.println(
                    Thread.currentThread().getName() +
                            " released read lock after seeing counter = " + counter
            );
        }
    }



}
