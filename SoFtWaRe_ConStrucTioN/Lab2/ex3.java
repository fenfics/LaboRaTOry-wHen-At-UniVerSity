import java.util.Scanner;

public class ex3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter sentence:");
        String s = input.nextLine();
       // String[] word = s.split("\\s+");
       // for(int i = 0 ; i< word.length;i++)
        String result = s.replaceAll("(?i)happy", "happy :)").replaceAll("(?i)sad", "sad :(");

        System.out.print(result);
        
        
    }
}
