package basic.funcInterface;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author yangjie
 * @date 2019-11-22
 * @time 16:30
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("aaa"));
        Predicate predicate1 = predicate.negate();
        System.out.println(predicate1.test("bbb"));

        Predicate<Object> predicate2 = Objects::nonNull;
        System.out.println(predicate2.test(null));

        Predicate<Boolean> predicate3 = Objects::isNull;
        System.out.println(predicate3.test(null));

        Predicate<String> predicate4=String::isEmpty;
        System.out.println(predicate4.test(""));

    }
}
