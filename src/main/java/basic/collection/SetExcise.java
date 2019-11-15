package basic.collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangjie
 * @date 2019-11-11
 * @time 16:05
 */
public class SetExcise {

    @Test
    public void testSimpleSet() {
        Set set = new HashSet<>();
        set.add("111");

        Object o = new Object();
        System.out.println(o.hashCode());
        Object o2 = o;
        System.out.println(o2.equals(o));

    }


}
