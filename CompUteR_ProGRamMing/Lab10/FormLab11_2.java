import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FormLab11_2 extends JFrame implements ActionListener{

    Container cp ;
    JButton left,right,up,down,box ;
    int x,y;
    
    public FormLab11_2(){
        Initial();
        setComponent();
        Finally();
    }
    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
    }
    public void setComponent(){
        x=0; y=0;
        left = new JButton("A");
        right = new JButton("D");
        up = new JButton("W");
        down = new JButton("S");
        box = new JButton();
        box.setBackground(Color.black);
        box.setBounds(x, y, 50, 50);

        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);

        left.setBounds(20, 225, 50, 40);
        down.setBounds(70, 225, 50, 40);
        right.setBounds(120, 225, 50, 40);
        up.setBounds(70, 185, 50, 40);

        cp.add(left);cp.add(right);
        cp.add(up);cp.add(down);
        cp.add(box);
    }
    public void Finally(){
        this.setSize(200, 300);
        this.setVisible(true);
        this.setResizable(false);//ปรับขนาดเฟรมไม่ได้
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==right && x+5<=135) {
        box.setBounds(x=x+5, y, 50, 50);
       }else if (e.getSource()==left && x-5>=0) {
        box.setBounds(x=x-5, y, 50, 50);
       }else if (e.getSource()==up&& y-5>=0) {
        box.setBounds(x, y=y-5, 50, 50);
       }else if (e.getSource()==down && y+5<=135) {
        box.setBounds(x,y=y+5, 50, 50);
       }
    }


    
}
