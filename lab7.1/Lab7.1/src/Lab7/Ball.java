package Lab7;

public abstract class Ball {
    private String treadMark;

    public Ball(String ball) {
        this.treadMark = ball;
    }

    public String getTreadMark() {
        return this.treadMark;
    }

    public abstract void inflate(double volume); // abstract method

    public String toString() {
        return (this.treadMark + " is a trademark of " + getClass().getSimpleName());
    }

    public abstract void roll();

}
