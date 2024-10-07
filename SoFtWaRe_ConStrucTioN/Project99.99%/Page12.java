import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Page12 extends JPanel {
    private MainApplication mainApp;
    private JTextField f1, f2, f3;
    private int salaryAfterExpenses; // รับค่าจากหน้าFamily
    private int totalDeduction; // รับค่าจากหน้าFamily
    private int salaryYear;
    private int Salaryyear;
    private int PVDlimit;

    public Page12(MainApplication mainApp) {
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
                mainApp.showPage("Home");
            }
        });
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setBounds(427, 622, 120, 50);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Family");
            }
        });
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalValue = getTotalValue(); // เรียก getTotalValue()
                int f1Value = Integer.parseInt(f1.getText().isEmpty() ? "0" : f1.getText());

                mainApp.sendValuesToPage13(salaryYear, totalDeduction, totalValue); // ส่งค่าทั้งสามไปยังหน้า Page13
                mainApp.showPage("page13");
            }
        });
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // รายการลดหย่อนภาษี : กองทุนPVD / เงินประกัน / ที่อยู่อาศัย
        JLabel family = new JLabel("รายการลดหย่อนภาษี : กองทุนPVD / เงินประกัน / ที่อยู่อาศัย");
        family.setFont(new Font("Tahoma", Font.BOLD, 32));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label1 = new JLabel("ลดหย่อนกองทุนPVD");
        label1.setBounds(350, 140, 500, 100);
        label1.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);

        // เพิ่มTextFieldสำหรับ(PVD)
        f1 = new JTextField();
        f1.setBounds(350, 210, 400, 50);
        f1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = f1.getText();
                int maxPVD = 500000;
                // กรองให้เฉพาะตัวเลข
                if (!text.matches("\\d+")) {
                    f1.setText(text.replaceAll("[^\\d]", ""));
                    return;
                }
                int value = Integer.parseInt(text);
                if (PVDlimit < maxPVD) {
                    // ถ้า PVDlimit น้อยกว่า 500,000 จะใช้ PVDlimit เป็นขีดจำกัด
                    if (value > PVDlimit) {
                        f1.setText(String.valueOf(PVDlimit));
                    }
                } else {
                    // ถ้า PVDlimit มากกว่า 500,000 จะใช้ 500,000 เป็นขีดจำกัด
                    if (value > maxPVD) {
                        f1.setText(String.valueOf(maxPVD));
                    }
                }

                updateContribution(); // อัปเดตค่ารวมกองทุนว่าครบ 500000 แล้วหรือยัง
            }
        });
        layeredPane.add(f1, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn = new JLabel("ไม่เกิน 15% ของเงินเดือน และไม่เกิน 500,000 บาท");
        warn.setBounds(350, 220, 300, 100);
        warn.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label2 = new JLabel("เงินประกัน");
        label2.setBounds(350, 280, 500, 100);
        label2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

        // TextField for เงินประกัน
        f2 = new JTextField();
        f2.setBounds(350, 350, 400, 50);
        f2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = f2.getText();
                if (!text.matches("\\d+")) {
                    f2.setText(text.replaceAll("[^\\d]", ""));
                }
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value > 9000) {
                            f2.setText("9000");
                        }
                    } catch (NumberFormatException ex) {

                    }
                }

            }
        });
        layeredPane.add(f2, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn2 = new JLabel("ไม่เกิน 9,000 บาท");
        warn2.setBounds(350, 360, 300, 100);
        warn2.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn2, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label3 = new JLabel("ดอกเบี้ยซื้อที่อยู่อาศัย");
        label3.setBounds(350, 420, 500, 100);
        label3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label3, JLayeredPane.PALETTE_LAYER);

        // TextField for เงินประกัน
        f3 = new JTextField();
        f3.setBounds(350, 490, 400, 50);
        f3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = f3.getText();
                if (!text.matches("\\d+")) {
                    f3.setText(text.replaceAll("[^\\d]", ""));
                }
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value > 100000) {
                            f3.setText("100000");
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });

        layeredPane.add(f3, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn3 = new JLabel("ไม่เกิน 100,000 บาท");
        warn3.setBounds(350, 500, 300, 100);
        warn3.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn3, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);
    }

    private int parseValue(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0; // คืนค่าศูนย์ถ้าฟิลด์ว่างหรือมีค่าที่ไม่ใช่ตัวเลข
        }
    }

    // นำtextfieldไปใช้ในคลาสอื่น
    public int getPVDValue() {
        return parseValue(f1.getText());
    }

    // Method สำหรับอัปเดตค่ารวม
    private void updateContribution() {
        int sum = 0;
        try {
            sum += Integer.parseInt(f1.getText().isEmpty() ? "0" : f1.getText());
        } catch (NumberFormatException ex) {
            // ถ้าผู้ใช้กรอกข้อมูลไม่ถูกต้อง
        }

        if (sum > 500000) {
            sum = 500000; // จำกัดค่ารวมไม่ให้เกิน 500,000
        }

        mainApp.updateTotalContribution(sum);

    }

    public int getTotalValue() {
        int value1 = parseValue(f1.getText());
        int value2 = parseValue(f2.getText());
        int value3 = parseValue(f3.getText());
        return value1 + value2 + value3; // ส่งค่ารวมคืน
    }

    public void receiveData(int salaryAfterExpenses, int totalDeduction) {
        this.salaryYear = salaryAfterExpenses;
        this.totalDeduction = totalDeduction;

        // แสดงผลค่าที่รับมา
        System.out.println("Salary after expenses: " + salaryAfterExpenses);
        System.out.println("Total Family: " + totalDeduction);
    }

    public void get(int Salaryyear) {
        PVDlimit = (int) (Salaryyear * 0.15);
        System.out.println("กองทุนสำรองPVD limit:" + PVDlimit);
        this.Salaryyear = Salaryyear;
    }
}

class DisplayGraphics extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.decode("#6390BA"));
        g2d.setStroke(new BasicStroke(7));
        g.drawLine(0, 90, 1133, 90);
    }
}