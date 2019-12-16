package basic.thread;

/**
 * @author yangjie
 * @date 2019-11-20
 * @time 10:53
 */
public class JoinDemo extends Thread {
    int i;
    Thread preThread;

    public JoinDemo(Thread preThread, int i) {
        this.i = i;
        this.preThread = preThread;
    }

    @Override
    public void run() {
        try {
            preThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread " + i + " finish");
    }

    public static void main(String[] args) {
        // 只有join阻塞的线程执行完，后续才能继续执行
        // 使用主线程作为起始阻塞，主线程执行完后，被阻塞的子线程依次执行
        // 好比是main,1,2,3,4,5,6,7,8,9,10,排队，第一个main排完了，后续才能继续依次排队通过
        Thread preThread = Thread.currentThread();
        System.out.println(preThread.getName() + "---------");
        for (int i = 0; i < 10; i++) {
            JoinDemo joinDemo = new JoinDemo(preThread, i + 1);
            joinDemo.start();
            preThread = joinDemo;
        }
        System.out.println("main endLine---------");
    }

}
