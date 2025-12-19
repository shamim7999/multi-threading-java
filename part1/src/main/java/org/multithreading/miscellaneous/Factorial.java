package org.multithreading.miscellaneous;

public class Factorial {
    private long fact;
    private int n;

    public long getFactorial(int n) {
        try {
            Thread.sleep(1000);
            this.n = n;
            this.fact = 1;
            for(int i=2; i<=this.n; i++) {
                fact*=i;
            }

        } finally {
            return fact;
        }
    }
}
