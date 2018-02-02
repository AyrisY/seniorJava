package schedule;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;

/**
 * @author yangjie
 * @date 2018/1/1
 * @time 下午1:56
 */
public class IScheduleTest {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-config.xml");

        TBScheduleManagerFactory factory=(TBScheduleManagerFactory)context.getBean("scheduleManagerFactory");
        try {
            factory.init();

            Iterator iterator= factory.getZkConfig().entrySet().iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testStart(){
        Thread thread=new Thread(()->System.out.println("test start"));

        thread.start();
    }



}
