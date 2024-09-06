package Lab7_2;

public class DebitCard extends Card {
    private FixedSalary employee;

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
        return true;
    }

    public String getCardNumber() {
        return "xxx-xxx-" + employee.getSecurityNumber().substring(employee.getSecurityNumber().length() - 4);
    }
}
