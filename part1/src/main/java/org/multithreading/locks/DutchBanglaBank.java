package org.multithreading.locks;

import org.multithreading.interfaces.Banks;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DutchBanglaBank implements Banks, Runnable {

    private int balance = 100;
    private final Lock lock = new ReentrantLock();

    @Override
    public void withdraw(int amount) {

        System.out.println(Thread.currentThread().getName()
                + " is trying to withdraw " + amount + " BDT.");

        boolean isLockAcquired = false;

        try {
            // ⏳ wait max 3 seconds to get the lock
            System.out.println(Thread.currentThread().getName()
                    + " trying to get the lock.");
            isLockAcquired = lock.tryLock(6, TimeUnit.SECONDS);

            if (isLockAcquired) {
                if (this.balance >= amount) {
                    System.out.println(Thread.currentThread().getName()
                            + " acquired lock, proceeding with withdrawal");

                    Thread.sleep(5000); // simulate long processing

                    this.balance -= amount;
                    System.out.println(Thread.currentThread().getName()
                            + " completed withdrawal. Current balance: " + this.balance);
                } else {
                    System.out.println(Thread.currentThread().getName()
                            + " insufficient balance");
                }
            } else {
                System.out.println(Thread.currentThread().getName()
                        + " could not acquire lock within time. Try again later.");
            }

        } catch (InterruptedException ex) {
            System.out.println(Thread.currentThread().getName()
                    + " interrupted while waiting for lock");
        } finally {
            if (isLockAcquired) {
                lock.unlock(); // 🔓 unlock only if acquired
            }
        }
    }

    @Override
    public void run() {
        withdraw(50);
    }
}
