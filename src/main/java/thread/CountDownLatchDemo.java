package thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author  yangjie
 * @date    2017/12/18
 * @time    上午9:41
 */

public class CountDownLatchDemo {
    private static final int SIZE=10;

    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        CountDownLatch start_latch=new CountDownLatch(1);
        CountDownLatch end_latch=new CountDownLatch(SIZE);
        for(int i=0;i<SIZE;i++){
            exec.execute(new Runner(start_latch,end_latch));
        }

        exec.execute(new Cocah(start_latch,end_latch));
        exec.shutdown();
    }

}

class Runner implements Runnable{
    private static int counter=0;
    private final  int id=counter++;
    private static Random rand=new Random(47);
    private final CountDownLatch start_latch;
    private final CountDownLatch end_latech;

    public Runner(CountDownLatch start_latch,CountDownLatch end_latch){
        this.start_latch=start_latch;
        this.end_latech=end_latch;
    }

    @Override
    public void run() {
        try {
            start_latch.await();
            doWork();

        } catch (InterruptedException e) {
            System.out.println("Interrupted Runner"+id);
            e.printStackTrace();
        }finally {
            end_latech.countDown();
        }

    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this+"completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-9d",id);
    }
}

class Cocah implements Runnable{

    private final CountDownLatch start_latch;
    private final CountDownLatch end_latch;

    public Cocah(CountDownLatch start_latch, CountDownLatch end_latch) {
        this.start_latch = start_latch;
        this.end_latch = end_latch;
    }

    @Override
    public void run() {
        start_latch.countDown();

        System.out.println("Coach Say:Ready!!! Go!!!");

        try {
            end_latch.await();
            System.out.println("All Runner passed the end point");
        } catch (InterruptedException e) {
            System.out.println(this+"interrupted");
            e.printStackTrace();
        }


    }
}

