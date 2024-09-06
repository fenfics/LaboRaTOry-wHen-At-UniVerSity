package Lab7_2;

public class SavingsAccount extends BaseAccount {
    private double balance;
    private DebitCard card;

    public SavingsAccount(FixedSalary employee) {
        this.balance = 0;
        this.card = employee.getCard();
    }

 
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

  
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public DebitCard getCard() {
        return this.card;
    }
}