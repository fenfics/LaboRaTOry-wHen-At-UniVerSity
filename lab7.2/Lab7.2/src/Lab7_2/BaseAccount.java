package Lab7_2;

public abstract class BaseAccount implements Withdrawable {
    public abstract boolean deposit(double amount);

    public boolean withdraw(double amount) {

        return false;
    }

}
