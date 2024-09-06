import java.util.ArrayList;
import java.util.Scanner;

public class Data{
    private Scanner input;
    private ArrayList<String> arr;

    public Data(){
        arr = new ArrayList<>();
        input=new Scanner(System.in);
    }
    
    public void Menu(){
        System.out.println("=====MENU=====");
        System.out.println("1)Add text to back");
        System.out.println("2)Add text at index");
        System.out.println("3)Edit text");
        System.out.println("4)Remove text by index");
        System.out.println("5)Remove text by value");
        System.out.println("6)Exit");
        
    }
    
    public void Case1(){
       // try {
        System.out.println("==Add text to back==");
        System.out.print("Input text :");
        String text = input.nextLine();
        arr.add(text);
      //  }
       // catch (Exception e) {
       //     System.out.println(e);
        //}
        this.ShowDate();  
    }

    public void ShowDate(){
        System.out.print("Data = ");
        for(String st : arr)
            System.out.print(st+" ");
        System.out.println("\n");
    }

    public void Case2(){
       // try{
        System.out.println("==Add text at index==");
        System.out.print("Input index :");
        int index = Integer.parseInt(input.nextLine());
        System.out.print("Input text :");
        String text = input.nextLine();
        arr.add(index, text);
       // }
       // catch (Exception e) {
       //     System.out.println(e);
      //  }
        this.ShowDate();
    }

    public void Case3(){
       // try{
        System.out.println("==Edit text==");
        System.out.print("Input index :");
        int index = Integer.parseInt(input.nextLine());
        System.out.print("Input text :");
        String text = input.nextLine();
        arr.remove(index);
        arr.add(index, text);
       // }
        //catch (Exception e) {
        //    System.out.println(e);
        //}
        this.ShowDate();

    }

    public void Case4(){
        System.out.println("==Remove text by index==");
        System.out.print("Input index :");
        int index = Integer.parseInt(input.nextLine());
        arr.remove(index);
        this.ShowDate();
    }

    public void Case5(){
        System.out.println("==Remove text by value==");
        System.out.print("Input text :");
        String text = input.nextLine();
        arr.remove(text);
        this.ShowDate();
    }

    

}
