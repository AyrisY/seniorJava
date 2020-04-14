package basic.inherit;

public class Fruit {

    public Fruit() {
        System.out.println("fruit construct.");
    }

    public void sayHello() {
        System.out.println("fruit sayHello.");
    }

    public static void main(String[] args) {
        System.out.println("---------init-------");
        Apple apple = new Apple();
        Orange orange = new Orange();
        System.out.println("---------sayHello---");
        apple.sayHello();
        System.out.println("---------override------");
        Fruit fruit = apple;
        fruit.sayHello();
        fruit = orange;
        fruit.sayHello();
    }
}
