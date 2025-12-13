package org.multithreading;


import org.multithreading.helpers.Counter;
import org.multithreading.interfaces.Banks;
import org.multithreading.locks.DutchBanglaBank;
import org.multithreading.synchronizes.SCBBank;
import org.multithreading.threads.ThreadA;
import org.multithreading.threads.ThreadC;

public class Main {
    public static void main(String[] args) {
//        ThreadA threadA = new ThreadA();
//        Thread th1 = new Thread(threadA, "Chrome");
//        Thread th2 = new Thread(threadA, "Firefox");
//        Thread th3 = new Thread(threadA, "Safari");
//
//
//        Counter counter = new Counter();
//        ThreadC threadC = new ThreadC(counter);
//
//        Thread th4 = new Thread(threadC, "Counter-1");
//        Thread th5 = new Thread(threadC, "Counter-2");
//
//
////        th1.start();
////        th2.start();
////        th3.start();
//        th4.start();
//        th5.start();
//
//        try {
//            th4.join();
//            th5.join();
//
//        } catch (Exception e) {
//
//        }
//
//        System.out.println("Counter: " + counter.getCount());

        Runnable scbBank = new SCBBank();
        Thread scbBankThread1 = new Thread(scbBank, "SCB-BANK-Customer1");
        Thread scbBankThread2 = new Thread(scbBank, "SCB-BANK-Customer2");

        scbBankThread1.start();
        scbBankThread2.start();
    }
}