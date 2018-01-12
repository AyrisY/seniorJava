package thread;

public class RunnableDemo {

    public static void main(String[] args) {
        AbRunnable abRunnable=new AbRunnable();
        abRunnable.run();   //直接在主线程执行
        Thread thread=new Thread(abRunnable);
        thread.start();     //新建一个子线程执行
        new Runnable(){
            @Override
            public void run(){
                System.out.println("匿名内部类执行");
            }
        }.run();
    }

}

class AbRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Current Thread Name:"+Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
