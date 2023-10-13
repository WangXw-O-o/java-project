package com.wxw.java.thread.multi;

import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        demo1();
//        demo2(executorService);
//        demo3(executorService);
        demo4(executorService);
        executorService.close();
    }

    public static void demo1() throws ExecutionException, InterruptedException {
        //使用默认线程池，简单执行一个任务
        CompletableFuture<String> cf  = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "cf执行中......");
            return "result_cf";
        });
        System.out.println("执行完成！结果：" + cf.get());
    }

    public static void demo2(ExecutorService executorService) throws ExecutionException, InterruptedException {
        //使用自定义线程池，执行一个任务cf1
        CompletableFuture<String> cf1  = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ===> cf1执行中......");
            return "result_cf1";
        }, executorService);
        //cf2 依赖于cf1的执行结果，使用cf1所使用的线程
        CompletableFuture<String> cf2  = cf1.thenApply(result -> {
            System.out.println(Thread.currentThread().getName() + " ===> cf1执行的结果："+result+"，cf2开始执行......");
            return "result_cf2";
        });
        System.out.println("cf2执行完成！结果："+cf2.get());
    }

    public static void demo3(ExecutorService executorService) throws ExecutionException, InterruptedException {
        //使用自定义线程池，执行一个任务cf1
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ===> cf1执行中......");
            return "result_cf1";
        }, executorService);
        //使用自定义线程池，执行一个任务cf2
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ===> cf2执行中......");
            return "result_cf2";
        }, executorService);
        //cf3依赖于 cf1 和 cf2，cf1和cf2执行完成后才开始执行cf3，并且使用自定义的线程池中的线程
        CompletableFuture<String> cf3 = cf2.thenCombineAsync(cf1, (result1, result2) -> {
            System.out.println(Thread.currentThread().getName()
                    + " ===> cf1执行结果：" + result1
                    + "，cf2执行结果：" + result2
                    + "，cf3开始执行......");
            return "result_cf3";
        }, executorService);

        System.out.println("cf3执行完成！结果："+cf3.get());
    }

    public static void demo4(ExecutorService executorService) throws ExecutionException, InterruptedException {
        //使用自定义线程池，执行一个任务cf1
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ===> cf1执行中......");
            return "result_cf1";
        }, executorService);
        //使用自定义线程池，执行一个任务cf2
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ===> cf2执行中......");
            return "result_cf2";
        }, executorService);
        //cf3依赖于 cf1 和 cf2，cf1和cf2执行完成后才开始执行cf3，并且使用自定义的线程池中的线程
        CompletableFuture<String> cf3 = cf2.thenCombineAsync(cf1, (result1, result2) -> {
            System.out.println(Thread.currentThread().getName()
                    + " ===> cf1执行结果：" + result1
                    + "，cf2执行结果：" + result2
                    + "，cf3开始执行......");
            return "result_cf3";
        }, executorService);


        //cf4依赖于cf1、cf2、cf3，只有cf1、cf2、cf3都执行完成后cf4才开始执行
        CompletableFuture<String> cf4 = CompletableFuture.allOf(cf1, cf2, cf3).thenApplyAsync(r -> {
            String result1 = cf1.join();
            String result2 = cf2.join();
            String result3 = cf3.join();
            System.out.println(Thread.currentThread().getName()
                    + " ===> cf1执行结果：" + result1
                    + "，cf2执行结果：" + result2
                    + "，cf3执行结果：" + result3
                    + "，cf4开始执行......");
            return "result_cf4";
        }, executorService);


        //cf5依赖于cf1、cf2、cf3，只要cf1、cf2、cf3其中有一个执行完成，cf5都开始执行
        CompletableFuture<String> cf5 = CompletableFuture.anyOf(cf1, cf2, cf3).thenApplyAsync(r -> {
            System.out.println(Thread.currentThread().getName()
                    + " ===> 前置任务执行结果：" + r
                    + "，cf5开始执行......");
            return "result_cf5";
        }, executorService);

        System.out.println("cf4执行完成！结果："+cf4.get());
        System.out.println("cf5执行完成！结果："+cf5.get());

    }


}
