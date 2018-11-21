package basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangjie
 * @date 2018/11/21
 * @time 上午11:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
public @interface MyAnnotation {

    String value() default "no name";

    int number() default 1;

    int[] numArr() default {1,2,3};

}
