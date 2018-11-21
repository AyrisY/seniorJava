package annotation;

import basic.annotation.MyAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yangjie
 * @date 2018/11/21
 * @time 上午11:32
 */
@MyAnnotation(value = "class annotation", number = 6, numArr = {3, 4, 5})
public class MyAnnotationTest {

    @MyAnnotation("field annotation")
    public String season;

    @MyAnnotation(value = "method annotation")
    public void printName() {
        System.out.println("print name method");
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        MyAnnotation classAnnotation = MyAnnotationTest.class.getAnnotation(MyAnnotation.class);
        printAnnotation(classAnnotation);

        Method printNameMethod = MyAnnotationTest.class.getMethod("printName");
        MyAnnotation methodAnnotation = printNameMethod.getAnnotation(MyAnnotation.class);
        printAnnotation(methodAnnotation);

        //需要定义field为public
        Field seasonField = MyAnnotationTest.class.getField("season");
        MyAnnotation fieldAnnotation = seasonField.getAnnotation(MyAnnotation.class);
        printAnnotation(fieldAnnotation);

    }

    public static void printAnnotation(MyAnnotation annotation) {
        System.out.println(annotation);
        System.out.println(annotation.value());
        System.out.println(annotation.number());
        //数组打印地址
        Arrays.stream(annotation.numArr()).forEach(x -> {
            System.out.println(x);
        });
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
