package aspectj;

import basic.aspectj.ExceptionHandle;
import basic.aspectj.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LogAspectTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
        Hello hello = (Hello) context.getBean("hello");
        hello.sayHello();

        ExceptionHandle exceptionHandle = (ExceptionHandle) context.getBean("exceptionHandle");
        exceptionHandle.throwException();
    }
}
