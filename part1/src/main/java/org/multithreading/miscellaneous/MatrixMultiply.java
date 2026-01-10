package org.multithreading.miscellaneous;

import java.util.concurrent.CyclicBarrier;

public class MatrixMultiply implements Runnable{

        private final int startRow, endRow;
        private final double[][] A, B, C;
        private final CyclicBarrier barrier;

        public MatrixMultiply(double[][] A, double[][] B, double[][] C,
               int startRow, int endRow, CyclicBarrier barrier) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.startRow = startRow;
            this.endRow = endRow;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                // Phase 1: Load matrices (here it's just already filled)
                System.out.println(Thread.currentThread().getName() +
                        " loaded its chunk of A.");

                barrier.await(); // meeting point for loading

                // Phase 2: Compute multiplication
                for (int i = startRow; i < endRow; i++) {
                    for (int j = 0; j < B[0].length; j++) {
                        double sum = 0;
                        for (int k = 0; k < A[0].length; k++) {
                            sum += A[i][k] * B[k][j];
                        }
                        C[i][j] = sum;
                    }
                }

                System.out.println(Thread.currentThread().getName() +
                        " finished computing rows: " + startRow + " to " + (endRow - 1));

                barrier.await(); // optional next phase sync

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

