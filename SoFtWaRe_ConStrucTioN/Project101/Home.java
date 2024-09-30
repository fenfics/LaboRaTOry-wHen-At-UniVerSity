import java.awt.*;
import javax.swing.*;

public class Home {
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

        // Home
        JLabel home = new JLabel("HOME");
        home.setBounds(483, 1, 200, 100);
        home.setFont(new Font("Tahoma", Font.BOLD, 42));
        home.setForeground(Color.BLACK);
        layeredPane.add(home, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม คำนวณภาษี
        JButton button5 = new JButton("คำนวณภาษี");
        button5.setFont(new Font("Tahoma", Font.BOLD, 15));
        button5.setBackground(Color.decode("#E5B6B3"));
        button5.setForeground(Color.WHITE);
        button5.setBounds(483, 222, 120, 50);
        layeredPane.add(button5, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม ประวัติการคำนวณภาษี
        JButton button6 = new JButton("ประวัติการคำนวณภาษี");
        button6.setFont(new Font("Tahoma", Font.BOLD, 15));
        button6.setBackground(Color.decode("#80C8CD"));
        button6.setForeground(Color.WHITE);
        button6.setBounds(435, 302, 220, 50);
        layeredPane.add(button6, JLayeredPane.PALETTE_LAYER);

        // ปุ่มกราฟการเปรียบเทียบภาษ๊
        JButton button7 = new JButton("กราฟการเปรียบเทียบภาษี");
        button7.setFont(new Font("Tahoma", Font.BOLD, 15));
        button7.setBackground(Color.decode("#80C8CD"));
        button7.setForeground(Color.WHITE);
        button7.setBounds(435, 382, 220, 50);
        layeredPane.add(button7, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม รายการลดหย่อน
        JButton button8 = new JButton("รายการลดหย่อน");
        button8.setFont(new Font("Tahoma", Font.BOLD, 15));
        button8.setBackground(Color.decode("#80C8CD"));
        button8.setForeground(Color.WHITE);
        button8.setBounds(455, 462, 180, 50);
        layeredPane.add(button8, JLayeredPane.PALETTE_LAYER);

        button5.addActionListener(e -> {
            frame.dispose();
            Salary.main(null);
        });

        frame.add(layeredPane);
        frame.setVisible(true);
    }
}

class DisplayGraphics extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode("#6390BA"));
        g2d.setStroke(new BasicStroke(7));
        g.drawLine(0, 90, 1133, 90);
    }
}