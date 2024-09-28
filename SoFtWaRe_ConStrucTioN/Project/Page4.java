import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Page4 {

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

        // รายการลดหย่อนภาษี : ครอบครัว
        JLabel family = new JLabel("รายการลดหย่อนภาษี : ครอบครัว");
        family.setFont(new Font("Tahoma", Font.BOLD, 32));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        // สถานะสมรส
        JLabel status = new JLabel("สถานะสมรส");
        status.setFont(new Font("Tahoma", Font.BOLD, 24));
        status.setBounds(75, 100, 1000, 50);
        layeredPane.add(status, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนส่วนบุคคล
        JLabel own = new JLabel("ลดหย่อนส่วนบุคคล");
        own.setFont(new Font("Tahoma", Font.BOLD, 24));
        own.setBounds(540, 100, 1000, 50);
        layeredPane.add(own, JLayeredPane.PALETTE_LAYER);

        // ล็อก ลดหย่อน 60000
        JLabel discount = new JLabel("  60,000");
        discount.setFont(new Font("Tahoma", Font.PLAIN, 24));
        discount.setBounds(540, 155, 300, 45);
        discount.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        discount.setBorder(border); // ตั้งกรอบให้ JLabel
        discount.setOpaque(true);
        layeredPane.add(discount, JLayeredPane.PALETTE_LAYER);

        // เลือกสถานะ
        String[] s1 = { "โสด", "หย่า", "คู่สมรสมีเงินได้", "คู่สมรสไม่มีเงินได้" };
        JComboBox<String> chosestatus = new JComboBox<>(s1);
        chosestatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        chosestatus.setBounds(75, 155, 300, 45);
        chosestatus.setBackground(Color.WHITE);
        chosestatus.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // // Custom ComboBox UI
        // chosestatus.setUI(new BasicComboBoxUI() {
        // @Override
        // protected JButton createArrowButton() {
        // JButton button = new JButton(new ImageIcon("path/to/arrow-icon.png"));
        // button.setContentAreaFilled(false);
        // button.setBorder(BorderFactory.createEmptyBorder());
        // return button;
        // }
        // });

        chosestatus.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(0, 10, 0, 0)); // Add left padding
                if (index == -1 && value == null) {
                    setText("กรุณาเลือกสถานะ");
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });

        chosestatus.setSelectedIndex(-1);

        layeredPane.add(chosestatus, JLayeredPane.PALETTE_LAYER);


        // ปุ่ม Home
        JButton button = new JButton("HOME");
        button.setBackground(Color.decode("#80C8CD"));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBounds(20, 20, 120, 50);
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
        button.setBorder(border1);
        button.addActionListener(e -> System.out.println("HOME button clicked"));
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        button1.setBorder(border1);
        button1.addActionListener(e -> System.out.println("BACK button clicked"));
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        button2.setBorder(border1);
        button2.addActionListener(e -> System.out.println("NEXT button clicked"));
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);
        frame.setVisible(true);
    }
}

class DisplayGraphics extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);

        Graphics2D g2d = (Graphics2D) g;
        // วาดเส้น
        g2d.setColor(Color.decode("#6390BA"));
        g2d.setStroke(new BasicStroke(7));
        g.drawLine(0, 90, 1133, 90);
    }
}
