import java.awt.*;
import javax.swing.*;

public class Salary {
    private static JTextField sala2;
    private static JTextField bonus2;
    private static JTextField free2;
    public static void main(String[] args) {
        Salary sr = new Salary();
        sr.FrameSalary();
    }

    public void FrameSalary() {
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

        // ปุ่ม Home
        JButton button = new JButton("HOME");
        button.setBackground(Color.decode("#80C8CD"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBounds(20, 20, 120, 50);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // กล่องเงินเดือน
        JLabel sala1 = new JLabel(" เงินเดือน(บาท)");
        sala1.setBounds(272, 100, 200, 100);
        sala1.setFont(new Font("Tahoma", Font.BOLD, 24));
        sala1.setForeground(Color.BLACK);
        layeredPane.add(sala1, JLayeredPane.PALETTE_LAYER);

        JTextField sala2 = new JTextField();
        sala2.setFont(new Font("Tahoma", Font.BOLD, 28));
        sala2.setBounds(272, 180, 600, 50);
        layeredPane.add(sala2, JLayeredPane.PALETTE_LAYER);

        // กล่องโบนัส(บาท)
        JLabel bonus1 = new JLabel(" โบนัส(บาท)");
        bonus1.setBounds(272, 220, 200, 100);
        bonus1.setFont(new Font("Tahoma", Font.BOLD, 24));
        bonus1.setForeground(Color.BLACK);
        layeredPane.add(bonus1, JLayeredPane.PALETTE_LAYER);

        JTextField bonus2 = new JTextField();
        bonus2.setFont(new Font("Tahoma", Font.BOLD, 28));
        bonus2.setBounds(272, 300, 600, 50);
        layeredPane.add(bonus2, JLayeredPane.PALETTE_LAYER);

        // กล่องรายได้อื่นๆ
        JLabel free1 = new JLabel(" รายได้อื่นๆ เช่น การขายของออนไลน์, ฟรีแลนซ์ (บาท)");
        free1.setBounds(272, 340, 700, 100);
        free1.setFont(new Font("Tahoma", Font.BOLD, 24));
        free1.setForeground(Color.BLACK);
        layeredPane.add(free1, JLayeredPane.PALETTE_LAYER);

        JTextField free2 = new JTextField();
        free2.setFont(new Font("Tahoma", Font.BOLD, 28));
        free2.setBounds(272, 420, 600, 50);
        layeredPane.add(free2, JLayeredPane.PALETTE_LAYER);

        // กล่องปี
        JLabel year1 = new JLabel(" ปี (พ.ศ.)");
        year1.setBounds(272, 460, 700, 100);
        year1.setFont(new Font("Tahoma", Font.BOLD, 24));
        year1.setForeground(Color.BLACK);
        layeredPane.add(year1, JLayeredPane.PALETTE_LAYER);

        JTextField year2 = new JTextField();
        year2.setFont(new Font("Tahoma", Font.BOLD, 28));
        year2.setBounds(272, 540, 600, 50);
        layeredPane.add(year2, JLayeredPane.PALETTE_LAYER);

        button.addActionListener(e -> {
            frame.dispose();
            Home.main(null);
        });

        button2.addActionListener(e -> {
            frame.dispose();
            Family.main(null);
        });

        button1.addActionListener(e -> {
            frame.dispose();
            Home.main(null);
        });

        frame.add(layeredPane);
        frame.setVisible(true);

    }

    public void calsalary() {
        int salarymont = Integer.parseInt(sala2.getText()); //เงินเดือน
        int bonutmont = Integer.parseInt(bonus2.getText()); //โบนัสเดือน
        int freemont = Integer.parseInt(free2.getText()); //รายได้อื่นๆ

        int salayYear = salarymont*12;
        int salaryYearwithoutpay = salayYear*((50/100)*salayYear);

        if (salaryYearwithoutpay>100000) {
            salayYear -=100000;
        }else{
            salayYear-=(50/100)*salayYear;
        }

        System.out.println(salayYear);



    }

    // เส้น
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

}
