import java.util.Scanner;

public class test{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter coordinate x = ");
        int x = input.nextInt();
        System.out.print("Enter coordinate y = ");
        int y = input.nextInt();

        if (x==0 && y==0) 
            System.out.println("coordinates ("+x+","+y+") = origin");
        if (y==0) 
            System.out.println("coordinates ("+x+","+y+") = x-intercept");
        if (x==0) 
            System.out.println("coordinates ("+x+","+y+") = y-intercept");
        if (x>0 && y>0) 
            System.out.println("coordinates ("+x+","+y+") = Q1");
        if (x<0 && y>0) 
            System.out.println("coordinates ("+x+","+y+") = Q2");
        if (x<0 && y<0) 
            System.out.println("coordinates ("+x+","+y+") = Q3");
        if (x>0 && y<0) 
            System.out.println("coordinates ("+x+","+y+") = Q4");

        input.close();

    }
}
