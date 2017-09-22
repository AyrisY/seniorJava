package java8.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by yangjie on 2017/9/12.
 */
public class BaseKeyWordTest {
    private List<Person> pl;

    {
        pl=new ArrayList<Person>();
        pl.add(new Person("Bran",7,"boy",155));
        pl.add(new Person("Alian",28,"man",188));
        pl.add(new Person("Moniok",22,"girl",175));
        pl.add(new Person("Flora",33,"woman",170));

    }

    /**
     *  流元素的筛选和切片
     *  filter,limit,skip关键字使用
     *  该关键字返回一个列表,返回部分元素T的全部字段
     * */
    @Test
    public void filterAndLimit(){
        System.out.println("---------filter关键字筛选");
        pl.stream().filter(p->p.getAge()>20).
                forEach(p->System.out.println(p.toString()));

        System.out.println("---------System.out打印简写");
        pl.stream().filter(p->p.getName().equals("Flora"))
                .forEach(System.out::println);

        System.out.println("---------collect关键字,生成子列表");
        List<Person> subPl = pl.stream().filter(p->p.getHeight()>170)
                .limit(2)
                .collect(toList());
        subPl.stream().forEach(System.out::println);

        System.out.println("---------limit关键字使用");
        pl.stream().filter(p->p.getHeight()>170)
                .limit(1)
                .forEach(System.out::println);

        System.out.println("---------skip关键字使用");
        pl.stream().filter(p->p.getHeight()>170)
                .skip(1)
                .forEach(System.out::println);


    }

    /**
     * sorted关键字
     * 返回一个列表
     * */
    @Test
    public void sortedResult(){
        System.out.println("---------按照年龄排序");
        List<Person> spl = pl.stream().sorted(comparing(Person::getAge)).collect(toList());
        spl.stream().forEach(p->System.out.println(p));

        System.out.println("---------按照身高排序");
        spl = pl.stream().sorted(comparing(Person::getHeight)).collect(toList());
        spl.stream().forEach(p->System.out.println(p));
    }


    /**
     * map
     * flatmap
     * 该关键字返回一个列表,返回全部元素T的部分字段结果集
     * */
    @Test
    public void mapResult(){
        System.out.println("-----------map选择属性列");
        List<String> pnl = pl.stream().map(p->p.getName())
                .collect(Collectors.toList());
        pnl.stream().forEach(System.out::println);

        System.out.println("----------单词去重复字母,错误示例");
        List<String[]> psl= pnl.stream().map(s->s.split(""))
                .distinct()
                .collect(toList());

        psl.stream().forEach(sa->{
            Arrays.asList(sa).stream().forEach(System.out::print);
        });

        System.out.println("\n----------单词去重复字母,正确示例---flatmap");
        List<String> pcl=psl.stream().flatMap(sa->Arrays.stream(sa))
                .distinct()
                .collect(toList());
        pcl.stream().forEach(System.out::print);

    }


    /**
     * anyMatch
     * allMatch
     * nonoMatch
     * 该关键字返回一个布尔值
     * */
    @Test
    public void  matchResut(){
        if(pl.stream().anyMatch(p->p.getHeight()>170)){
            System.out.println("anyMatch--至少匹配到一个身高大于170");
        }else{
            System.out.println("anyMatch--没有匹配到一个身高大于170");
        }

        if(pl.stream().anyMatch(p->p.getHeight()>190)){
            System.out.println("anyMatch--至少匹配到一个身高大于190");
        }else{
            System.out.println("anyMatch--没有匹配到一个身高大于190");
        }

        if(pl.stream().allMatch(p->p.getHeight()>150)){
            System.out.println("allMatch--全部身高大于150");
        }else{
            System.out.println("allMatch--至少有一个身高不大于150");
        }

        if(pl.stream().allMatch(p->p.getHeight()>170)){
            System.out.println("allMatch--全部身高大于170");
        }else{
            System.out.println("allMatch--至少有一个身高不大于170");
        }

        if(pl.stream().noneMatch(p->p.getHeight()>200)){
            System.out.println("noneMatch--没有一个身高大于200");
        }else{
            System.out.println("noneMatch--至少有一个身高大于200");
        }

        if(pl.stream().noneMatch(p->p.getHeight()>170)){
            System.out.println("noneMatch--没有一个身高大于170");
        }else{
            System.out.println("noneMatch--至少有一个身高大于170");
        }

    }

