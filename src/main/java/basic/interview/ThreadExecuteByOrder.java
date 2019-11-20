package basic.interview;

import org.junit.Test;

/**
 * @author yangjie
 * @date 2019-11-20
 * @time 10:23
 */
public class ThreadExecuteByOrder {

    @Test
    public void testJoinThread() {
        // 使用join方法，线程t1,t2,t3顺序执行，在子线程中进行阻塞
        final Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 thread finish");
            }
        });

        final Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 thread finish");
            }
        });

        final Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3 thread finish");
            }
        });

        t3.start();
        t2.start();
        t1.start();
    }

    @Test
    public void testJoinThread2() throws InterruptedException {
        // 在主线程中使用join方法阻塞机制，保证线程顺序执行
        Runnable task=new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" finish");
            }
        };

        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");
        Thread t3 = new Thread(task, "t3");

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

    }
}
