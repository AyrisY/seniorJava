package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MessageService;

/**
 * @author yangjie
 * @date 2019-05-09
 * @time 16:39
 */
public class AppTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-beantest.xml");

        System.out.println("context 启动成功");

        MessageService messageService = (MessageService) context.getBean("messageServiceImpl");
        System.out.println(messageService.message());
    }

}
