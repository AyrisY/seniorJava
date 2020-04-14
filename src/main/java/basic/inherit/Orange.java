package basic.inherit;

public class Orange extends Fruit{

    public Orange() {
        System.out.println("orange construct.");
    }

    @Override
    public void sayHello() {
        System.out.println("orange sayHello.");
    }
}
