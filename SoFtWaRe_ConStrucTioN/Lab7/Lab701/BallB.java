package Lab7;

public class BallB extends BallA {

    public BallB(String ball) {
        super(ball);
    }

    public void inflate(double a) {
        System.out.println(getTreadMark() + "'s ball is inflated " + a + " cu.ft.");
    }

    public String toString() {
        return (getTreadMark() + " is a trademark of " + getClass().getSimpleName());
    }

    public void roll() {
        System.out.println(getTreadMark() + " rolls smoothly.");
    }

}
