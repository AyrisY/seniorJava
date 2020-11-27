package basic.lambda;

public class SportExecise {

    public static void main(String[] args) {
        SportExecise sportExecise = new SportExecise();
        sportExecise.doSport("swimming",(name)->{
            System.out.println("start " + name + " sport!");
        });
    }

    public void doSport(String sportName, Sport sport) {
        sport.doSport(sportName);
    }
}
