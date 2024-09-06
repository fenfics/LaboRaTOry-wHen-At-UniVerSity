package lab8;

public class SavingsAccount extends BaseAccount {
    private double balance;
    private DebitCard card;
    private Employees employee;

    public SavingsAccount(FixedSalary employee) {
        super(); 
        this.card = new DebitCard(employee);
        this.balance = 0;
        this.employee = employee;
        employee.savingsAccount = this;
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
        return card;
    }

    public class DebitCard extends Card { // Inner class
        private FixedSalary employee;

        public SavingsAccount getSavingsAccount() {
            return SavingsAccount.this;
        }

        public DebitCard(FixedSalary employee) {
            this.employee = employee;
        }

        public String type() {
            return "visa";
        }

        public double discount() {
            return 2.5; // 2.5% discount
        }

        public boolean withdraw(double amount) {
            return SavingsAccount.this.withdraw(amount);
        }

        public String getCardNumber() {
            return "xxx-xxx-" + employee.getSecurityNumber().substring(employee.getSecurityNumber().length() - 4);
        }
    }
}