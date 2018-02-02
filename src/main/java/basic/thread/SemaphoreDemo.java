package basic.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjie
 * @date 2017/12/20
 * @time 上午9:53
 */
public class SemaphoreDemo {
    private static ExecutorService threadPool= Executors.newFixedThreadPool(Toilet.Toilet_COUNT);

    private static Semaphore semaphore=new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<Toilet.Toilet_COUNT;i++){
            threadPool.execute(new Employee(i+1,semaphore));
        }

        threadPool.shutdown();
    }

}

class Employee implements Runnable{

    private int id;
    private Semaphore semaphore;

    private static Random rand=new Random();

    public Employee(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            //获取信号量
            semaphore.acquire();
            System.out.println(this.id+" is using the toilet");
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));

            //释放锁
            semaphore.release();
            System.out.println(this.id+" leave the toilet");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }


    }
}

class Toilet{
     static final int Toilet_COUNT=30;
}
