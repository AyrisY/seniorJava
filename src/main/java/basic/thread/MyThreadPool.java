package basic.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yangjie
 * @date 2019-04-25
 * @time 10:09
 */
public class MyThreadPool {

    @Test
    public void getThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
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
            System.out.println(Thread.currentThread().getName() + "threadpool is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "threadpool is shutdown");

        return;
    }

    @Test
    public void createThreadPool() {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        Random random = new Random(10);
        for (int i = 0; i < 15; i++) {
            pool.execute(() -> {
//                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(Thread.currentThread().getName() + " end");
            });

            int currentPoolSize = pool.getPoolSize();
            long completedTaskCount = pool.getCompletedTaskCount();
            int queueSize = pool.getQueue().size();
            System.out.println("currentPoolSize:" + currentPoolSize + ",completedTaskCount:" + completedTaskCount + ",queueSize:" + queueSize);

        }

        pool.shutdown();

        int corePoolSize = pool.getCorePoolSize();
        int maxPoolSize = pool.getMaximumPoolSize();
        long taskCount = pool.getTaskCount();

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("corePoolSize:" + corePoolSize + ",maxPoolSize:" + maxPoolSize + ",taskCount:" + taskCount);

        while (!pool.isTerminated()) {
            int currentPoolSize = pool.getPoolSize();
            long completedTaskCount = pool.getCompletedTaskCount();
            int queueSize = pool.getQueue().size();
            System.out.println("currentPoolSize:" + currentPoolSize + ",completedTaskCount:" + completedTaskCount + ",queueSize:" + queueSize);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("thread pool is shutdown");

        return;
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.getThreadPool();
    }

}
