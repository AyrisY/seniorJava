package web.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author yangjie
 * Created 2017/5/23.
 */
public class TestMqConsumer {

    private static String TOPIC_TEST1="TopicTest1";
    private static String TOPIC_TEST2="TopicTest2";
    private static String TOPIC_TEST3="TopicTest3";

    private static String TAG_A="TagA";
    private static String TAG_B="TagB";
    private static String TAG_C="TagC";


    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("localDevGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("Consumer");
        consumer.setVipChannelEnabled(false);

        consumer.subscribe(TOPIC_TEST1,TAG_A+TAG_B+TAG_C);

        consumer.subscribe(TOPIC_TEST2,"*");

        consumer.subscribe(TOPIC_TEST3,TAG_C);


        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName()+" Receive New Messages: "+msgs.size());

                MessageExt msg=msgs.get(0);
                if(TOPIC_TEST1.equals(msg.getTopic())){
                    System.out.println("Topic="+msg.getTopic()+",Tag="+msg.getTags());
                    if(msg.getTags()!=null&&TAG_A.equals(msg.getTags())){
                        System.out.println(new String(msg.getBody()));
                    }else if(msg.getTags()!=null&&TAG_B.equals(msg.getTags())){
                        System.out.println(new String(msg.getBody()));
                    }else if(msg.getTags()!=null&&TAG_C.equals(msg.getTags())){
                        System.out.println(new String(msg.getBody()));
                    }

                }else if(TOPIC_TEST2.equals(msg.getTopic())){
                    System.out.println("Topic="+msg.getTopic()+",Tag="+msg.getTags());
                    System.out.println(new String(msg.getBody()));
                }else if(TOPIC_TEST3.equals(msg.getTopic())){
                    System.out.println("Topic="+msg.getTopic()+",Tag="+msg.getTags());
                    System.out.println(new String(msg.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("Begin to consumer message...");
        consumer.shutdown();
        System.out.println("consumer message over.");

    }

}
