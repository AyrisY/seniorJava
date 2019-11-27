package basic.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
public class LogService {

    @Before("execution(* basic.aspectj.Hello.*(..))")
    public void startLog() {
        System.out.println("日志切面开始");
    }

    @After("execution(* basic.aspectj.Hello.*(..))")
    public void endLog() {
        System.out.println("日志切面结束");
    }

    @Around("execution(* basic.aspectj.Hello.*(..))")
    public void aroundLog(ProceedingJoinPoint proceedingJoinPoint) {
        Long startTime = System.nanoTime();
        System.out.println("开始时间：" + LocalDateTime.now());
        try {
            Object object = proceedingJoinPoint.proceed();
            Thread.sleep(1000);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("结束时间：" + LocalDateTime.now());

        Long endTime = System.nanoTime();
        System.out.println("执行时间：" + (endTime - startTime));
    }

}
