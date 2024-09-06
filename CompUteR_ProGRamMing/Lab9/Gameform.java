import javax.swing.*;
import java.awt.*;


public class Gameform extends JFrame {
    
    public Gameform(){
        super("Checkers");
        ImageIcon img = new ImageIcon("./tt.jpg");
        this.setIconImage(img.getImage());
        Container cp = this.getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel gamepanel = creategame();
        cp.add(gamepanel,BorderLayout.EAST);

        JPanel gametable = creategametable();
        cp.add(gametable,BorderLayout.CENTER);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    public JPanel creategametable(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(8,8));
        JButton bt[][]=new JButton[8][8];
        
        for(int i = 0;i<8;i++){
            for(int j = 0; j<8;j++){
                bt[i][j] = new JButton();
                bt[i][j].setPreferredSize(new Dimension(30, 30));
                if((i+j) % 2 == 0)
                bt[i][j].setBackground(Color.pink);
                else
                bt[i][j].setBackground(Color.white);
                p.add(bt[i][j]);
            }
        }
        
        return p ;
    }

    public JPanel creategame(){
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        JLabel l1= new JLabel("Game Panel.");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 100;
        c.ipady = 20;
        p.add(l1,c);


        JLabel l2= new JLabel("Time passed : 00.00");
        c.gridy = 1;
        c.ipadx = 100;
        c.ipady = 25;
        p.add(l2,c);

        JButton b = new JButton("START");
        c.gridy = 2;
        c.ipadx = 15 ;
        c.ipady = 10 ;
        p.add(b,c);

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setVerticalAlignment(JLabel.BOTTOM);
        l2.setHorizontalAlignment(JLabel.CENTER);
        b.setHorizontalAlignment(JButton.CENTER);

        return p;
    }


}
