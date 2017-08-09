package rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by yangjie on 2017/5/23.
 */
public class TestMqConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("localDevGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("Consumer");
        consumer.setVipChannelEnabled(false);

        consumer.subscribe("TopicTest1","TagA||TagB||TagC");

        consumer.subscribe("TopicTest2","*");

        consumer.subscribe("TopicTest3","TagC");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName()+" Receive New Messages: "+msgs.size());

                MessageExt msg=msgs.get(0);
                if(msg.getTopic().equals("TopicTest1")){
                    System.out.println("Topic="+msg.getTopic()+",Tag="+msg.getTags());
                    if(msg.getTags()!=null&&msg.getTags().equals("TagA")){
                        System.out.println(new String(msg.getBody()));
                    }else if(msg.getTags()!=null&&msg.getTags().equals("TagB")){
                        System.out.println(new String(msg.getBody()));
                    }else if(msg.getTags()!=null&&msg.getTags().equals("TagC")){
                        System.out.println(new String(msg.getBody()));
                    }

                }else if(msg.getTopic().equals("TopicTest2")){
                    System.out.println("Topic="+msg.getTopic()+",Tag="+msg.getTags());
                    System.out.println(new String(msg.getBody()));
                }else if(msg.getTopic().equals("TopicTest3")){
                    System.out.println("Topic="+msg.getTopic()+",Tag="+msg.getTags());
                    System.out.println(new String(msg.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Begin to consumer message...");
//        consumer.shutdown();
        System.out.println("consumer message over.");
    }

}
