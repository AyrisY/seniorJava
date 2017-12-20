package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author yangjie
 * @date 2017/12/18
 * @time 下午3:57
 */
public class CyclicBarrierDemo {
    static final int FINISH_SIZE=75;
    private List<Horse> horses=new ArrayList<Horse>();
    private ExecutorService exec= Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    private static boolean idDone=false;

    public CyclicBarrierDemo(int nHorses,final int pause) {
        barrier=new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder s=new StringBuilder();
                for(int i=0;i<FINISH_SIZE;i++){
                    s.append("=");
                }
                System.out.println(s);

                for(Horse horse:horses){
                    System.out.println(horse.tracks());
                }

                for(Horse horse:horses){
                    if(horse.getStrides()>=FINISH_SIZE){
                        System.out.println(horse+"won!");
                        exec.shutdownNow();
                        return ;
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        for(int i=0;i<nHorses;i++){
            Horse horse=new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses=7;
        int pause=200;
        new CyclicBarrierDemo(nHorses,pause);
    }
}

class Horse implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private int strides=0;
    private Random rand=new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b){
        barrier=b;
    }

    public synchronized int getStrides(){
        return strides;
    }



    @Override
    public void run() {
        while (!Thread.interrupted()){
            synchronized (this){
                strides+=rand.nextInt(3);
            }
            try {
                barrier.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Horse "+id+" ";
    }

    public String tracks(){
        StringBuilder s=new StringBuilder();
        for(int i=0;i<getStrides();i++){
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }

}

