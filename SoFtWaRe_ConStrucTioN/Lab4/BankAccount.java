public class BankAccount {
    private String name;
    private double money;
    private Date date;

    public BankAccount(String name){
        this.name = name;
        this.money = 0;
        date = new Date(17,7,2024);
    }

    public BankAccount(String name, double amount, Date date){
        this.name=name;
        this.money= amount;
        this.date=date;
    }

    public void deposit(double amount){
        if (amount > 0) {
            this.money += amount;
        } else {
            System.out.println("Deposit failed");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= this.money && amount > 0) {
            this.money -= amount;
            return true;
        } else {
            System.out.println("Withdraw failed");
            return false;
        }
    }

    public void transfer(BankAccount other, double amount){
        if (amount<=this.money) {
            this.withdraw(amount);
            other.deposit(amount);
            System.out.println("Transfer Completed");
        } else {
            System.out.println("Transfer failed");
        }
    }

    public String printMoney(){
        return "Account Name: " + this.name + " , Balance: " + this.money;
    }

    public String getInfo() {
        return "Account Name: " + this.name + " , Open Date: " + this.date;
    }

}
