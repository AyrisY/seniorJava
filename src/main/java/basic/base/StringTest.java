package basic.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yangjie
 * @date 2019-11-18
 * @time 15:34
 */
public class StringTest {

    @Test
    public void testIntern() {
        // new String，同时创建堆空间对象和常量池对象，空间地址不相同
        String s1 = new String("helloworld");

        // 字符串已存在，intern直接返回常量池对象
        String s2 = s1.intern();
        // 常量池对象
        String s3 = "helloworld";

        // false
        System.out.println(s1 == s2);
        // false
        System.out.println(s1 == s3);
        // true
        System.out.println(s2 == s3);
    }

    @Test
    public void testIntern1() {
        String str2 = new String("str") + new String("01");
        str2.intern();
        String str1 = "str01";

        // true , 字符串在常量池预先不存在，intern会创建对于原堆空间中字符串的引用，以后调用都是返回堆空间引用
        System.out.println(str2 == str1);


    }

    @Test
    public void testIntern2() {
        String str2 = new String("str") + new String("01");
        String str1 = "str01";
        str2.intern();

        // false, 字符串预先不存在，后重新再常量池空间建立对字符串的引用，和堆空间的地址不同
        System.out.println(str2 == str1);
    }

    @Test
    public void testAddOperate() {
        // + 操作符中间是通过StringBuilder实现的
        String s1 = null;
        String s2 = "2222";

        String s3 = s1 + s2;

        // "null2222"
        System.out.println(s3);
    }

    @Test
    public void testBasicType() {
        // [-128,127]使用了常量池技术
        Integer i1 = 11;
        Integer i2 = 11;
        Integer i3 = 0;
        Integer i4 = new Integer(11);
        Integer i5 = new Integer(11);
        Integer i6 = new Integer(0);

        // true
        System.out.println(i1 == i2);
        // false
        System.out.println(i1 == i4);
        // false
        System.out.println(i4 == i5);
        // true,i5+i6会进行拆箱操作，最终转化为基础类型的比较
        System.out.println(i4 == i5 + i6);
    }

    @Test
    public void testStringCompare() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "herry");
        Collections.sort(names);
        System.out.println(names);

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(names);

        Collections.sort(names,(o1,o2)->{
            return o2.compareTo(o1);
        });
        System.out.println(names);

        Collections.sort(names,Comparator.comparing(String::toString));
        System.out.println(names);

    }
}
