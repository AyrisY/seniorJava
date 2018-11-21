package schedule;

import org.junit.Test;

/**
 * @author yangjie
 * @date 2018/1/1
 * @time 下午1:56
 */
public class IScheduleTest {

    public static void main(String[] args) {


    }

    @Test
    public void testStart(){
        Thread thread=new Thread(()->System.out.println("test start"));

        thread.start();
    }



}
