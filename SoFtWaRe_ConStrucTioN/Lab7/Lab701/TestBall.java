package Lab7;

public class TestBall {

    public static void main(String[] args) {
        Ball a = new BallA("Zentia");
        Ball b = new BallB("Zapphire");
        Ball c = new BallC("Zenith");

        TestBall(a, 1.0);
        TestBall(b, 1.1);
        TestBall(c, 1.2);

    }

    public static void TestBall(Ball e, double d) {
        System.out.println(e.toString());
        e.inflate(d);
        e.roll();
    }
}
