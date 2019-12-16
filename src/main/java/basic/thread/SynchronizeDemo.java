package basic.thread;

public class SynchronizeDemo {

    public void printA() {

        synchronized (this) {
            System.out.println("print AAA");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printEnd();
        }
    }

    public synchronized void printB() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("printB interruped,thread=" + Thread.currentThread().getName());
        }
        System.out.println("print BBB");
    }

    public synchronized void printEnd() {
        System.out.println("end line--------");
    }

    public static void main(String[] args) {
        SynchronizeDemo demo = new SynchronizeDemo();
        demo.printA();

        Thread threadA = new Thread(() -> {
            demo.printA();
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            demo.printB();
        });
        threadB.start();
        threadB.interrupt();
    }

}
