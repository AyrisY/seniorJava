package basic.aspectj;

import org.springframework.stereotype.Component;

@Component
public class ExceptionHandle {

    public void throwException(){
        int temp=1/0;
    }
}