    /**
     * findAny
     * findFirtst
     * 返回一个包含元素T的容器Optional,根据容器的接口来进行下一步判断
     * */
    @Test
    public void findResult(){
        Optional<Person> optional=pl.stream().filter(p->p.getHeight()>170).findAny();
        if(optional.isPresent()){
            optional.ifPresent(p-> System.out.println(p.getName()+","+p.getAge()));
        }

        Person person = optional.get();
        System.out.println(person.getName()+","+person.getHeight());

        Optional<Person> optional2=pl.stream().filter(p->p.getHeight()>200).findAny();
        if(optional2.isPresent()){
            optional2.ifPresent(p-> System.out.println(p.getName()+","+p.getAge()));
        }else{
            System.out.println("未找到身高超过200的人");
        }

    }

    /**
     * reduce
     * 归约关键字,返回一个结果值
     * */
    @Test
    public void reduceResult(){
        System.out.println("---------求和");
        List<Integer> numList=Arrays.asList(1,2,3,4,5);
        Integer Sa=numList.stream().reduce(0,(a,b)->a+b);
        System.out.println("sum="+Sa);

        numList=Arrays.asList(1,2,3,4,5,6);
        System.out.println(numList.stream().reduce(0,Integer::sum));

        System.out.println("---------计算最大值和最小值");
        System.out.println(numList.stream().reduce(Integer::min).get());
        System.out.println(numList.stream().reduce(0,Integer::min));
        System.out.println(numList.stream().reduce(Integer::max).get());
        System.out.println(numList.stream().reduce(0,Integer::max));

        //计算元素总个数的两种表达形式
        System.out.println("---------计算元素总个数的两种表达形式");
        System.out.println(numList.stream().map(t->1).reduce(0,Integer::sum));
        System.out.println(numList.stream().map(t->1).reduce(Integer::sum).get());


    }

    @Test
    public void numStreamResult(){
        System.out.println(pl.stream().mapToInt(p->p.getAge()).sum());
        System.out.println(pl.stream().mapToDouble(p->p.getAge()).sum());
        System.out.println(pl.stream().mapToLong(p->p.getAge()).sum());

        System.out.println("------------数值流的装箱拆箱操作");
        IntStream intStream=pl.stream().mapToInt(p->p.getAge());
        Stream<Integer> stream=intStream.boxed();
        stream.forEach(System.out::println);

        System.out.println("-----------数值流求出最大值和最小值");
        OptionalInt optionalInt = pl.stream().mapToInt(p->p.getAge()).max();
        if(optionalInt.isPresent()){
            System.out.println(optionalInt.getAsInt());
        }

        System.out.println("-----------生成随机数值流,range和rangeClosed的区别");
        IntStream intStream1=IntStream.range(1,100).filter(x->x%2==0);
        System.out.println(intStream1.count());
        IntStream intStream2=IntStream.rangeClosed(1,100).filter(x->x%2==0);
        System.out.println(intStream2.count());

    }


    @Test
    public void createStream(){
        System.out.println("------------创建流");
        System.out.println("------------1.直接传值创建流");
        Stream<String> stringStream=Stream.of("111","222","333");
        System.out.println(stringStream.count());

        System.out.println("------------2.数组创建流");
        int[] num={1,2,3,4,5};
        IntStream intStream=Arrays.stream(num);
        System.out.println(intStream.count());

        System.out.println("------------3.文件创建流");
        try {
            long uniqueWords=0;
            Stream<String> lines= Files.lines(Paths.get("src/main/java/java8/stream/word.txt"), Charset.defaultCharset());
            uniqueWords=lines.flatMap(line->Arrays.stream(line.split(","))).distinct().count();

            System.out.println("uniqueWords="+uniqueWords);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------4.函数生成流");
        Stream.iterate(0,n->n+2).limit(5).forEach(System.out::print);
        System.out.println();
        Stream.iterate(1,n->n*3).limit(5).forEach(System.out::print);

    }

}
