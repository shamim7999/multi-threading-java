package org.multithreading.miscellaneous;

import org.multithreading.interfaces.Area;

import java.util.concurrent.Callable;

public class Square implements Area, Callable<Double> {
    private Double side;

    public void setSide(Double side) {
        this.side = side;
    }

    @Override
    public Double call() throws Exception {
        return this.area();
    }

    @Override
    public Double area() throws InterruptedException {
        Thread.sleep(1000);
        return this.side * this.side;
    }

}

