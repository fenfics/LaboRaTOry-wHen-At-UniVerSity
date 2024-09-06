import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        
        System.out.println("Input your number :");
        Scanner input = new Scanner(System.in);
        int x=input.nextInt();

        int y ;
        System.out.print("Your Encyption Code = ");
        while (x!=0) {
           y=x%10;
           if(y%2==0)
           y++;
           else
           y--;
        System.out.print(y);
            x=x/10;
        }
    }
}
