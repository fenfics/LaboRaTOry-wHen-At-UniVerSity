import java.util.Scanner;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        Main.exam1();
    }

    public static void exam1(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please input csv file name : ");
        String name = input.nextLine();
        File f = new File(name + ".csv");//เวลารับข้อมูลจะใส่นามสกุลต่อท้ายเป็นไฟล์ของเรา
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            System.out.print("Please input string : ");
            String search = input.nextLine();
            String s;
            while ((s=br.readLine()) != null ) {
                if(s.contains(search))//เอสมีเสิร์จอยู่หรือป่าว
                System.out.println(s);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        finally{
            try {
                 br.close(); fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        input.close();
    }


    public static void exam2(){
        Scanner input = new Scanner(System.in);
        File f = new File("Text1.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            int i = 1;

            while (true) {
                System.out.print("Please input name : ");
                String s = input.nextLine();
                
                bw.write("Name ["+i+"] : "+s+"\n");
                i++;
                if (s.equals("Q")) break;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        finally{
            try {
                bw.close();fw.close(); input.close();
            } catch (Exception e) {
               System.out.println(e);
            }
        }
    }
}
