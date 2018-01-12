package thread.executer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangjie
 * @date 2017/12/25
 * @time 下午1:46
 */
public class EnhancedThreadPoolExecutor extends ThreadPoolExecutor{

    private final AtomicInteger submittedTaskCount=new AtomicInteger();

    public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, TaskQueue workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,new ThreadPoolExecutor.AbortPolicy());
        workQueue.setExecutor(this);
        System.out.println("core pool size:"+this.getCorePoolSize()+",max pool size:"+this.getMaximumPoolSize());
    }

    @Override
    public void execute(Runnable command) {
        submittedTaskCount.incrementAndGet();

        try {
            super.execute(command);

        } catch (RejectedExecutionException e) {
            BlockingQueue<Runnable> taskQueue= super.getQueue();
            if(taskQueue instanceof TaskQueue) {
                final TaskQueue queue = (TaskQueue) taskQueue;
                if (!queue.forceTaskIntoQueue(command)) {
                    this.shutdown();
                    submittedTaskCount.decrementAndGet();
                    throw new RejectedExecutionException("队列已满");
                } else {
                    submittedTaskCount.decrementAndGet();
                    throw e;
                }
            }
        } finally {
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r,t);
        submittedTaskCount.decrementAndGet();
        System.out.println("当前已提交任务数目："+this.getSubmittedTaskCount()+",当前线程ID："+Thread.currentThread().getId());
    }

    public int getSubmittedTaskCount(){
        return this.submittedTaskCount.get();
    }


}
