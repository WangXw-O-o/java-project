package com.wxw.java.thread.pool;

public class MyThreadImRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(">>> MyThreadImRunnable.run...");
    }
}
