import Lib.*;

public class Main {

    public static void main(String[] args) {
      /*   MyDate x = new MyDate();
        x.setDate(16, 8, 2005);
        System.out.println(x.toString());
        x.setMonth(20);*/

        /*People A = new People();
        A.setName("David", "Beckham");
        A.setBirthdate(2, 5, 1975);
        System.out.println(A.toString());*/

        Employee J = new Employee();
        Employee K = new Employee();
        J.setName("Leonel", "messi");
        J.setBirthdate(24, 6, 1987);
        J.setCompany("Argentina FC");
        J.setSalary(15000);
        K.setName("Cristiano", "Ronaldo");
        K.setBirthdate(5, 5, 1985);
        K.setCompany("Portugal FC");
        K.setSalary(19000);

        if (J.Taxpay()) J.Show();
        if (K.Taxpay()) K.Show();
    }
}
