package org.multithreading.locks;

import org.multithreading.interfaces.Banks;

public class DutchBanglaBank implements Banks, Runnable {
    @Override
    public void withdraw(int amount) {

    }

    @Override
    public void run() {
        this.withdraw(50);
    }
}
