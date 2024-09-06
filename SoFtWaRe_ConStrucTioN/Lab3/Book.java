
public class Book {
  
    public String name ;
    public String auther = new String("J.K. Rowling");
    public int year = 2542;
    public int price;
    public void show() {
        System.out.print("Book: " + name + "\n" + "Written by " + auther + " in " + year+"\n"+"Price is "+price+" Bath");
    }
}
