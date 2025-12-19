package org.multithreading;


import org.multithreading.deadlock.Paper;
import org.multithreading.deadlock.Pen;
import org.multithreading.helpers.Counter;
import org.multithreading.interfaces.Banks;
import org.multithreading.locks.DutchBanglaBank;
import org.multithreading.synchronizes.SCBBank;
import org.multithreading.threadcommunication.Consumer;
import org.multithreading.threadcommunication.Producer;
import org.multithreading.threadcommunication.SharedData;
import org.multithreading.threads.ThreadA;
import org.multithreading.threads.ThreadC;

import java.sql.ShardingKey;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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
        Runnable dutchBanglaBank = new DutchBanglaBank();

        Thread scbBankThread1 = new Thread(scbBank, "SCB-BANK-Customer1");
        Thread scbBankThread2 = new Thread(scbBank, "SCB-BANK-Customer2");

        Thread dutchBanglaThread1 = new Thread(dutchBanglaBank, "DUTCHBANGLA-BANK-Customer1");
        Thread dutchBanglaThread2 = new Thread(dutchBanglaBank, "DUTCHBANGLA-BANK-Customer2");

        scbBankThread1.start();
        scbBankThread2.start();

        dutchBanglaThread1.start();
        dutchBanglaThread2.start();

        scbBankThread1.join();
        scbBankThread2.join();
        dutchBanglaThread1.join();
        dutchBanglaThread2.join();

        System.out.println("Starting DEADLOCK EXAMPLE\n\n");

        Pen pen = new Pen();
        Paper paper = new Paper();

        pen.setPaper(paper);
        paper.setPen(pen);

        Thread penThread = new Thread((Runnable) pen, "PEN-THREAD");
        Thread paperThread = new Thread((Runnable) paper, "PAPER-THREAD");

        penThread.start();
        paperThread.start();

        penThread.join();
        paperThread.join();

        System.out.println("Starting THREAD COMMUNICATION EXAMPLE\n\n");

        SharedData sharedData = new SharedData();
        Runnable consumer = new Consumer(sharedData);
        Runnable producer = new Producer(sharedData);

        Thread consumerThread = new Thread(consumer);
        Thread producerThread = new Thread(producer);

        consumerThread.start();
        producerThread.start();

    }
}