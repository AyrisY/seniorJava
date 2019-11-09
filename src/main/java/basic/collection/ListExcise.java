package basic.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author yangjie
 * @date 2019-11-08
 * @time 18:02
 */
public class ListExcise {

    @Test
    public void testIsSorted() {
        List sortList = new ArrayList<>();
        sortList.add(18);
        sortList.add(7);
        sortList.add(99);
        sortList.add(18);

        for (Object object : sortList) {
            System.out.println(object);
        }

        Set set = new HashSet<>();
        set.add(18);
        set.add(7);
        set.add(99);
        set.add(18);
        for (Object o : set) {
            System.out.println(o);
        }

    }

    @Test
    public void testToArray() {
        List copyList = new ArrayList();
        copyList.add("aaa");
        copyList.add("bbb");
        copyList.add("ccc");

        String[] arrayList = new String[5];
        arrayList[3] = "dddd";
        arrayList[4] = "eeee";
        copyList.toArray(arrayList);
        for (int i = 0; i < arrayList.length; i++) {
            System.out.println(arrayList[i]);
        }

        // 位运算以2的指数倍变化
        // 左进位数字变大
        System.out.println(10 << 1);
        // 2的5次幂，32
        System.out.println(10 << 5);
        // 右进位数字变小，
        System.out.println(10 >> 1);

        Object[] objArr = new Object[5];
        System.out.println(objArr.getClass());
        System.out.println(Object[].class);
    }

    @Test
    public void testModCount() {
        List tempList = new ArrayList();
        tempList.add("aaa");
        tempList.add("bbb");
        tempList.add("ccc");
        tempList.add("ddd");
        tempList.add("eee");

        Iterator iterator = tempList.iterator();
        while (iterator.hasNext()) {
            String ss = (String) iterator.next();

            System.out.println(ss);
//            tempList.remove(ss);
            // 刚好删除这次元素，size变为4，当前游标也是4，下次就不会执行，也就不会输出eee
            if ("ddd".equalsIgnoreCase(ss)) {
                tempList.remove(ss);
            }
        }

    }

}
