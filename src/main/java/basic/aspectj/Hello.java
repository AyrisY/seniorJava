package basic.aspectj;

/**
 * @author yangjie
 * @date 2018/2/9
 * @time 上午11:21
 */
public class Hello {
    public void sayHello(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Hello hello=new Hello();
        hello.sayHello();
    }
}
