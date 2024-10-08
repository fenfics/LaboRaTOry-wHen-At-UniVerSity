import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Page17 extends JPanel {

    private MainApplication mainApp;
    private int netIncome;
    private JLabel amount, amount2, amount3;
    private int donationAmount;
    private int FinalTax;
    private int RllyLastTax;
    private int DonateMax2;
    private int tax;
    private int TaxAfterReduce;

    public Page17(MainApplication mainApp) {
        this.mainApp = mainApp;
        setLayout(null);
        setBackground(Color.decode("#D8F5FF"));

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
         button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("HOME");
            }
        });
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setBounds(427, 622, 120, 50);
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("page16");
            }
        });

        // ปุ่ม NEXT
        JButton button2 = new JButton("FINISH");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setBounds(587, 622, 120, 50);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Home");
            }
        });

        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
        // บริจาค
        JLabel family = new JLabel("บริจาค");
        family.setFont(new Font("Tahoma", Font.BOLD, 24));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label1 = new JLabel("เงินบริจาคเพื่อการศึกษา การกีฬา");
        JLabel label2 = new JLabel("การพัฒนาสังคมและโรงพยาบาลรัฐ");
        label1.setBounds(50, 100, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label1.setFont(new Font("Tahoma", Font.BOLD, 24));
        label2.setBounds(50, 120, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

        //เพิ่มTextFieldสำหรับ(PVD)
        JTextField f1 = new JTextField();
        f1.setBounds(600, 135, 400, 50);
        f1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = f1.getText();
                if (!text.matches("\\d")) {
                    f1.setText(text.replaceAll("[^\\d]", ""));
                }
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value > donationAmount) {
                            f1.setText("" + donationAmount);
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });
        layeredPane.add(f1, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn = new JLabel("*ลดหย่อน 2 เท่าของเงินที่จ่ายจริง แต่ไม่เกิน 10% ของเงินได้สุทธิ");
        warn.setBounds(600, 145, 600, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn.setForeground(Color.red);
        layeredPane.add(warn, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label3 = new JLabel("เงินบริจาคทั่วไป");
        label3.setBounds(100, 220, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label3, JLayeredPane.PALETTE_LAYER);

        //TextField for เงินประกัน
        JTextField f2 = new JTextField();
        f2.setBounds(600, 250, 400, 50);
        f2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = f2.getText();
                if (!text.matches("\\d")) {
                    f2.setText(text.replaceAll("[^\\d]", ""));
                }
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value > RllyLastTax) {
                            f2.setText("" + RllyLastTax);
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });
        layeredPane.add(f2, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn2 = new JLabel("ตามที่จ่ายจริงแต่ไม่เกิน 10% ของเงินได้สุทธิ");
        warn2.setBounds(600, 260, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn2.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn2.setForeground(Color.RED);
        layeredPane.add(warn2, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label4 = new JLabel("เหลือภาษีที่ต้องจ่าย");
        label4.setBounds(90, 450, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label4.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label4, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn3 = new JLabel("หลังลดหย่อนเงินบริจาค");
        warn3.setBounds(90, 470, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn3.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn3, JLayeredPane.PALETTE_LAYER);

        //เมื่อลงทุนสูงสุด
        JLabel label5 = new JLabel("เมื่อลงทุนสูงสุด");
        label5.setBounds(460, 400, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label5.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label5, JLayeredPane.PALETTE_LAYER);

        amount = new JLabel("0");
        amount.setBounds(490, 450, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount, JLayeredPane.PALETTE_LAYER);

        JLabel label6 = new JLabel("เมื่อลงทุนตามจำนวนเงินของคุณ");
        label6.setBounds(730, 400, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label6.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label6, JLayeredPane.PALETTE_LAYER);

        amount2 = new JLabel("0");
        amount2.setBounds(820, 450, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount2, JLayeredPane.PALETTE_LAYER);
        add(layeredPane);
    }
    public void setAmount(String value) {
        amount.setText(value);
    }
    public void setAmount2(String value) {
        amount2.setText(value);
    }
    public void getNet(int netIncome) {
        this.netIncome=netIncome;
        int Maxdonate = netIncome - 800000;
        if (Maxdonate > 0) {
            donationAmount = (int) ((Maxdonate * 0.10) / 2);
        }
        FinalTax = Maxdonate - (donationAmount * 2);
        RllyLastTax = (int) (FinalTax * 0.10);
        int DonateMax = Maxdonate - (donationAmount * 2) - RllyLastTax;
        if (DonateMax < 150000) {
            DonateMax = 0; // ไม่ต้องเสียภาษี
        } else if (DonateMax > 150000 && DonateMax <= 300000) {
            DonateMax = (int) ((DonateMax - 150000) * 0.05); // คำนวณภาษี 5%
        } else if (DonateMax > 300000 && DonateMax <= 500000) {
            DonateMax = (int) ((DonateMax - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 * 0.05)=7500
        } else if (DonateMax > 500000 && DonateMax <= 750000) {
            DonateMax = (int) ((DonateMax - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 15% //200000*0.10-จะได้2หมื่นและนำ(150000 * 0.05)มาใช้ 
        } else if (DonateMax > 750000 && DonateMax <= 1000000) {
            DonateMax = (int) ((DonateMax - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 20% //(250000 * 0.15)=37500
        } else if (DonateMax > 1000000 && DonateMax <= 2000000) {
            DonateMax = (int) ((DonateMax - 1000000) * 0.25 + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
        } else if (DonateMax > 2000000 && DonateMax <= 5000000) {
            DonateMax = (int) ((DonateMax - 2000000) * 0.30 + (1000000 * 0.25) + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
        } else if (DonateMax > 5000000) {
            DonateMax = (int) ((DonateMax - 5000000) * 0.35 + (3000000 * 0.30) + (1000000 * 0.25) + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
        }
        setAmount(String.valueOf(DonateMax));
    }

    public void getTax(int tax) {
        int NewTax = tax - DonateMax2;
        int TaxAfterReduce = tax - DonateMax2;
        setAmount2(String.valueOf(NewTax));
    }
}

class DisplayGraphics extends JPanel {

    public DisplayGraphics() {
        setOpaque(false); // Set this to false so the background color of Login can be seen
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the line with a transparent background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode("#6390BA"));
        g2d.setStroke(new BasicStroke(7));
        g.drawLine(0, 90, 1133, 90);
    }
}