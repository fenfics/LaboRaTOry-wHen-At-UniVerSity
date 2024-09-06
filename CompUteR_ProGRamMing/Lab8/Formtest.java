import java.awt.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;
public class Formtest{
    public class Form3 extends JFrame implements MouseListener {
        Container cp ;JTextArea ta ;JButton BT ;
        public Form3(){
     
        ta = new JTextArea(5,30);
        ta.setLineWrap(true);
        BT = new JButton("OK");
        
        BT.addMouseListener(this);
        ta.addMouseListener(this);
        
        cp.add(ta,BorderLayout.NORTH);
        cp.add(BT,BorderLayout.SOUTH);
        public void mouseClicked(MouseEvent e) {
    ta.setText(ta.getText()+" Click");
    if(e.getSource()==BT)
    System.out.println("hello");
    }
    public void mousePressed(MouseEvent e) {
    ta.setText(ta.getText()+" Press");
    }
    public void mouseReleased(MouseEvent e) {
    ta.setText(ta.getText()+" Releas");
    }
    public void mouseEntered(MouseEvent e) {
    ta.setText(ta.getText()+" IN");
    }
    public void mouseExited(MouseEvent e) {
    ta.setText(ta.getText()+" OUT");
    }
}

