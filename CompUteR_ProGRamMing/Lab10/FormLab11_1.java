import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class FormLab11_1 extends JFrame implements ActionListener {

    JTextField user;
    JPasswordField pass;
    public FormLab11_1(){
        super("Login");
        Container cp = this.getContentPane();
        cp.setLayout(null);
        JLabel L1 = new JLabel("USER : ");
        JLabel L2 = new JLabel("Password : ");
        user = new JTextField();
        pass = new JPasswordField();
        JButton b = new JButton("Login");
        b.addActionListener(this);
        L1.setBounds(34,10,46,25);
        L2.setBounds(10,40,70,25);
        user.setBounds(80, 10, 120, 25);
        pass.setBounds(80, 40, 120, 25);
        b.setBounds(80, 70, 70, 30);
        cp.add(L1);cp.add(L2);cp.add(b);cp.add(user);cp.add(pass);
        this.setSize(225, 150);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    
    public void actionPerformed(ActionEvent e) {
        String st = user.getText();
        String stpass = String.valueOf(pass.getPassword());

        if (st.equals("")||stpass.equals("")) {
            Popup("Plz input Username and Password.");
        }
        else{
            try {
                File f = new File("./user_table.csv");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String s=br.readLine();

                boolean correct = false;

                while((s = br.readLine()) != null){
                    String arr[] = s.split(",");
                    if (st.equals(arr[0])&&stpass.equals(arr[1])) {
                        correct = true;
                        break;
                    }
                    }
                    if (correct) {
                        Popup("Login Complete!!!");
                        this.setVisible(false);
                    }else Popup("Username or password Incorrect.");
                    
                    br.close(); fr.close();
                
            } catch (Exception Q) {
                System.out.println(Q);
            }
        }
    }

    public void Popup(String s){//here!!!
        JDialog d = new JDialog();
        d.getContentPane().setLayout(new FlowLayout());
        d.getContentPane().add(new JLabel(s));//L1สร้างในนี้เลย
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);

        }
    
}
