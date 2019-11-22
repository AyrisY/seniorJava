package basic.funcInterface;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangjie
 * @date 2019-11-22
 * @time 18:00
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> ssList = Arrays.asList("bbb", "aaa", "acc", "ddd");

        System.out.println("----------test print--------------------");
        ssList.stream().forEach(System.out::println);

        System.out.println("----------test filter-------------------");
        ssList.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

        System.out.println("----------test sorted-------------------");
        ssList.stream().sorted().forEach(System.out::println);

    }

}
