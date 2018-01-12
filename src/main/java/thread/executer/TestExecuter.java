package thread.executer;

import java.util.concurrent.TimeUnit;

/**
 * @author yangjie
 * @date 2017/12/25
 * @time 下午2:59
 */
public class TestExecuter {

    private static final int CORE_SIZE=5;

    private static final int MAX_SIZE=10;

    private static final long KEEP_ALIVE_TIME=30;

    private static final int QUEUE_SIZE=5;

    static EnhancedThreadPoolExecutor executor=
            new EnhancedThreadPoolExecutor(CORE_SIZE,MAX_SIZE,KEEP_ALIVE_TIME, TimeUnit.SECONDS,new TaskQueue(QUEUE_SIZE));


    public static void main(String[] args) {
        for(int i=0;i<15;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("线程池中现在的线程数目是："+executor.getPoolSize()
                    +",队列中正在执行的任务数量是："+executor.getQueue().size()
                    +"，现在的提交的任务数："+executor.getSubmittedTaskCount());
        }

        System.out.println("睡眠前现在提交的任务数："+executor.getSubmittedTaskCount());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡眠后现在提交的任务数："+executor.getSubmittedTaskCount());

        executor.shutdown();

    }

}
