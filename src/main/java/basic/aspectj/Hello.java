package basic.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author yangjie
 * @date 2018/2/9
 * @time 上午11:21
 */
@Component
public class Hello {

    public void sayHello(){
        System.out.println("hello world");
    }
}
