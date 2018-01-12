package thread.executer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author yangjie
 * @date 2017/12/25
 * @time 下午2:03
 */
public class TaskQueue extends LinkedBlockingQueue<Runnable>{

    private EnhancedThreadPoolExecutor executor;

    public TaskQueue(int capacity) {
        super(capacity);
    }

    public void setExecutor(EnhancedThreadPoolExecutor executor){
        this.executor=executor;
    }

    public boolean forceTaskIntoQueue(Runnable o){
        if(executor.isShutdown()){
            throw new RejectedExecutionException("Executor已经关闭了，不能添加任务到队列里面");
        }

        return super.offer(o);
    }

    @Override
    public boolean offer(Runnable o) {
        int currentPoolThreadSize=executor.getPoolSize();

        if(currentPoolThreadSize==executor.getMaximumPoolSize()){
            return super.offer(o);
        }

        if(executor.getSubmittedTaskCount()<currentPoolThreadSize){
            return super.offer(o);
        }

        if(currentPoolThreadSize<executor.getMaximumPoolSize()){
            return false;
        }

        return super.offer(o);
    }

}
