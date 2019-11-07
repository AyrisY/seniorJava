package basic.base;

import static java.util.Objects.hash;

/**
 * @author yangjie
 * @date 2019-04-28
 * @time 11:33
 */
public class BitProcess {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1<<3));
        System.out.println(Integer.toBinaryString(-1>>3));
        System.out.println(-1<<3);
        System.out.println(-1>>3);

        System.out.println("------------------------------");

        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(1<<3));
        System.out.println(Integer.toBinaryString(1>>3));
        System.out.println(1<<3);
        System.out.println(1>>3);

        System.out.println("-----------------------------");

        System.out.println(1<<29);
        System.out.println(2<<29);
        System.out.println((-1<<29)|0);

        String hkey = "name";
        System.out.println(hash(hkey));
        System.out.println(Integer.toBinaryString(hash(hkey)));
        System.out.println(Integer.toBinaryString(15));
        System.out.println(hash(hkey)&15);
    }

}
