
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 public class Form3  extends JFrame implements KeyListener {    
    Container cp = this.getContentPane(); 
    JTextField tf1; JLabel l1; JLabel l2 ;int x; 
    public Form3(){
        this.setLayout(new FlowLayout());
        l1 = new JLabel("10");
        l2 = new JLabel("20");
        tf1 = new JTextField(20);
        tf1.addKeyListener(this);
        cp.add(l1);
        cp.add(tf1);
        cp.add(l2);
       pack(); //setSize(300,300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        }                                                                                                                                                                                                     

        public void keyTyped(KeyEvent e) {
             l1.setText((++x)+"s");
            }
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_DOWN)
            l2.setText("Down");
            else if(e.getKeyChar() == 'm')
            l2.setText("push m ");
            }
        
            
        public void keyReleased(KeyEvent e) {
                
            }
            
    }

    
    

