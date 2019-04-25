package basic.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author yangjie
 * @date 2019-04-25
 * @time 10:09
 */
public class MyThreadPool {

    @Test
    public void getThreadPool() {
        ExecutorService pool=Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        pool.shutdown();
        while (!pool.isTerminated()) {
            System.out.println(Thread.currentThread().getName()+"threadpool is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+"threadpool is shutdown");

        return ;
    }

    public ExecutorService createThreadPool() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10),new ThreadPoolExecutor.AbortPolicy());


        return null;
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.getThreadPool();
    }

}
