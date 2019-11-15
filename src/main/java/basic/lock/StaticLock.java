package basic.lock;

/**
 * @author yangjie
 * @date 2019-11-12
 * @time 17:56
 */
public class StaticLock {

    public synchronized static void print1() {
        for (int i = 0; i < 5; i++) {
            System.out.println("print1-----" + i);
            try {
                Thread.sleep(1000);

                if (i == 3) {
                    StaticLock.class.wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void print2() {
        for (int i = 0; i < 5; i++) {
            System.out.println("print2-----" + i);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        StaticLock.class.notify();

    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                StaticLock.print1();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                StaticLock.print2();
            }
        });

        thread1.start();
        thread2.start();

    }

}
