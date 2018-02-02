package lock;

import basic.lock.RedisLock;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author yangjie
 * @date 2018/1/15
 * @time 下午2:46
 */
public class RedisLockTest {


    /**
     * 这个方法用来模拟未做并发控制下的秒杀情况
     * */
    @Test
    public void SimpleSecKillTest() {

        //使用这种函数式的传入，虽然也有竞争，但是比较小，直接run模拟竞争更好
        IntStream.rangeClosed(1,1000).parallel().forEach(
                (i)-> new Thread(()-> Product.apple--).run()
        );

        //测试的几个结果如下：
        //9891-8684，9558-7254,9895-8997

        //这种对于锁的竞争比上面的严重，差别非常大，
        IntStream.rangeClosed(1,1000).parallel().forEach(
                (i)->{
                    new Runnable(){
                        @Override
                        public void run(){
                            Product.box--;
                        }
                    }.run();
                }
        );

        System.out.println("now,apple="+Product.apple);
        System.out.println("now,box="+Product.box);
    }


    /**
     * 这个方法用来模拟使用redis锁来控制秒杀的进行的
     * 锁设置了超时时间，在超时时间内处理完业务并删除锁，并发控制没有问题
     * 下一步模拟业务处理时间超过了Redis锁超时时间，导致锁自动失效，锁被其他线程拿到，同时有两个线程参与到共享资源的争夺中
     * 会造成并发的问题
     * */
    @Test
    public void SecKillTestWithLock() {

        Random random=new Random();
        IntStream.rangeClosed(1,300).parallel().forEach(
                (i)->{
                    RedisLock lock=new RedisLock();
                    new Thread(()->{
                        //线程内不断循环尝试获取锁
                        while(true){
                            if(lock.setLock("apple","1",10)){
                                Product.apple--;
                                lock.delLock("apple");
                                break;
                            }else{
                                try {
                                    Thread.currentThread().sleep(random.nextInt(2000));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).run();
                }
        );

        IntStream.rangeClosed(1,300).parallel().forEach(
                (i)-> {
                    new Runnable() {
                        @Override
                        public void run() {
                            RedisLock lock=new RedisLock();
                            //线程内不断循环尝试获取锁
                            while(true){
                                if (lock.setLock("box", "1", 1)) {
                                    Product.box--;
                                    lock.delLock("box");
                                    break;
                                }else{
                                    try {
                                        Thread.currentThread().sleep(random.nextInt(1000));
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }.run();
                }
        );

        System.out.println("now,apple="+Product.apple);
        System.out.println("now,box="+Product.box);

    }

    /**
     * 该方法模拟业务处理时间超过锁超时时间的场景，并进行控制的方法
     * */
    @Test
    public void lockExpandExpireTime(){

    }

}

//模拟商品总数
class Product{
    public static int apple=100;
    public static int box=100;
}
