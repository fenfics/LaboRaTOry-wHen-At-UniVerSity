import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Page14 extends JPanel {
    private MainApplication mainApp;
    private JTextField f, f2, f3;
    private int maxLimit = 500000;
    private int previousPageValue = 0;
    double income1 = 0.0;
    double income2 = 0.0;
    double income3 = 0.0;
    private int salaryAfterExpenses;
    private int totalDeduction;
    private int totalValue;
    private int alltotalValue;
    private int netIncome;
    private int PVDlimitFORpage14;
    private int Salaryyear2;
    private int salaryYear;
    private int f4Value = 0;
    private int f1Value = 0;
    private int f4ValueFromPage13;

    public Page14(MainApplication mainApp) {
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
                mainApp.showPage("page13");
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
                int TotalValue3 = getTotalValue2();
                // คำนวณ netIncome
                int netIncome = salaryAfterExpenses - totalDeduction - totalValue - alltotalValue - TotalValue3;
                // ส่งค่าทั้งหมดไปยัง Page15
                int remainingFromFiveHundredThousand = 500000 - TotalValue3;
                mainApp.sendbeartoPage16(TotalValue3);
                mainApp.sendValuesToPage15(salaryAfterExpenses, totalDeduction, totalValue, alltotalValue, TotalValue3, netIncome);
                mainApp.sendRemainfor500K(remainingFromFiveHundredThousand);    
                mainApp.showPage("page15");
            }
        });
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // รายการลดหย่อนภาษี : กองทุนอื่นๆ
        JLabel family = new JLabel("รายการลดหย่อนภาษี : กองทุนอื่นๆ");
        family.setFont(new Font("Tahoma", Font.BOLD, 24));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label1 = new JLabel("กองทุนบำเหน็จบำนาญข้าราชการ(กบข.)");
        label1.setBounds(350, 140, 600, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label1.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);

        // เพิ่มTextFieldกองทุนบำเหน็จบำนาญข้าราชการ(กบข.)
        f = new JTextField();
        f.setBounds(350, 210, 400, 50);
        f.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateContribution(f, f2, f3);
                String text = f.getText();
                if (!text.matches("\\d*")) {
                    f.setText(text.replaceAll("[^\\d]", ""));
                    return;
                }

                if (!text.isEmpty()) {
                    int value = Integer.parseInt(text);
                    int maxValue = Math.min(PVDlimitFORpage14,
                            500000 - f4ValueFromPage13 - parseValue(f2) - parseValue(f3));
                    if (value > maxValue) {
                        f.setText(String.valueOf(maxValue));
                    }
                }
            }
        });

        layeredPane.add(f, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn = new JLabel("ไม่เกิน 15% ของเงินเดือน และรวมกับกองทุนอื่นไม่เกิน 500,000 บาท");
        warn.setBounds(350, 220, 700, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง กองทุนออมแห่งชาติ(กอช.)
        JLabel label2 = new JLabel("กองทุนออมแห่งชาติ(กอช.)");
        label2.setBounds(350, 280, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

        // TextField for กองทุนออมแห่งชาติ(กอช.)
        f2 = new JTextField();
        f2.setBounds(350, 350, 400, 50);
        f2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateContribution(f2, f, f3);
                String text = f2.getText();
                if (!text.matches("\\d*")) {
                    f2.setText(text.replaceAll("[^\\d]", ""));
                    return;
                }

                if (!text.isEmpty()) {
                    int value = Integer.parseInt(text);
                    int maxValue = Math.min(13200, 500000 - f4ValueFromPage13 - parseValue(f) - parseValue(f3));
                    if (value > maxValue) {
                        f2.setText(String.valueOf(maxValue));
                    }
                }
            }
        });
        layeredPane.add(f2, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn2 = new JLabel(
                "ไม่เกิน 13,200 บาทและรวมกับกองทุนอื่นและเบี้ยประกันชีวิตแบบบำนาญแล้วไม่เกิน 500,000บาท");
        warn2.setBounds(350, 360, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn2.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn2, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง กองทุนครูเอกชน
        JLabel label3 = new JLabel("กองทุนครูเอกชน");
        label3.setBounds(350, 420, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label3, JLayeredPane.PALETTE_LAYER);

        // TextField for กองทุนครูเอกชน
        f3 = new JTextField();
        f3.setBounds(350, 490, 400, 50);
        f3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateContribution(f3, f, f2);
                String text = f3.getText();
                if (!text.matches("\\d*")) {
                    f3.setText(text.replaceAll("[^\\d]", ""));
                    return;
                }

                if (!text.isEmpty()) {
                    int value = Integer.parseInt(text);
                    int maxValue = Math.min(PVDlimitFORpage14,
                            500000 - f4ValueFromPage13 - parseValue(f) - parseValue(f2));
                    if (value > maxValue) {
                        f3.setText(String.valueOf(maxValue));
                    }
                }
            }
        });
        layeredPane.add(f3, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn3 = new JLabel(
                "ไม่เกิน 15% ของเงินเดือน และรวมกับกองทุนอื่นและเบี้ยประกันชีวิตแบบบำนาญแล้วไม่เกิน500,000บาท");
        warn3.setBounds(350, 500, 700, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn3.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn3, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);
    }
    public int getTotalValue2() {
        int value1 = parseValue(f);
        int value2 = parseValue(f2);
        int value3 = parseValue(f3);
        return value1 + value2 + value3; // ส่งค่ารวมคืน
    }

    public void setPreviousPageValue(int value) {
        this.previousPageValue = value;
    }

    public void receiveData(int netIncome) {
        this.netIncome = netIncome;

    }

    // Method ช่วยในการแปลงค่าใน JTextField
    private int parseValue(JTextField field) {
        try {
            return Integer.parseInt(field.getText().isEmpty() ? "0" : field.getText());
        } catch (NumberFormatException ex) {
            return 0; // ถ้าไม่ใช่ตัวเลขให้คืนค่า 0
        }
    }

    public void receiveData(int salaryAfterExpenses, int totalDeduction, int totalValue, int alltotalValue) {
        this.salaryAfterExpenses = salaryAfterExpenses;
        this.totalDeduction = totalDeduction;
        this.totalValue = totalValue;
        this.alltotalValue = alltotalValue;
        // แสดงผลค่าหรือทำงานเพิ่มเติมได้ที่นี่
        System.out.println("---------------------------------------------------------");
        System.out.println("Salary after expenses: " + salaryAfterExpenses);
        System.out.println("Total Family: " + totalDeduction);
        System.out.println("Total FundsPVDandOTHER: " + totalValue);
        System.out.println("Total insurance:" + alltotalValue);
    }

    public void setF4Value(int f4Value) {
        this.f4ValueFromPage13 = f4Value;
        System.out.println("Received f4 value from Page13: " + f4ValueFromPage13);
        // เรียกอัปเดตการคำนวณเมื่อได้รับค่า f4 ใหม่จาก Page13
    }

    public void setF1Value(int f1Value) {
        this.f1Value = f1Value;
        // อัปเดตการคำนวณเมื่อได้รับค่าใหม่
    }

    private void updateContribution(JTextField currentField, JTextField otherField1, JTextField otherField2) {
        int totalFromPreviousPages = mainApp.getTotalContribution() + f4ValueFromPage13;
        int maxAllowed = 500000 - totalFromPreviousPages;
        int sum = parseValue(currentField) + parseValue(otherField1) + parseValue(otherField2);

        if (sum > maxAllowed) {
            int excess = sum - maxAllowed;
            int currentValue = parseValue(currentField);
            if (currentValue > excess) {
                currentField.setText(String.valueOf(currentValue - excess));
            } else {
                currentField.setText("0");
            }
        }

        int newTotal = sum + totalFromPreviousPages;
        System.out.println("Current total: " + newTotal);
        System.out.println("Remaining: " + (500000 - newTotal));
    }

    public void setF4ValueFromPage13(int value) {
        this.f4ValueFromPage13 = value;
        System.out.println("Received f4 value from Page13: " + f4ValueFromPage13);
        updateAllFields();
    }

    private void updateAllFields() {
        updateContribution(f, f2, f3);
        updateContribution(f2, f, f3);
        updateContribution(f3, f, f2);
    }

    public void get(int Salaryyear2) {
        this.salaryYear = Salaryyear2;
        PVDlimitFORpage14 = Math.min((int) (Salaryyear2 * 0.15), 500000);
        System.out.println("กองทุนสำรองPVD limit: " + PVDlimitFORpage14);
        System.out.println("Salary year: " + salaryYear);
        updateAllFields();
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