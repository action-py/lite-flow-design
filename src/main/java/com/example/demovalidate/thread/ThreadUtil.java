package com.example.demovalidate.thread;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.concurrent.*;

/**
 * 项目名称：cupid
 * 类 名 称：ThreadUtil
 * 类 描 述：TODO
 * 创建时间：2022/11/28 11:44
 * 创 建 人：panyong
 */
public class ThreadUtil {
    static class CustomBlockingQueue<E> extends ArrayBlockingQueue<E> {

        public CustomBlockingQueue(int capacity) {
            super(capacity);
        }

        public CustomBlockingQueue(int capacity, boolean fair) {
            super(capacity, fair);
        }

        public CustomBlockingQueue(int capacity, boolean fair, Collection c) {
            super(capacity, fair, c);
        }

        @Override
        public boolean offer(E e) {
            return false;
        }

        @Override
        public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        public boolean offerAgain(E e) {
            return super.offer(e);
        }
        public boolean offerAgain(E e, long timeout, TimeUnit unit) throws InterruptedException {
            return super.offer(e, timeout, unit);
        }
    }
    static class SimpleThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("thread--demo");
            return thread;
        }
    }
    static class CustomPolicy implements RejectedExecutionHandler {
        private CustomBlockingQueue<Runnable> queue;
        public CustomPolicy(CustomBlockingQueue<Runnable> queue) {
            this.queue = queue;
        }
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.err.println(Thread.currentThread().getName()+Thread.currentThread().getId()+"空间:"+queue.size());
            boolean b = queue.offerAgain(r);
            if(!b){
                System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+"队列已满："+r.getClass());
            }
                /*Runnable poll = queue.poll(10, TimeUnit.MILLISECONDS);
                queue.put(r);
                System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+"之前存放的任务："+poll.getClass());*/
            /*throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    e.toString());*/
        }
    }
    static CustomBlockingQueue queue;
    public static ThreadPoolExecutor executor;
    static {
        queue =new CustomBlockingQueue<Runnable>(3);
        executor =new ThreadPoolExecutor(3,
                5,
                20l,TimeUnit.SECONDS,queue,new SimpleThreadFactory(),new CustomPolicy(queue));

    }
    //jvm关闭时 优雅关闭线程池
    @PostConstruct
    public void init(){
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            executor.shutdown();
        }));
    }
    public static void main(String[] args) throws InterruptedException {
        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+Thread.currentThread().getId()+":"+ finalI);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (executor.getPoolSize()!=0){
            System.out.println("ActiveCount:"+executor.getActiveCount());
            System.out.println(executor.allowsCoreThreadTimeOut());
            System.out.println("PoolSize:"+executor.getPoolSize());
            System.err.println("等待核心线程销毁～～～～");
            Thread.sleep(3000);
            if(executor.getActiveCount()==0 && executor.getPoolSize() == executor.getCorePoolSize()){
                executor.allowCoreThreadTimeOut(true);
            }
        }
        //executor.shutdown();
    }
}
