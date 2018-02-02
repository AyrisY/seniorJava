package basic.annotation;

import java.lang.annotation.*;

/**
 * @author yangjie
 * @date 2018/1/30
 * @time 下午2:33
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.LOCAL_VARIABLE,ElementType.FIELD})
public @interface FirstAnnotation {

    String prefix() default "prefix";

}
