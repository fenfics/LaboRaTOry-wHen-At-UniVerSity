import java.math.BigInteger;
import java.util.Scanner;

public class ch1{
    
   void exxam1(){
        System.out.print("Enter Number: ");
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();

        int i,j,k;
        int count=0;
        
         if (x%2 == 1){
            
         }


    
    }

    void exxam2(){
        Scanner input = new Scanner(System.in);
        System.out.print("Input Frist Number: ");
        String x = input.nextLine();
        System.out.print("Input Second Number: ");
        String y = input.nextLine();
        
        
        BigInteger bi1 = new BigInteger(x);
        BigInteger bi2 = new BigInteger(y);

        BigInteger sum = bi1.add(bi2);

        System.out.print("Summation = " +sum);

    }
    
    
}