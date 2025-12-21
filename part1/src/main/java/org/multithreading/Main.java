package org.multithreading;


import org.multithreading.deadlock.Paper;
import org.multithreading.deadlock.Pen;
import org.multithreading.helpers.Counter;
import org.multithreading.interfaces.Banks;
import org.multithreading.locks.DutchBanglaBank;
import org.multithreading.miscellaneous.Circle;
import org.multithreading.miscellaneous.Factorial;
import org.multithreading.miscellaneous.Square;
import org.multithreading.synchronizes.SCBBank;
import org.multithreading.threadcommunication.Consumer;
import org.multithreading.threadcommunication.Producer;
import org.multithreading.threadcommunication.SharedData;
import org.multithreading.threads.ThreadA;
import org.multithreading.threads.ThreadC;

import java.sql.ShardingKey;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

        consumerThread.join();
        producerThread.join();

        System.out.println("FACTORIAL CALCULATION USING THREADS\n\n");


        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];
        for (int i = 1; i <= 10; i++) {
            final int finalI = i;
            threads[finalI - 1] = new Thread(() -> {
                System.out.println("Factorial(" + finalI + "): " + new Factorial().getFactorial(finalI));
            });
            threads[finalI - 1].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }


        /**
         * Example: Sequential Factorial Computation
         *
         * The following code computes factorials from 1 to 10 sequentially:
         *
         * <pre>
         * // Factorial factorial = new Factorial();
         * // for(int i = 1; i <= 10; i++) {
         * //     System.out.println("Factorial(" + i + "): " + factorial.getFactorial(i));
         * // }
         * </pre>
         *
         * Explanation:
         * - The loop calls `factorial.getFactorial(i)` one by one.
         * - Inside `getFactorial()`, there is a `Thread.sleep(1000)` (1 second) to simulate delay.
         * - Since the loop is sequential, each call to `getFactorial()` waits for 1 second before computing the factorial.
         * - Therefore, computing factorials from 1 to 10 takes approximately 10 seconds in total (1 second per iteration).
         *
         * Note:
         * - Unlike multi-threaded execution where threads can sleep in parallel, here each call blocks the main thread,
         *   causing a cumulative delay.
         */


        System.out.println("Total Time taken: " + (System.currentTimeMillis() - startTime) + "ms");

        System.out.println("Doing Factorial evaluation using EXECUTOR FRAMEWORK...");
        startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            for (int i = 1; i <= 10; i++) {
                final int finalI = i;
                executorService.submit(() -> {
                    System.out.println("Factorial(" + finalI + "): " + new Factorial().getFactorial(finalI));
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Total Time taken by executor service: " + (System.currentTimeMillis() - startTime) + "ms");


        System.out.println("Now, evaluating area of circles using callable interface...");
        startTime = System.currentTimeMillis();
        ExecutorService callableService = Executors.newFixedThreadPool(3);

        List<Future<Double>> futures = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            futures.add(callableService.submit(new Circle((double) i)));
        }
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println("Area of Circle with radius " + i + " is: " + futures.get(i - 1).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        callableService.shutdown();
        callableService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Total Time taken by callable interface: " + (System.currentTimeMillis() - startTime) + "ms");


        System.out.println("Now, evaluating area of squares using invokeAll()...");
        startTime = System.currentTimeMillis();
        ExecutorService executorService2 = Executors.newFixedThreadPool(3);
        Square square = new Square();
        List<Callable<Double>> squareCallbles = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final Double finalSide = (double) i;
            Square sq = new Square();
            sq.setSide(finalSide);
            squareCallbles.add(() -> sq.area());
        }
        List<Future<Double>> squareAreas = executorService2.invokeAll(squareCallbles);

        int side = 1;

        for (Future<Double> squareArea : squareAreas) {
            try {
                System.out.println("Area of Square with side " + side++ + " is: " + squareArea.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService2.shutdown();
        executorService2.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Total Time taken by callable interface: " + (System.currentTimeMillis() - startTime) + "ms");

    }
}