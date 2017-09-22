package java8.stream;

import org.junit.Test;

import java8.funcInterface.Apple;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by yangjie on 2017/8/3.
 */
public class TestSimpleStream {

    @Test
    public void filterTest(){
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

        System.out.println("原始未简化版");
        list.stream().filter((Integer i)->i%2==0)
                .distinct()
                .forEach((Integer i)->System.out.print(i+","));
        System.out.println();

        System.out.println("格式简化版");
        list.stream().filter(i->i%2==0)
                .distinct()
                .forEach(System.out::print);

        System.out.println();
        System.out.println("limit关键字使用");
        list.stream().filter(i->i%2==0)
                .distinct()
                .limit(3)
                .forEach(i->System.out.print(i+","));

        System.out.println();
        System.out.println("collect关键字使用");
        List<Integer> subList=list.stream().filter(i->i%2==0)
                .distinct()
                .limit(2)
                .collect(toList());
        subList.stream().forEach(i->System.out.print(i+","));

        System.out.println();
        System.out.println("skip关键字使用");
        list.stream().filter(i->i%2==0)
                .distinct()
                .skip(3)
                .forEach(i->System.out.print(i+","));

        System.out.println();
        System.out.println("map关键字使用");
        List<Apple> apps=Arrays.asList(new Apple("red",130),new Apple("blue",80));
        List<String> colors=apps.stream().map(Apple::getColor)
                .collect(toList());
        colors.stream().forEach(i->System.out.print(i+","));

        System.out.println();
        List weights=apps.stream().map(Apple::getWeight)
                .collect(toList());
        weights.stream().forEach(i->System.out.print(i+","));

        System.out.println();
        System.out.println("map关键字错误使用");
        List<String> words=Arrays.asList("Hello","World");
        words.stream().map(word->word.split(""))
                .distinct()
                .forEach(i->System.out.print(i));

        System.out.println();
        List subWords=words.stream().map(word->word.split(""))
                .distinct()
                .collect(toList());

        System.out.println(subWords.getClass().getName());
        for(int i=0;i<subWords.size();i++){
            System.out.println(subWords.get(i).getClass().getName());

            for(int j=0;j<Array.getLength(subWords.get(i));j++){
                System.out.print(Array.get(subWords.get(i),j)+",");
            }
            System.out.println();

        }

        System.out.println("flatMap关键字正确使用");
        words.stream().map(word->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(i->System.out.print(i));

    }

}
