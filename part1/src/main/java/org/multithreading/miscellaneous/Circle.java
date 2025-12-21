package org.multithreading.miscellaneous;

import org.multithreading.interfaces.Area;

import java.util.concurrent.Callable;

public class Circle implements Area, Callable<Double> {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    public Double call() throws Exception {
        return this.area();
    }

    @Override
    public Double area() throws InterruptedException {
        Thread.sleep(1000);
        return 3.1416*this.radius*this.radius;
    }
}
