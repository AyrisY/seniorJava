package basic.lock;

/**
 * @author yangjie
 * @date 2019-11-12
 * @time 17:56
 */
public class ObjectLock {


    public synchronized void print1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("print1---" + i);
        }
    }

    public synchronized void print2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("print2---" + i);
        }
    }

    public static void main(String[] args) {
        final ObjectLock objectLock = new ObjectLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.print1();
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.print2();
            }
        });

        thread1.start();
        thread2.start();

    }

}
