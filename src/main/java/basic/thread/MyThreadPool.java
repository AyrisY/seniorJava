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

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 15; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            printThreadPoolDetail(pool);

        }

        System.out.println("---------------------------------------------------------------------------");
        printThreadPoolDetail(pool);

        while (!pool.isTerminated()) {
            printThreadPoolDetail(pool);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (pool.getQueue().size() == 0) {
                break;
            }

        }

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.printThreadPoolDetail(pool);

        pool.shutdown();

        this.printThreadPoolDetail(pool);

        System.out.println("thread pool is shutdown");

        return;
    }

    private void printThreadPoolDetail(ThreadPoolExecutor pool) {
        int currentPoolSize = pool.getPoolSize();
        long completedTaskCount = pool.getCompletedTaskCount();
        int queueSize = pool.getQueue().size();
        System.out.println("currentPoolSize:" + currentPoolSize + ",completedTaskCount:" + completedTaskCount + ",queueSize:" + queueSize);

    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.getThreadPool();
    }

}
