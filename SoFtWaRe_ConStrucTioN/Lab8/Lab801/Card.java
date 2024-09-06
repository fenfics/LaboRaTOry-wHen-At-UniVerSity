package lab8;

public abstract class Card implements Withdrawable {

    public abstract String type();

    public abstract double discount();

    public boolean withdraw(double amount) {

        return false;
    }

}
