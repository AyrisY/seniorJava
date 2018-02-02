package basic.funcInterface;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by yangjie on 2017/8/1.
 */
public class TestFunctionInterface {


    @Test
    public void DefineFunctionInterface(){
        //自定义函数式接口
        AppleInterface at=()->System.out.println("define FunctionInterface AppleInterface.");
        at.test();
    }

    @Test
    public void FunctionTest(){
        Function<String,Apple> function=(String s)->{return new Apple(s,666);};
        Apple app=function.apply("red");
        System.out.println(app.getColor()+","+app.getWeight());
        app=function.apply("green");
        System.out.println(app.getColor()+","+app.getWeight());

    }

    @Test
    public void SupplierTest(){
        Supplier<Apple> supplier=()->{return new Apple("hello supplier",999);};
        Apple app=supplier.get();
        System.out.println(app.getColor()+","+app.getWeight());
    }

    @Test
    public void ConsumerTest(){
        Consumer<Apple> consumer=(Apple app)->{System.out.println(app.getColor()+","+app.getWeight());};
        List<Apple> apps=Arrays.asList(new Apple("red", 120),new Apple("blue", 80),
                new Apple("green",100));
        ConsumerApple(apps,consumer);
    }

    public void ConsumerApple(List<Apple> apps,Consumer<Apple> c){
        for(Apple app:apps){
            c.accept(app);
        }
    }

    @Test
    public void PredicateTest(){
        //系统预定义函数式接口测试
        Predicate<Apple> p1=(Apple a)->{if(a.getWeight()>90) return true;return false;};
        Predicate<Apple> p2=(Apple a)->{if(a.getColor().equals("blue")) return true;return false;};

        List<Apple> apps=Arrays.asList(new Apple("red", 120),new Apple("blue", 80),
                new Apple("green",100));

        filterApple(apps,p1);//筛选重量大于90g的苹果
        filterApple(apps,p2);//筛选蓝色的苹果
    }

    public void filterApple(List<Apple> apps,Predicate<Apple> p){
        for(Apple app:apps){
            if(p.test(app)){
                System.out.println(app.getColor()+","+app.getWeight());
            }
        }

    }

}
