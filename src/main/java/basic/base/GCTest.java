package basic.base;

/**
 * @author yangjie
 * @date 2019-11-18
 * @time 18:14
 */
public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900*1024];
        allocation2 = new byte[1800*1024];
    }
}
