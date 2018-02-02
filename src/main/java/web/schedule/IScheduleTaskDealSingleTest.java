package web.schedule;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vo.TaskModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author yangjie
 * @date 2018/1/1
 * @time 下午1:14
 */
@Component("iScheduleTaskDealSingleTest")
public class IScheduleTaskDealSingleTest implements IScheduleTaskDealSingle<TaskModel>{

    private static final org.slf4j.Logger LOG= LoggerFactory.getLogger(IScheduleTaskDealSingle.class);

    @Override
    public boolean execute(TaskModel model, String s) throws Exception {
        LOG.info("IScheduleTaskDealSingleTest执行开始了...");
        System.out.println(model);
        return true;
    }

    @Override
    public List<TaskModel> selectTasks(String taskParameter, String ownSign,
                                    int taskQueueNum, List<TaskItemDefine> taskItemList, int eachFetchDataNum) throws Exception {
        LOG.info("IScheduleTaskDealSingleTest配置的参数：");
        LOG.info("taskParameter:"+taskParameter);
        LOG.info("ownSign:"+ownSign);
        LOG.info("taskQueueNum:"+taskQueueNum);
        LOG.info("taskItemList:"+taskItemList);
        LOG.info("eachFetchDataNum:"+eachFetchDataNum);
        LOG.info("IScheduleTaskDealSingleTest选择任务列表开始了...");
        List<TaskModel> models=new ArrayList<TaskModel>();
        models.add(new TaskModel(String.valueOf(System.currentTimeMillis()),"taskJob1"));
        models.add(new TaskModel(String.valueOf(System.currentTimeMillis()),"taskJob2"));

        return models;
    }

    @Override
    public Comparator<TaskModel> getComparator() {
        return null;
    }

}
