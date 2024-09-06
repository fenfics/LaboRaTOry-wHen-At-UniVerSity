package Lab7_2;

public class FixedSalary extends Employees {
    private double salary;
    private DebitCard card;

    public FixedSalary(String firstName, String lastName, String securityNumber, double salary) {
        super(firstName, lastName, securityNumber);
        this.salary = salary;
        this.card = new DebitCard(this);
    }

    
    public double earnings() {
        return this.salary;
    }

    public DebitCard getCard() {
        return this.card;
    }
}
