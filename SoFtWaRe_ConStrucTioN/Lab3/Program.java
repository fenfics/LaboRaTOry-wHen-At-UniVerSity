import java.util.Scanner;

public class Program{
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 =null;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Book name: ");
        book1.name =input.nextLine();
        System.out.print("Enter Book price: ");
        book1.price= input.nextInt();
       
        System.out.println(book2.name);
        /*System.out.println();
        book1.show(); */
        
        
        
    
    }
}
