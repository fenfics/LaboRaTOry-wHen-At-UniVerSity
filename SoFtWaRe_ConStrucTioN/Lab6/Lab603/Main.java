public class Main{
    
    public static void main(String[] args) {
        
        Employees fixedSalary = new FixedSalary("Clark", "Kent","555-999-5555", 15000.00);
        Employees hourlySalary = new HourlySalary("Bruce", "Wayne","555-777-1111", 20.00, 165);
        Employees commissionPay = new CommissionPay("Peter", "Parker","555-111-5555", 95000, 0.06);
        java.util.ArrayList<Employees> employees = new java.util.ArrayList<Employees>();//สร้างอาเรย์ได้สองแบบ
        employees.add(fixedSalary);
        employees.add(hourlySalary);
        employees.add(commissionPay);
       /*Employee[]employees = new Employee[3] ;
         employees[0] = fixedSalary;
         employees[1] = hourlySalary;
         employees[2] = commissionPay; */
        for (Employees emp : employees) {
        System.out.println(emp);
        System.out.printf("earned $%.2f\n\n", emp.earnings());
        }
    }
}
