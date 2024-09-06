import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class form132 extends JFrame{
    
    public form132(){
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new Animation1(),BorderLayout.CENTER);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

class Animation1 extends JPanel implements ActionListener{
    int r; int count; boolean growth;
    public Animation1(){
        r=20; count=0;  growth=true;
        Timer t = new Timer(100, this);
        t.start();
    }
   
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.pink);
        g.fillOval(getWidth()/2-r, getHeight()/2-r, 2*r, 2*r);
        
    }

   
    public void actionPerformed(ActionEvent e) {
        if (growth) {
        r+=5;
        count++;
        if (count==10) growth=false;
        } else {
            r-=5;
            count--;
            if (count==0) growth=true;
        }
        repaint();
    }
}

