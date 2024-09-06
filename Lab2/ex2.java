import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
        System.out.print("Enter started day: ");
        int number = input.nextInt();
        String week[] = {"Su","Mo","Tu","We","Th","Fr","Sa"};

        for(int d = 0; d<7;d++)
        System.out.print(week[d]+"\t");

        System.out.println();
        for(int s = 0; s<number-1 ;s++){
            System.out.print("\t");
        }
        for(int day = 1 ; day<=31 ; day++){
            System.out.print(day + "\t");
            if ((((number - 1)+day)%7)==0) {
                System.out.println();
                }
           
            }
            System.out.println("\n");  
        }       
    }
}
