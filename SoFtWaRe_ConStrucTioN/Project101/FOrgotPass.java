import java.awt.*;
import javax.swing.*;
public class FOrgotPass {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1133, 744);
        frame.setTitle("โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("project.jpg"));
        frame.getContentPane().setBackground(Color.decode("#D8F5FF"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        // ปุ่ม Finish
        JButton FinishButton = new JButton("Finish");
        FinishButton.setBackground(Color.decode("#E5B6B3"));
        FinishButton.setForeground(Color.WHITE);
        FinishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        FinishButton.setBounds(511, 622, 110, 40);
        layeredPane.add(FinishButton, JLayeredPane.DEFAULT_LAYER);


        // สร้างPIN
        JLabel create1 = new JLabel("ชื่อ - นามสกุล");
        create1.setBounds(272, 180, 250, 100);
        create1.setFont(new Font("Tahoma", Font.BOLD, 24));
        create1.setForeground(Color.BLACK);
        layeredPane.add(create1, JLayeredPane.PALETTE_LAYER);

        JTextField create2 = new JTextField();
        create2.setFont(new Font("Tahoma", Font.BOLD, 24));
        create2.setBounds(272, 260, 600, 50);
        layeredPane.add(create2, JLayeredPane.PALETTE_LAYER);

        //ยืนยันPIN
        JLabel confirm1 = new JLabel("รหัสใหม่ 6 หลัก");
        confirm1.setBounds(272, 310, 200, 100);
        confirm1.setFont(new Font("Tahoma", Font.BOLD, 24));
        confirm1.setForeground(Color.BLACK);
        layeredPane.add(confirm1, JLayeredPane.PALETTE_LAYER);

        JTextField confirm2 = new JTextField();
        confirm2.setFont(new Font("Tahoma", Font.BOLD, 24));
        confirm2.setBounds(272, 390, 600, 50);
        layeredPane.add(confirm2, JLayeredPane.PALETTE_LAYER);

        FinishButton.addActionListener(e -> {
            frame.dispose();
            Login.main(null);
        });

        frame.add(layeredPane);
        frame.setVisible(true);

    }

    
}
