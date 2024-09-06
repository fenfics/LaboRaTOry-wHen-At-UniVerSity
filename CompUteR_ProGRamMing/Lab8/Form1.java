import java.awt.*;
import javax.swing.*;
public class Form1 {
    
    public Form1(){

    JFrame f = new JFrame("Login");
    Container cp =f.getContentPane();
    cp.setLayout(null);

    JLabel L1 = new JLabel("USER :");
    L1.setBounds(30, 10, 50, 25);
    cp.add(L1);
    JTextField t1 = new JTextField();
    t1.setBounds(80, 10, 150, 20);
    cp.add(t1);

    JLabel L2 = new JLabel("Password :");
    L2.setBounds(10, 40, 70, 25);
    cp.add(L2);
    JTextField t2 = new JTextField();
    t2.setBounds(80, 40, 150, 20);
    cp.add(t2);

    JButton B1 = new JButton("Login");
    B1.setBounds(80, 80, 80, 25); 
    cp.add(B1);

    f.setSize(250, 250);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
