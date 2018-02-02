package annotation;

import basic.annotation.FirstAnnotation;
import basic.annotation.ParamAnnotation;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author yangjie
 * @date 2018/1/30
 * @time 下午2:41
 */
@FirstAnnotation(prefix = "class")
public class AnnotationTest {

    @FirstAnnotation(prefix = "field")
    public String name;

    /**
     * 设置字段名称并获取加上注解后的字段值
     * */
    @Test
    public void testGetFieldAnnotation() throws NoSuchFieldException {
        setName("jie");
        //getField获取字段名称要求必须是public类型
        Field field=AnnotationTest.class.getField("name");
        FirstAnnotation fan=field.getAnnotation(FirstAnnotation.class);
        System.out.println(fan.toString());
        System.out.println(fan.prefix());
        System.out.println(name);
    }

    @Test
    public void testMethod(){
        try {
            testGetMethodAnnotation("testParam");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 参数级的注解依赖在参数上，所以一定要有参数
     * */
    @FirstAnnotation(prefix = "method")
    public void testGetMethodAnnotation(@ParamAnnotation("param") String name) throws NoSuchMethodException {
        //获取方法上的注解
        Method method=AnnotationTest.class
                .getMethod("testGetMethodAnnotation",String.class);
        FirstAnnotation fan=method.getAnnotation(FirstAnnotation.class);
        System.out.println(fan.toString());
        System.out.println(fan.prefix());

        //获取参数上的注解
        Parameter[] parameters=method.getParameters();
        for(Parameter p:parameters){
            ParamAnnotation pan=p.getAnnotation(ParamAnnotation.class);
            System.out.println(pan.toString());
            System.out.println(pan.value());
        }
    }

    /**
     * 获取类上面的注解，打印实例化的注解类
     * */
    @Test
    public void testGetClassAnnotation(){
        if(AnnotationTest.class.isAnnotationPresent(FirstAnnotation.class)){
            FirstAnnotation fan=AnnotationTest.class.getAnnotation(FirstAnnotation.class);
            System.out.println(fan.toString());
            System.out.println(fan.prefix());
        }

    }

    public void setName(String name) {
        this.name = name;
    }
}
