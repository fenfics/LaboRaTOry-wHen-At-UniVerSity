import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input Number : ");
        //int x = Integer.parseInt(input.nextLine());
        int x = input.nextInt();
        System.out.print("Summation : ");
        System.out.println(fibonacci(x));
        input.close();
    }

        public static int fibonacci(int x){
            int fib;
            if (x > 2)
            fib = fibonacci(x-1) + fibonacci(x-2);
            else if (x == 2)
            fib = 1;
            else
            fib = 0;
            return fib;
            }
            
    }
