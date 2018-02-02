package basic.annotation;

import java.lang.annotation.*;

/**
 * @author yangjie
 * @date 2018/1/30
 * @time 下午4:41
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamAnnotation {
    String value() default "";
}
