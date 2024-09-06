import java.util.Scanner;

public class ex1{
public static void main(String[] args) {
    
    System.out.println("Input distance(km): ");
    Scanner input = new Scanner(System.in);
    float x = input.nextFloat();

    float y,z;
    if (x<=4) 
    System.out.println("Delivery Fee = Free");
    if (x<=10){
        z=(x-4)*10;
    System.out.println("Delivery Fee = "+z+" Bath");
    }
    
    if(x>10){
        z=(x-10)*12+60;
        System.out.println("Delivery Fee = "+z+" Bath");
    }
    
}


}
