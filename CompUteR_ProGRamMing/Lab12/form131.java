import javax.swing.*;
import java.awt.*;

public class form131 extends JFrame{
    
    public form131(){
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new Draw1(),BorderLayout.CENTER);
        setSize(300,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}

class Draw1 extends JPanel{
    public void paintComponent(Graphics g){
        g.drawString("Just keep smiling", getWidth()/2-50,60 );
        g.setColor(Color.pink);
        g.fillOval(getWidth()/2-50, getHeight()/2-50, 100, 100);
        g.setColor(Color.black);
        g.drawOval(getWidth()/2-50, getHeight()/2-50, 100, 100);
        g.drawArc(getWidth()/2-38,getHeight()/2-45,75 , 75, 220, 100);
        g.fillOval(getWidth()/2-25, getHeight()/2-25, 15, 15);
        g.fillOval(getWidth()/2+10, getHeight()/2-25, 15, 15);

    }
}
