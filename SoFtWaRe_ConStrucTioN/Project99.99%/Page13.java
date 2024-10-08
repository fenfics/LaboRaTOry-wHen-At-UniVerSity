import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Page13 extends JPanel {
    private int previousPageValue = 0;
    private int salaryYear;
    private int totalDeduction;
    private int totalValue;
    private int Salaryyear180k;
    private int PVDlimit;
    private JTextField f1, f2, f3, f4;
    private final int MAX_TOTAL = 100000;
    private final int MAX_F3 = 25000;
    private final int MAX_F4 = 300000;
     private int PVDvalue;
    public Page13(MainApplication mainApp) {
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
        button.setBounds(20, 20, 120, 50);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Home");
            }
        });
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("page12");
            }
        });

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 int alltotalValue = getTotalValue();
                int valueF4 = Integer.parseInt(f4.getText().isEmpty() ? "0" : f4.getText());
                int f1Value = parseValue(f1.getText());
                int f3Value = parseValue(f3.getText());
                int usedQuota = f1Value + f3Value;
                int remainingQuota = Math.max(100000 - usedQuota, 0);
    
                int adjustedF4Value;
                if (remainingQuota > 0) {
                    // Subtract the remaining quota from f4, but not less than 0
                    adjustedF4Value = Math.max(valueF4 - remainingQuota, 0);
                } else {
                    // If no remaining quota, send full f4 value
                    adjustedF4Value = valueF4;
                }
    
                mainApp.sendF4valuetoPage15(valueF4);
                mainApp.sendF4toPage16(adjustedF4Value);
                mainApp.sendF4ToPage14(adjustedF4Value);
                mainApp.sendValuesToPage14(salaryYear, totalDeduction, totalValue, alltotalValue);
                mainApp.showPage("page14"); 
            }
        });
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        // รายการลดหย่อนภาษี :ประกัน
        JLabel family2 = new JLabel("รายการลดหย่อนภาษี :ประกัน");
        family2.setFont(new Font("Tahoma", Font.BOLD, 32));
        family2.setBounds(160, 15, 1000, 50);
        layeredPane.add(family2, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label1 = new JLabel("เบี้ยประกันชีวิต");
        label1.setBounds(100, 140, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label1.setFont(new Font("Tahoma", Font.BOLD, 24));

        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);

        // เพิ่มTextFieldเบี้ยประกันชีวิต
        f1 = new JTextField();
        f1.setBounds(100, 210, 400, 50);
        f1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> updateFields(f1, f3));
            }
        });

        layeredPane.add(f1, JLayeredPane.PALETTE_LAYER);

        // เพิ่มTextFieldเบี้ยประกันชีวิต บิดาและมารดา
        f2 = new JTextField();
        f2.setBounds(100, 420, 400, 50);
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
                        if (value > 15000) {
                            f2.setText("15000");
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });
        layeredPane.add(f2, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn = new JLabel("ไม่เกิน 100,000 บาท");
        JLabel warn2 = new JLabel("*เบี้ยประกันชีวิต และประกันสุขภาพรวมกันต้องไม่เกิน 100,000 บาท*");
        warn.setBounds(100, 220, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn2.setBounds(100, 230, 500, 100);
        warn2.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn2, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label2 = new JLabel("เบี้ยประกันชีวิต บิดาและมารดา");
        label2.setBounds(100, 350, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn3 = new JLabel("ไม่เกิน 15,000");
        warn3.setBounds(100, 430, 500, 100);
        warn3.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn3, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label3 = new JLabel("เบี้ยประกันสุขภาพ");
        label3.setBounds(600, 140, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label3, JLayeredPane.PALETTE_LAYER);

        // TextField for เงินประกัน
        f3 = new JTextField();
        f3.setBounds(600, 210, 400, 50);
        f3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f3.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> updateFields(f3, f1));
            }
        });

        layeredPane.add(f3, JLayeredPane.PALETTE_LAYER);
        // ข้อกำหนด
        JLabel warn4 = new JLabel("ไม่เกิน 25,000");
        warn4.setBounds(600, 220, 500, 100);
        warn4.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn4, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label4 = new JLabel("เบี้ยประกันชีวิตบำนาญ");
        label4.setBounds(600, 350, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label4.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label4, JLayeredPane.PALETTE_LAYER);

        // เพิ่มTextFieldสำหรับ(เบี้ยประกันชีวิตบำนาญ)
        f4 = new JTextField();
        f4.setBounds(600, 420, 400, 50);
        f4.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f4.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> updateF4());
            }
        });
        layeredPane.add(f4, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn5 = new JLabel("ไม่เกิน 15% ของรายได้ทั้งปี ไม่เกิน 200,000 บาท ");
        JLabel warn6 = new JLabel("หากไม่ได้ใช้สิทธิประกันชีวิตทั่วไป สามารถนำมารวมได้สูงสุด 300,000 บาท");
        JLabel warn7 = new JLabel("และรวมกับกองทุนอื่นไม่เกิน 500,000 บาท");
        warn5.setBounds(600, 430, 500, 100);
        warn5.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn6.setBounds(600, 450, 500, 100);
        warn6.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn7.setBounds(600, 470, 500, 100);
        warn7.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn5, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn6, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn7, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);

    }

    public int getTotalValue() {
        int value1 = parseValue(f1.getText());
        int value2 = parseValue(f2.getText());
        int value3 = parseValue(f3.getText());
        int value4 = parseValue(f4.getText());
        return value1 + value2 + value3 + value4;
    }

    private void updateFields(JTextField currentField, JTextField otherField) {
    int currentValue = parseValue(currentField.getText());
    int otherValue = parseValue(otherField.getText());
    int total = currentValue + otherValue;

    if (total > MAX_TOTAL) {
        int maxAllowed = MAX_TOTAL - otherValue;
        if (maxAllowed < 0) maxAllowed = 0;
        currentField.setText(String.valueOf(maxAllowed));
    }

    if (currentField == f3 && currentValue > MAX_F3) {
        currentField.setText(String.valueOf(MAX_F3));
    }
}

    private void updateF4() {
    int f1Value = parseValue(f1.getText());
    int f3Value = parseValue(f3.getText());
    int f4Value = parseValue(f4.getText());
    int usedQuota = f1Value + f3Value;
    int remainingQuota = Math.max(100000 - usedQuota, 0);

    // Calculate maxF4 considering PVDlimit
    int maxF4 = Math.min(PVDlimit, 300000);  // Limit to PVDlimit or 300,000, whichever is lower

    if (usedQuota < 100000) {
        // If f1 and f3 haven't used the full 100,000 quota, add the remaining to maxF4
        maxF4 = Math.min(maxF4 + remainingQuota, 300000);
    } else {
        // If f1 and f3 have used 100,000 or more, limit to PVDlimit or 200,000
        maxF4 = Math.min(maxF4, 200000);
    }

    // Ensure maxF4 is not negative
    maxF4 = Math.max(maxF4, 0);

    if (f4Value > maxF4) {
        f4.setText(String.valueOf(maxF4));
    }
}


    private int parseValue(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void receiveData(int salaryAfterExpenses, int totalDeduction, int totalValue) {
        this.salaryYear = salaryAfterExpenses;
        this.totalDeduction = totalDeduction;
        this.totalValue = totalValue;

        // แสดงผลค่าหรือทำงานเพิ่มเติมได้ที่นี่
        System.out.println("Salary after expenses: " + salaryAfterExpenses);
        System.out.println("Total Family: " + totalDeduction);
        System.out.println("Total FundsPVDandOTHER: " + totalValue);
    }

    public void getSalary(int salaryYearPage13) {
        this.Salaryyear180k = salaryYearPage13;
        PVDlimit = (int) (Salaryyear180k * 0.15);
        System.out.println("กองทุนสำรองPVD limitหน้า13:" + PVDlimit);

    }
    public void getPVD(int value){
    this.PVDvalue =value;
    updateF4();
    }

    private static class DisplayGraphics extends JPanel {

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
}