package basic.funcInterface;

import java.util.Optional;

/**
 * @author yangjie
 * @date 2019-11-22
 * @time 17:51
 */
public class OptionalTest {

    public static void main(String[] args) {
        Optional<String> optional = Optional.of("hello world");
        optional.ifPresent(s -> System.out.println(s));

        Optional<String> optional2 = Optional.empty();
        System.out.println(optional2.orElse("empty value"));

    }
}
