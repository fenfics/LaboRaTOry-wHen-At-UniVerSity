public class Test {
    public static void main(String[] args) {

    BankAccount b1 = new BankAccount("Preaw",0,new Date(17,7,2024));
    BankAccount b2 = new BankAccount("Dear",500,new Date(1, 11, 2015));
    b1.deposit(1000);
    b2.transfer(b1,300);
    b2.withdraw(300);
    System.out.println(b1.printMoney());
    System.out.println(b2.printMoney());
    
    }
}
