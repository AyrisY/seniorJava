package thread;


public class ThreadDemo {

    public static void main(String[] args) {
        AbThread abThread=new AbThread();
        abThread.start();
    }
}


class AbThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("Current Thread Name:"+Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}