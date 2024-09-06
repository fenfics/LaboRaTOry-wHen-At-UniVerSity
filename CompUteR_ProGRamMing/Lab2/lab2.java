import java.util.Scanner;

public class lab2 {

    void exam1(){

        System.out.print("Input String 1 :");
        Scanner input = new Scanner(System.in);
        String s1 = input.nextLine();
        System.out.print("Input String 2 :");
        String s2= input.nextLine();
        
        String x,y;
        x=s1.toUpperCase();
        y=s2.toUpperCase();
        if (x.equals(y))
            System.out.println("The two string are equal");
        else
        System.out.println("The two string are not equal");
        input.close();
    }

    void exam2(){
        Scanner input = new Scanner(System.in);
        System.out.print("Input your E-mail : ");
        String st = input.nextLine();
        

        for( int i = st.length()-1 ; i > 0 ; i--){
            if (st.charAt(i)=='@') {
                System.out.print("Your Domian name is \"");
                    System.out.print(st.substring(i)+"\"");
                break;
            }
        }
        input.close();
    }

    void exam3(){
        Scanner input = new Scanner(System.in);
        float max = 0 ;
        String id_max="";
        boolean First_std = true;

        while (true){
        System.out.print("Std ID :");
        String id = input.nextLine();
        if (id.equals("Q")) break;

        System.out.print("Score =");
        Float scr= input.nextFloat();
        input.nextLine();

        if (First_std) {
            max = scr;
            id_max = id;
            First_std = false;
            }else{
                    if (scr>max) {
                    max=scr;
                    id_max = id;
                    }
                }
        }
        if (!First_std) 
            System.out.println("Maximum Score is "+max+" on "+id_max);
        input.close();
        }
    }

