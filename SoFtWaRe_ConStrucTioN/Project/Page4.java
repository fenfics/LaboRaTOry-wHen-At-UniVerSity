import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        status.setFont(new Font("Tahoma", Font.BOLD, 28));
        status.setBounds(75, 125, 1000, 50);
        layeredPane.add(status, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนส่วนบุคคล
        JLabel own = new JLabel("ลดหย่อนส่วนบุคคล");
        own.setFont(new Font("Tahoma", Font.BOLD, 28));
        own.setBounds(500, 125, 1000, 50);
        layeredPane.add(own, JLayeredPane.PALETTE_LAYER);

        // ล็อก ลดหย่อน 60000
        RoundedLabel discount = new RoundedLabel("  60,000", 15);
        discount.setFont(new Font("Tahoma", Font.PLAIN, 25));
        discount.setBounds(500, 180, 300, 45);
        discount.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        discount.setBorder(border); // ตั้งกรอบให้ JLabel
        discount.setOpaque(true);
        layeredPane.add(discount, JLayeredPane.PALETTE_LAYER);

        // เลือกสถานะ
        String s1[] = { "  โสด", "  หย่า", "  คู่สมรสมีเงินได้", "  คู่สมรสไม่มีเงินได้" };
        RoundedComboBox chosestatus = new RoundedComboBox(s1, 15);
        chosestatus.setFont(new Font("Tahoma", Font.PLAIN, 25));
        chosestatus.setBounds(75, 180, 300, 45);
        layeredPane.add(chosestatus, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Home
        RoundedButton button = new RoundedButton("HOME", 20);
        button.setBackground(Color.decode("#80C8CD"));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBounds(20, 20, 120, 50);
        button.addActionListener(e -> System.out.println("HOME button clicked"));
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        RoundedButton button1 = new RoundedButton("BACK", 20);
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        button1.addActionListener(e -> System.out.println("BACK button clicked"));
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        RoundedButton button2 = new RoundedButton("NEXT", 20);
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
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
        //วาดเส้น
        g2d.setColor(Color.decode("#6390BA"));
        g2d.setStroke(new BasicStroke(7));
        g.drawLine(0, 90, 1133, 90);
    }
}

//ทำปุ่มกดแบบมน
class RoundedButton extends JButton {
    private boolean isPressed = false;
    private final int radius;

    public RoundedButton(String label, int radius) {
        super(label);
        this.radius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isPressed) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));

        FontMetrics metrics = g2.getFontMetrics(getFont());
        int x = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // ไม่วาดขอบ
    }

    @Override
    public boolean contains(int x, int y) {
        return new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius).contains(x, y);
    }
}

//ทำลาเบลแบบมน
class RoundedLabel extends JLabel {
    private int radius;

    public RoundedLabel(String text, int radius) {
        super(text);
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
        super.paintComponent(g2);
        g2.dispose();
    }
}

//ทำคอมโบบ็อกซ์แบบมน
class RoundedComboBox extends JComboBox<String> {
    private int radius;

    public RoundedComboBox(String[] items, int radius) {
        super(items);
        this.radius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
        g2.dispose();
    }
}
