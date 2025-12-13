package org.multithreading.synchronizes;

import org.multithreading.interfaces.Banks;

public class SCBBank implements Banks, Runnable {

    private int balance = 100;

    @Override
    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() +
                " is trying to withdraw " + amount + " BDT.");
        if(this.balance >= amount) {
            System.out.println(Thread.currentThread().getName() +
                    " proceeding with withdrawal");
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
                System.out.println("Exception is: " + ex.getMessage());
            }
            this.balance -= amount;
            System.out.println(Thread.currentThread().getName() +
                    " completed withdrawal. Current balance is: " + this.balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }

    @Override
    public void run() {
        this.withdraw(50);
    }
}
