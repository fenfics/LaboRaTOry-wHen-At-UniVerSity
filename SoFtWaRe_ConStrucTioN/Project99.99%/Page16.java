import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;

public class Page16 extends JPanel {

    private MainApplication mainApp;
    private JTextField f1, f2, f3;
    private JLabel label5;
    private JLabel amount, amount2, amount3, amount4, amount5, amount6;
    private static final long Max_Total = 800000;
    private int netIncome;
    private int tax;
    private int Tax;
    private int invest, invest1;
    private double Totalinvest;
    private int newTax;
    private int SalaryYear;
    double investwithurMoney;

    public Page16(MainApplication mainApp) {

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
                mainApp.showPage("HOME");
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
                mainApp.showPage("page15");
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
                mainApp.showPage("page17");
                calculateTotal();
            }
        });
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนภาษีกับกองทุน (SSF / RMF/ ThaiESG)
        JLabel family = new JLabel("ลดหย่อนภาษีกับกองทุน (SSF / RMF/ ThaiESG)");
        family.setFont(new Font("Tahoma", Font.BOLD, 24));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label1 = new JLabel("ลงทุน SSF อย่างเดียว");
        label1.setBounds(50, 130, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label1.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);

        // จำนวน
        amount = new JLabel("XXX");
        amount.setBounds(500, 130, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount, JLayeredPane.PALETTE_LAYER);

        // เพิ่มTextFieldสำหรับ(PVD)
        f1 = new JTextField();
        f1.setBounds(700, 160, 400, 50);
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
                        if (value > 200000) {
                            f1.setText("200000");
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });
        f1.getDocument().addDocumentListener(documentListener);
        layeredPane.add(f1, JLayeredPane.PALETTE_LAYER);

        // รายละเอียด
        JLabel detail = new JLabel("*จำนวนเงินลงทุนแนะนำในการลดหย่อนภาษี*");
        detail.setBounds(700, 170, 300, 100);
        detail.setFont(new Font("Tahoma", Font.BOLD, 11));
        detail.setForeground(Color.RED);
        layeredPane.add(detail, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn = new JLabel("SSF 30 % ของรายได้ทั้งปี ไม่เกิน 200,000 บาท");
        JLabel warn2 = new JLabel("และรวมกับกองทุนกลุ่มเกษียณ ไม่เกิน 500,000 บาท");
        warn.setBounds(50, 160, 300, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn.setForeground(Color.RED);
        warn2.setBounds(50, 180, 300, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn2.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn2.setForeground(Color.RED);
        layeredPane.add(warn, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn2, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label2 = new JLabel("ลงทุน RMF อย่างเดียว");
        label2.setBounds(50, 240, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

        // จำนวน
        amount2 = new JLabel("360000");
        amount2.setBounds(500, 240, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount2, JLayeredPane.PALETTE_LAYER);

        // TextField for ลงทุน RMF อย่างเดียว
        f2 = new JTextField("");
        f2.setBounds(700, 270, 400, 50);
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
                        if (value > 300000) {
                            f2.setText("300000");
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });
        f2.getDocument().addDocumentListener(documentListener);
        layeredPane.add(f2, JLayeredPane.PALETTE_LAYER);

        // รายละเอียด
        JLabel detail2 = new JLabel("*จำนวนเงินลงทุนแนะนำในการลดหย่อนภาษี*");
        detail2.setBounds(700, 280, 300, 100);
        detail2.setFont(new Font("Tahoma", Font.BOLD, 11));
        detail2.setForeground(Color.RED);
        layeredPane.add(detail2, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn3 = new JLabel("RMF 30 % ของรายได้ทั้งปี และรวมกับ");
        JLabel warn4 = new JLabel("กองทุนกลุ่มเกษียณ ไม่เกิน 500,000 บาท");
        warn3.setBounds(50, 270, 300, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn3.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn3.setForeground(Color.RED);
        warn4.setBounds(50, 290, 300, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn4.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn4.setForeground(Color.RED);
        layeredPane.add(warn3, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn4, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label3 = new JLabel("ลงทุน ThaiESG อย่างเดียว");
        label3.setBounds(50, 350, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label3, JLayeredPane.PALETTE_LAYER);

        // จำนวน
        amount3 = new JLabel("XXX");
        amount3.setBounds(500, 350, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount3, JLayeredPane.PALETTE_LAYER);

        // TextField for เงินประกัน
        f3 = new JTextField();
        f3.setBounds(700, 380, 400, 50);
        f3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        f3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = f3.getText();
                if (!text.matches("\\d")) {
                    f3.setText(text.replaceAll("[^\\d]", ""));
                }
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value > 300000) {
                            f3.setText("300000");
                        }
                    } catch (NumberFormatException ex) {

                    }
                }
            }
        });
        f3.getDocument().addDocumentListener(documentListener);
        layeredPane.add(f3, JLayeredPane.PALETTE_LAYER);

        // รายละเอียด
        JLabel detail3 = new JLabel("*จำนวนเงินลงทุนแนะนำในการลดหย่อนภาษี*");
        detail3.setBounds(700, 390, 300, 100);
        detail3.setFont(new Font("Tahoma", Font.BOLD, 11));
        detail3.setForeground(Color.RED);
        layeredPane.add(detail3, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn5 = new JLabel("ThaiESG30 % ของรายได้ทั้งปี ไม่เกิน 300,000 บาท");
        JLabel warn6 = new JLabel("และไม่รวมกับกองทุนกลุ่มเกษียณ");
        warn5.setBounds(50, 380, 400, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn5.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn5.setForeground(Color.RED);
        warn6.setBounds(50, 400, 400, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn6.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn6.setForeground(Color.RED);
        layeredPane.add(warn5, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn6, JLayeredPane.PALETTE_LAYER);

        // หัวข้อเรื่อง
        JLabel label4 = new JLabel("รวม");
        label4.setBounds(700, 420, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label4.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label4, JLayeredPane.PALETTE_LAYER);

        label5 = new JLabel("0");
        label5.setBounds(850, 420, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label5.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label5, JLayeredPane.PALETTE_LAYER);

        // หัวข้อ
        JLabel label7 = new JLabel("จำนวนที่คุณลงทุนได้สูงสุด (บาท)");
        label7.setBounds(340, 80, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label7.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label7, JLayeredPane.PALETTE_LAYER);

        JLabel label8 = new JLabel("จำนวนที่ต้องการจะลงทุน (บาท)");
        label8.setBounds(750, 80, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label8.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label8, JLayeredPane.PALETTE_LAYER);

        // หัวข้อภาษีที่ประหยัดไปได้
        JLabel label9 = new JLabel("ภาษีที่ประหยัดไปได้");
        label9.setBounds(50, 510, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label9.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label9, JLayeredPane.PALETTE_LAYER);

        // ข้อกำหนด
        JLabel warn7 = new JLabel("หลังลดหย่อน SSF/RMF/ThaiESG");
        warn7.setBounds(50, 530, 300, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn7.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn7, JLayeredPane.PALETTE_LAYER);

        // เมื่อลงทุนสูงสุด
        JLabel label10 = new JLabel("เมื่อลงทุนสูงสุด");
        label10.setBounds(460, 470, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label10.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label10, JLayeredPane.PALETTE_LAYER);

        // จำนวน
        amount4 = new JLabel("0");
        amount4.setBounds(505, 510, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount4.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount4, JLayeredPane.PALETTE_LAYER);

        // เมื่อลงทุนตามจำนวนเงินของคุณ
        JLabel label11 = new JLabel("เมื่อลงทุนตามจำนวนเงินของคุณ");
        label11.setBounds(750, 470, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label11.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label11, JLayeredPane.PALETTE_LAYER);

        amount5 = new JLabel("0");
        amount5.setBounds(860, 510, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount5.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount5, JLayeredPane.PALETTE_LAYER);

        JLabel label12 = new JLabel("ภาษีที่ต้องจ่ายหลังลงทุน");
        label12.setBounds(750, 600, 300, 50);
        label12.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(label12, JLayeredPane.PALETTE_LAYER);

        amount6 = new JLabel("0");
        amount6.setBounds(770, 610, 500, 100); // ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount6.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(amount6, JLayeredPane.PALETTE_LAYER);

        JLabel label13 = new JLabel("บาท");
        label13.setBounds(880, 635, 500, 50);
        label13.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(label13, JLayeredPane.PALETTE_LAYER);
        add(layeredPane);
    }

    DocumentListener documentListener = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            calculateTotal();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calculateTotal();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calculateTotal();
        }
    };

    private void calculateTotal() {
        try {
            double ssf = Double.parseDouble(f1.getText());
            label5.setText(String.format("%.2f บาท", ssf));
            double rmf = Double.parseDouble(f2.getText());
            double total1 = ssf + rmf;
            if (total1 > Max_Total) {
                total1 = Max_Total;
            }
            label5.setText(String.format("%.2f บาท", total1));
            double thaiESG = Double.parseDouble(f3.getText());
            double total2 = total1 + thaiESG;
            investwithurMoney = netIncome - total2; // เมื่อลงทุนสูงสุด
            Totalinvest = netIncome - total2; // เมื่อลงทุนตามจำนวนที่คุณลงทุน
            invest = 0;
            if (Totalinvest < 150000) {
                invest1 = 0; // ไม่ต้องเสียภาษี
            } else if (Totalinvest > 150000 && Totalinvest <= 300000) {
                invest1 = (int) ((Totalinvest - 150000) * 0.05); // คำนวณภาษี 5%
            } else if (Totalinvest > 300000 && Totalinvest <= 500000) {
                invest1 = (int) ((Totalinvest - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 *
                                                                                   // 0.05)=7500
            } else if (Totalinvest > 500000 && Totalinvest <= 750000) {
                invest1 = (int) ((Totalinvest - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 15%
                                                                                                     // //200000*0.10-จะได้2หมื่นและนำ(150000
                                                                                                     // * 0.05)มาใช้
            } else if (Totalinvest > 750000 && Totalinvest <= 1000000) {
                invest1 = (int) ((Totalinvest - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี
                                                                                                                       // 20%
                                                                                                                       // //(250000
                                                                                                                       // *
                                                                                                                       // 0.15)=37500
            } else if (Totalinvest > 1000000 && Totalinvest <= 2000000) {
                invest1 = (int) ((netIncome - 1000000) * 0.25 + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10)
                        + (150000 * 0.05) + (150000 * 0.05));
            } else if (Totalinvest > 2000000 && Totalinvest <= 5000000) {
                invest1 = (int) ((netIncome - 2000000) * 0.30 + (1000000 * 0.25) + (250000 * 0.20) + (200000 * 0.15)
                        + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
            } else if (Totalinvest > 5000000) {
                invest1 = (int) ((Totalinvest - 5000000) * 0.35 + (3000000 * 0.30) + (1000000 * 0.25) + (250000 * 0.20)
                        + (200000 * 0.15) + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
            }
            updateAmount5(String.valueOf(invest1));
            if (investwithurMoney < 150000) {
                invest1 = 0; // ไม่ต้องเสียภาษี
            } else if (investwithurMoney > 150000 && investwithurMoney <= 300000) {
                invest = (int) ((investwithurMoney - 150000) * 0.05); // คำนวณภาษี 5%
            } else if (investwithurMoney > 300000 && investwithurMoney <= 500000) {
                invest = (int) ((investwithurMoney - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 *
                                                                                        // 0.05)=7500
            } else if (investwithurMoney > 500000 && investwithurMoney <= 750000) {
                invest = (int) ((investwithurMoney - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี
                                                                                                          // 15%
                                                                                                          // //200000*0.10-จะได้2หมื่นและนำ(150000
                                                                                                          // *
                                                                                                          // 0.05)มาใช้
            } else if (investwithurMoney > 750000 && investwithurMoney <= 1000000) {
                invest = (int) ((investwithurMoney - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10)
                        + (150000 * 0.05)); // คำนวณภาษี 20% //(250000 * 0.15)=37500
            } else if (investwithurMoney > 1000000 && investwithurMoney <= 2000000) {
                invest = (int) ((netIncome - 1000000) * 0.25 + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10)
                        + (150000 * 0.05) + (150000 * 0.05));
            } else if (investwithurMoney > 2000000 && investwithurMoney <= 5000000) {
                invest = (int) ((netIncome - 2000000) * 0.30 + (1000000 * 0.25) + (250000 * 0.20) + (200000 * 0.15)
                        + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
            } else if (investwithurMoney > 5000000) {
                invest = (int) ((investwithurMoney - 5000000) * 0.35 + (3000000 * 0.30) + (1000000 * 0.25)
                        + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
            }

            if (total2 > Max_Total) {
                total2 = Max_Total;
            }
            label5.setText(String.format("%.2f บาท", total2));
        } catch (NumberFormatException ex) {

        }
    }

    public void updateAmount(String value) {
        amount.setText(value);
    }

    public void updateAmount3(String value) {
        amount3.setText(value);
    }

    public void updateAmount4(String value) {
        amount4.setText(value);
    }

    public void updateAmount5(String value) {
        amount5.setText(value);
    }

    public void updateAmount6(String value) {
        amount6.setText(value);
    }

    public void receiveData(int netIncome) {
        int maxSSF = (int) (netIncome * 0.30);
        if (maxSSF > 200000) {
            maxSSF = 200000;
        }
        updateAmount(String.valueOf(maxSSF));
        int maxThaiESG = (int) (netIncome * 0.30);
        if (maxThaiESG > 300000) {
            maxThaiESG = 300000;
        }
        updateAmount3(String.valueOf(maxThaiESG));
        int maxOFinvest = netIncome - 800000;
        if (maxOFinvest < 150000) {
            tax = 0; // ไม่ต้องเสียภาษี
        } else if (maxOFinvest > 150000 && maxOFinvest <= 300000) {
            tax = (int) ((maxOFinvest - 150000) * 0.05); // คำนวณภาษี 5%
        } else if (maxOFinvest > 300000 && maxOFinvest <= 500000) {
            tax = (int) ((maxOFinvest - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 * 0.05)=7500
        } else if (maxOFinvest > 500000 && maxOFinvest <= 750000) {
            tax = (int) ((maxOFinvest - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 15%
                                                                                             // //200000*0.10-จะได้2หมื่นและนำ(150000
                                                                                             // * 0.05)มาใช้
        } else if (maxOFinvest > 750000 && maxOFinvest <= 1000000) {
            tax = (int) ((maxOFinvest - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี
                                                                                                               // 20%
                                                                                                               // //(250000
                                                                                                               // *
                                                                                                               // 0.15)=37500
        } else if (maxOFinvest > 1000000 && maxOFinvest <= 2000000) {
            tax = (int) ((netIncome - 1000000) * 0.25 + (250000 * 0.20) + (200000 * 0.15) + (200000 * 0.10)
                    + (150000 * 0.05) + (150000 * 0.05));
        } else if (maxOFinvest > 2000000 && maxOFinvest <= 5000000) {
            tax = (int) ((netIncome - 2000000) * 0.30 + (1000000 * 0.25) + (250000 * 0.20) + (200000 * 0.15)
                    + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
        } else if (maxOFinvest > 5000000) {
            tax = (int) ((maxOFinvest - 5000000) * 0.35 + (3000000 * 0.30) + (1000000 * 0.25) + (250000 * 0.20)
                    + (200000 * 0.15) + (200000 * 0.10) + (150000 * 0.05) + (150000 * 0.05));
        }
        updateAmount4(String.valueOf(tax));
        this.netIncome = netIncome;
        System.out.println("เงินได้สุทธิ:" + netIncome);
    }

    public void getSalary(int salaryYear1) {
        this.SalaryYear = salaryYear1;
    }

    public void getTax(int Tax) {
        this.Tax = Tax;
        newTax = Tax - tax;
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