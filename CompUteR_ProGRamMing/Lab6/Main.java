import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Data D = new Data();
    D.Menu();

        while (true) {
           try {
                System.out.print("Select==> ");
                int n = Integer.parseInt(input.nextLine());

                switch (n) {
                    case 1:
                        D.Case1();
                        break;
                    case 2:
                        D.Case2();
                        break;
                    case 3:
                        D.Case3();
                        break;
                    case 4:
                        D.Case4();
                        break;
                    case 5:
                        D.Case5();
                        break;
                    case 6:
                    System.out.println("Bye!!!");
                        return ;
                        
                    default:
                    System.out.println("Wrong input!!!");
                        break;
                }
               } catch (Exception e) {
               System.out.println(e);
               D.ShowDate();
           }
        }
    }




    public static void exam1(){

        while (true) {
            try {
            System.out.print("Input Number : ");
            Scanner input = new Scanner(System.in);
            int n = Integer.parseInt(input.nextLine());

            int arr[][]= new int[n][];//สร้างอาเรย์
            for(int i=0;i<arr.length;i++)
                arr[i] = new int[n--];

            int value=1;

            for(int i=0;i<arr.length;i++){//ไอมากกว่า0 แต่น้อยกว่าความยาว
                for(int j=0;j<arr[i].length;j++){//เจมากกว่า0 แต่น้อยกว่าไอ
                    arr[i][j]=value++;//เอาแวลูแทนค่าก่อนแล้วค่อยบวก
                }
            }

            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();//ครบ 1 แถวแล้วเอ็นเทอร์
            }
                break;//out of loop try

                } catch (Exception e) {
                System.out.println(e);//printoutexception e
            }
        }
        
    }

}
