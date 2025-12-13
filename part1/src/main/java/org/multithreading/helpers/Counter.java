package org.multithreading.helpers;

public class Counter {
    private int count = 0;

    public int getCount(){
        return this.count;
    }

    public void setCount() {
//        synchronized (this) {
//            this.count++;
//        } // This will run synchronously.
        this.count++; // This will run concurrently
    }

//    public synchronized void setCount() {
//        this.count++; // This section is called critical section. Because this section is
    // shared by multiple objects.
//    }

}
