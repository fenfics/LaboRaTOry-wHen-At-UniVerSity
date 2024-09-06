import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Form2 {
    public Form2(){
    JFrame f = new JFrame("Vehicle registation");
    Container cp =f.getContentPane();
    cp.setLayout(null);
    
    JLabel L1 = new JLabel("Name :");
    L1.setBounds(39, 10, 50, 25);
    cp.add(L1);
    JTextField t1 = new JTextField();
    t1.setBounds(80, 10, 150, 20);
    cp.add(t1);

    JLabel L2 = new JLabel("Car license :");
    L2.setBounds(7, 30, 100, 25);
    cp.add(L2);
    JTextField pf = new JTextField();
    pf.setBounds(80, 35, 100, 20);
    cp.add(pf);

    JLabel L3 = new JLabel("Province :");
    L3.setBounds(20, 60, 80, 20);
    cp.add(L3);
    JComboBox<String> c = new JComboBox<>();
    c.setBounds(80, 60, 100, 20);
    cp.add(c);
   
    

    try {
        File F = new File("Thailand_PRV.csv");
        FileReader fr = new FileReader(F);
        BufferedReader br =new BufferedReader(fr);
        String s;
        br.readLine();//อ่านบรรทัดแรกทิ้งไปก่อน
        while ((s=br.readLine()) != null) {
            String arr[] = s.split(",");
            c.addItem(arr[1]);
           
        }
        c.setSelectedItem("Bangkok");
        br.close(); fr.close();

    } catch (Exception e) {
       System.out.println(e);
    }




    JLabel L4 = new JLabel("Car Type :");
    L4.setBounds(20, 90, 70, 25);
    cp.add(L4);
    JRadioButton rb1 = new JRadioButton("Car",true);
    rb1.setBounds(80, 80, 80, 50);
    cp.add(rb1);
    JRadioButton rb2 = new JRadioButton("Bike",false);
    rb2.setBounds(160, 80, 80, 50);
    cp.add(rb2);


    JButton B1 = new JButton("OK");
    B1.setBounds(85, 140, 70, 35); 
    cp.add(B1);
   

    f.setSize(250, 230);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
