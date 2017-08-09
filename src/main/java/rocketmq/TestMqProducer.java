package rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangjie on 2017/5/23.
 */
public class TestMqProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {

        DefaultMQProducer producer=new DefaultMQProducer("localDevGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("Producer");
        producer.setVipChannelEnabled(false);

        producer.start();

        try {
            {
                Message msg= new Message("TopicTest1","TagA","OrderID001","First RocketMQ Message".getBytes());
                SendResult sendResult=producer.send(msg);
                System.out.println(sendResult);
            }

            {
                Message msg= new Message("TopicTest2","TagB","OrderID002","Second RocketMQ Message".getBytes());
                SendResult sendResult=producer.send(msg);
                System.out.println(sendResult);
            }

            {
                Message msg= new Message("TopicTest3","TagC","OrderID003","Third RocketMQ Message".getBytes());
                SendResult sendResult=producer.send(msg);
                System.out.println(sendResult);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        producer.shutdown();

    }

}
