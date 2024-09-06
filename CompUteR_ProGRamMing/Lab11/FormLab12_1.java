import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FormLab12_1 extends JFrame implements KeyListener{

    Container cp ;
    JLabel name , id ;
    JTextField t1 ,t2 ;
    JButton b1,b2 ;
    public FormLab12_1(){
        Initial();
        setComponent();
        Finally();
    }
    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
    }
    public void setComponent(){
        id = new JLabel("ID : ");
        name = new JLabel("Name : ");
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        b1 = new JButton("Add");
        b2 = new JButton("Clear");  
        
        t1.addKeyListener(this);
        t2.addKeyListener(this);
        id.setBounds(32, 10, 60, 25);
        name.setBounds(10, 40, 60, 25);
        t1.setBounds(60, 10, 120, 25);
        t2.setBounds(60, 40, 180, 25);
        b1.setBounds(30, 80, 70, 25);
        b2.setBounds(120, 80, 70, 25);
        
        cp.add(id); cp.add(t1);
        cp.add(name); cp.add(t2);
        cp.add(b1) ; cp.add(b2);
    }
    public void Finally(){
        this.setSize(270, 160);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
   
    public void keyTyped(KeyEvent e) {
        if(e.getSource()==t1){
            if(!(Character.isDigit(e.getKeyChar()))){
               e.consume();
            }

        }else if(e.getSource()==t2){
            if(!Character.isAlphabetic(e.getKeyChar())&& e.getKeyChar()!=KeyEvent.VK_SPACE){
                e.consume();
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {
        
    }
    
    public void keyReleased(KeyEvent e) {
       
    }

}
