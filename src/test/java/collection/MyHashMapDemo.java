package collection;

import org.junit.Test;

/**
 * @author yangjie
 * @date 2018/1/18
 * @time 下午3:34
 */
public class MyHashMapDemo {

    @Test
    public void hashDemoTest(){
        String key="mySingleName";
        int h;
        System.out.println(1<<4);
        System.out.println(Integer.toBinaryString(key.hashCode()));
        System.out.println("0000000000000000"+Integer.toBinaryString(key.hashCode() >>> 16));
        System.out.println(Integer.toBinaryString((h=key.hashCode())^h>>>16));
    }

}
