package basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author  yangjie
 * @date    2017/11/20
 * @time    上午11:12
 */

public class FutureTaskDemo {

    public static void main(String[] args) {
        ACallable aCallable=new ACallable();
        FutureTask futureTask=new FutureTask(aCallable);

        Thread thread=new Thread(futureTask);
        thread.start();

        int count=0;
        do{
            //while wait ,do something
            count++;
        }while(!futureTask.isDone());
        System.out.println("Current-Thread-Name-"+count+":"+Thread.currentThread().getName());

        try {
            //after wait ,the task have finished,get and print it
            String result=(String)futureTask.get();
            System.out.println(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class ACallable implements Callable<String>{

    @Override
    public String call() throws Exception {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Current-Thread-Name:"+Thread.currentThread().getName());
        return "Result:"+Thread.currentThread().getName();
    }
}
