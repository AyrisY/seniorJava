package basic.inherit;

public class Apple extends Fruit{

    public Apple() {
        System.out.println("Apple construct.");
    }

    @Override
    public void sayHello() {
        System.out.println("Apple sayHello.");
    }

}
