import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jfree.chart.util.StringUtils;

public class Page16 extends JPanel {

    private MainApplication mainApp;
    private JTextField f1, f2, f3;
    private JLabel label5;
    private JLabel amount, amount2, amount3, amount4, amount5, amount6;
    private int MaxAf=800000;
    private int newAf;
    private int netIncome;
    private int tax;
    private int Tax;
    private int invest, invest1;
    private double Totalinvest;
    private int SalaryYear;
    private int value;
    private int PVDvalue;
    private int F4value;
    private int Page14value;
    private  int newTax;
    double investwithurMoney;
    private int netIncom;

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
                // calculateTotal();
            }
        });
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนภาษีกับกองทุน (SSF / RMF/ ThaiESG)
        JLabel family = new JLabel("ลดหย่อนภาษีกับกองทุน (SSF / RMF/ ThaiESG)");
        family.setFont(new Font("Tahoma", Font.BOLD, 24));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label1 = new JLabel("ลงทุน SSF อย่างเดียว");
        label1.setBounds(50, 130, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label1.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);

        //จำนวน
        amount = new JLabel("200000");
        amount.setBounds(500, 130, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount, JLayeredPane.PALETTE_LAYER);

        //เพิ่มTextFieldสำหรับ(PVD)
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
                validateInput();
            }
        });
        // f1.getDocument().addDocumentListener(documentListener);
        layeredPane.add(f1, JLayeredPane.PALETTE_LAYER);

        //รายละเอียด
        JLabel detail = new JLabel("*จำนวนเงินลงทุนแนะนำในการลดหย่อนภาษี*");
        detail.setBounds(700, 170, 300, 100);
        detail.setFont(new Font("Tahoma", Font.BOLD, 11));
        detail.setForeground(Color.RED);
        layeredPane.add(detail, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn = new JLabel("SSF 30 % ของรายได้ทั้งปี ไม่เกิน 200,000 บาท");
        JLabel warn2 = new JLabel("และรวมกับกองทุนกลุ่มเกษียณ ไม่เกิน 500,000 บาท");
        warn.setBounds(50, 160, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn.setForeground(Color.RED);
        warn2.setBounds(50, 180, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn2.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn2.setForeground(Color.RED);
        layeredPane.add(warn, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn2, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label2 = new JLabel("ลงทุน RMF อย่างเดียว");
        label2.setBounds(50, 240, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

        //จำนวน
        amount2 = new JLabel("300000");
        amount2.setBounds(500, 240, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount2.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount2, JLayeredPane.PALETTE_LAYER);

        //TextField for ลงทุน RMF อย่างเดียว
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
                validateInput();
            }
        });
        // f2.getDocument().addDocumentListener(documentListener);
        layeredPane.add(f2, JLayeredPane.PALETTE_LAYER);

        //รายละเอียด
        JLabel detail2 = new JLabel("*จำนวนเงินลงทุนแนะนำในการลดหย่อนภาษี*");
        detail2.setBounds(700, 280, 300, 100);
        detail2.setFont(new Font("Tahoma", Font.BOLD, 11));
        detail2.setForeground(Color.RED);
        layeredPane.add(detail2, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn3 = new JLabel("RMF 30 % ของรายได้ทั้งปี และรวมกับ");
        JLabel warn4 = new JLabel("กองทุนกลุ่มเกษียณ ไม่เกิน 500,000 บาท");
        warn3.setBounds(50, 270, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn3.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn3.setForeground(Color.RED);
        warn4.setBounds(50, 290, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn4.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn4.setForeground(Color.RED);
        layeredPane.add(warn3, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn4, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label3 = new JLabel("ลงทุน ThaiESG อย่างเดียว");
        label3.setBounds(50, 350, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label3, JLayeredPane.PALETTE_LAYER);

        //จำนวน
        amount3 = new JLabel("300000");
        amount3.setBounds(500, 350, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount3.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount3, JLayeredPane.PALETTE_LAYER);

        //TextField for เงินประกัน
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
                validateInput();
            }
        });
        // f3.getDocument().addDocumentListener(documentListener);
        layeredPane.add(f3, JLayeredPane.PALETTE_LAYER);

        //รายละเอียด
        JLabel detail3 = new JLabel("*จำนวนเงินลงทุนแนะนำในการลดหย่อนภาษี*");
        detail3.setBounds(700, 390, 300, 100);
        detail3.setFont(new Font("Tahoma", Font.BOLD, 11));
        detail3.setForeground(Color.RED);
        layeredPane.add(detail3, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn5 = new JLabel("ThaiESG30 % ของรายได้ทั้งปี ไม่เกิน 300,000 บาท");
        JLabel warn6 = new JLabel("และไม่รวมกับกองทุนกลุ่มเกษียณ");
        warn5.setBounds(50, 380, 400, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn5.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn5.setForeground(Color.RED);
        warn6.setBounds(50, 400, 400, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn6.setFont(new Font("Tahoma", Font.BOLD, 11));
        warn6.setForeground(Color.RED);
        layeredPane.add(warn5, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(warn6, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label4 = new JLabel("รวม");
        label4.setBounds(700, 420, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label4.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label4, JLayeredPane.PALETTE_LAYER);

        label5 = new JLabel("0");
        label5.setBounds(850, 420, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label5.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label5, JLayeredPane.PALETTE_LAYER);

        //หัวข้อ
        JLabel label7 = new JLabel("จำนวนที่คุณลงทุนได้สูงสุด (บาท)");
        label7.setBounds(340, 80, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label7.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label7, JLayeredPane.PALETTE_LAYER);

        JLabel label8 = new JLabel("จำนวนที่ต้องการจะลงทุน (บาท)");
        label8.setBounds(750, 80, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label8.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label8, JLayeredPane.PALETTE_LAYER);

        //หัวข้อภาษีที่ประหยัดไปได้
        JLabel label9 = new JLabel("ภาษีที่ประหยัดไปได้");
        label9.setBounds(50, 510, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label9.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label9, JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn7 = new JLabel("หลังลดหย่อน SSF/RMF/ThaiESG");
        warn7.setBounds(50, 530, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn7.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(warn7, JLayeredPane.PALETTE_LAYER);

        //เมื่อลงทุนสูงสุด
        JLabel label10 = new JLabel("เมื่อลงทุนสูงสุด");
        label10.setBounds(460, 470, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label10.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label10, JLayeredPane.PALETTE_LAYER);

        //จำนวน
        amount4 = new JLabel("0");
        amount4.setBounds(505, 510, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount4.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount4, JLayeredPane.PALETTE_LAYER);

        //เมื่อลงทุนตามจำนวนเงินของคุณ"เมื่อลงทุนตามจำนวนเงินของคุณ"
        JLabel label11 = new JLabel("ภาษีที่ต้องจ่ายหลังลงทุน");
        label11.setBounds(750, 600, 300, 50); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label11.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(label11, JLayeredPane.PALETTE_LAYER);

        amount5 = new JLabel("0");
        amount5.setBounds(770, 610, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount5.setFont(new Font("Tahoma", Font.BOLD, 24));
        layeredPane.add(amount5, JLayeredPane.PALETTE_LAYER);

        JLabel label12 = new JLabel("เมื่อลงทุนตามจำนวนเงินของคุณ");
        label12.setBounds(750, 470, 500, 100);
        label12.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(label12, JLayeredPane.PALETTE_LAYER);

        amount6 = new JLabel("0");
        amount6.setBounds(860, 510, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        amount6.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(amount6, JLayeredPane.PALETTE_LAYER);

        JLabel label13 = new JLabel("บาท");
        label13.setBounds(880, 635, 500, 50);
        label13.setFont(new Font("Tahoma", Font.BOLD, 20));
        layeredPane.add(label13, JLayeredPane.PALETTE_LAYER);
        add(layeredPane);
    }

   
    public void getSalary(int salaryYear1){
        this.SalaryYear = salaryYear1;
    }
    public void getTax(int Tax) {
        this.Tax = Tax;
        System.out.println("total tax page16: "+Tax);
    }
    public void getValue(int value){
        this.value=value;
        System.out.println("value for page16:"+value);
    }
    public void getPVD(int value){
        this.PVDvalue=value;
        System.out.println("PVD page16: "+PVDvalue);
        
    }
    public void getF4(int value){
        this.F4value=value;
        System.out.println("F4 page16: "+F4value);
        
    }
    public void getAllpage14value(int value){
        this.Page14value=value;
        System.out.println("Page14 value:" +Page14value);
        calculatedMax();
    }
    private void calculatedMax(){
        int Max800kbeforcal = PVDvalue+F4value+Page14value;
        newAf = MaxAf-Max800kbeforcal;
    }
    public void setamount4(String value){
        amount4.setText(value);
    }
    public void setamount5(String value){
        amount5.setText(value);
    }
    public void setamount6(String value){
        amount6.setText(value);
    }
    private void validateInput() {
        try {
            // อ่านค่าจากฟิลด์ f1, f2, f3 และกำหนดค่าเป็น 0 ถ้ายังไม่ได้กรอก
            int valueF1 = f1.getText().isEmpty() ? 0 : Integer.parseInt(f1.getText());
            int valueF2 = f2.getText().isEmpty() ? 0 : Integer.parseInt(f2.getText());
            int valueF3 = f3.getText().isEmpty() ? 0 : Integer.parseInt(f3.getText());
    
            // ตรวจสอบว่าไม่มีค่าใดติดลบ
            if (valueF1 < 0 || valueF2 < 0 || valueF3 < 0) {
                JOptionPane.showMessageDialog(null, "กรุณากรอกจำนวนเงินที่ไม่ติดลบ");
                return; // หยุดการทำงานถ้ามีค่าติดลบ
            }
    
            // ตรวจสอบลิมิตของแต่ละช่อง
            if (valueF1 > 200000) {
                valueF1 = 200000;
                f1.setText(String.valueOf(valueF1));
            }
            if (valueF2 > 300000) {
                valueF2 = 300000;
                f2.setText(String.valueOf(valueF2));
            }
            if (valueF3 > 300000) {
                valueF3 = 300000;
                f3.setText(String.valueOf(valueF3));
            }
    
            int totalValue = valueF1 + valueF2 + valueF3; // คำนวณค่ารวม
    
            // ตรวจสอบว่าค่ารวมไม่เกิน newAf
            if (totalValue > newAf) {
                // ถ้าเกิน newAf ให้ปรับค่าช่องสุดท้ายที่ถูกแก้ไขให้พอดีกับ newAf
                int diff = totalValue - newAf;
               if (f3.isFocusOwner() || (!f1.getText().isEmpty() && !f2.getText().isEmpty() && f3.getText().isEmpty())) {
                    valueF3 = Math.max(0, valueF3 - diff);
                    f3.setText(String.valueOf(valueF3));
                } else if (f2.isFocusOwner() || (!f1.getText().isEmpty() && f2.getText().isEmpty())) {
                    valueF2 = Math.max(0, valueF2 - diff);
                    f2.setText(String.valueOf(valueF2));
                } else {
                    valueF1 = Math.max(0, valueF1 - diff);
                    f1.setText(String.valueOf(valueF1));
                }
                totalValue = newAf; 
            }
            int difference = netIncome - totalValue;
            int TotalTaxaftercal;
            if (difference < 150000) {
                newTax = 0; // ไม่ต้องเสียภาษี
            } else if (difference > 150000 && difference <= 300000) {
                newTax = (int) ((difference - 150000) * 0.05); // คำนวณภาษี 5%
            } else if (difference > 300000 && difference <= 500000) {
                newTax = (int) ((difference - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 * 0.05)=7500
            } else if (difference > 500000 && difference <= 750000) {
                newTax = (int) ((difference - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 15% //200000*0.10-จะได้2หมื่นและนำ(150000 * 0.05)มาใช้ 
            } else if (difference > 750000 && difference <= 1000000) {
                newTax = (int) ((difference - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 20% //(250000 * 0.15)=37500
            }else if(difference >1000000 && difference<=2000000){
                newTax = (int) ((difference-1000000) * 0.25+ (250000 * 0.20) + (200000 * 0.15)+ (200000 * 0.10) +  (150000 * 0.05)+(150000 * 0.05));
            }else if(difference >2000000 && difference <=5000000){
                newTax = (int) ((difference-2000000)*0.30+ (1000000*0.25)+ (250000 * 0.20) + (200000 * 0.15) +(200000 * 0.10) + (150000 * 0.05)+(150000 * 0.05));  
            }else if(difference >5000000){
                newTax = (int) ((difference-5000000)*0.35+(3000000 * 0.30) +(1000000 * 0.25) +(250000 * 0.20) +(200000 * 0.15) +(200000 * 0.10) +(150000 * 0.05)+ (150000 * 0.05));
            }
            setamount5(String.valueOf(newTax));
            TotalTaxaftercal = Tax-newTax;
            setamount6(String.valueOf(TotalTaxaftercal));
            setamount4(String.valueOf(TotalTaxaftercal));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "กรุณากรอกตัวเลขเท่านั้น");
        }

    }
 
    public void getNet(int netIncome){
        this.netIncome=netIncome;
        System.out.println("netincome :"+netIncome);
        if (netIncome < 150000) {
            Tax = 0; // ไม่ต้องเสียภาษี
        } else if (netIncome > 150000 && netIncome <= 300000) {
            Tax = (int) ((netIncome - 150000) * 0.05); // คำนวณภาษี 5%
        } else if (netIncome > 300000 && netIncome <= 500000) {
            Tax = (int) ((netIncome - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 * 0.05)=7500
        } else if (netIncome > 500000 && netIncome <= 750000) {
            Tax = (int) ((netIncome - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 15% //200000*0.10-จะได้2หมื่นและนำ(150000 * 0.05)มาใช้ 
        } else if (netIncome > 750000 && netIncome <= 1000000) {
            Tax = (int) ((netIncome - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 20% //(250000 * 0.15)=37500
        }else if(netIncome >1000000 && netIncome<=2000000){
            Tax = (int) ((netIncome-1000000) * 0.25+ (250000 * 0.20) + (200000 * 0.15)+ (200000 * 0.10) +  (150000 * 0.05)+(150000 * 0.05));
        }else if(netIncome >2000000 && netIncome <=5000000){
            Tax = (int) ((netIncome-2000000)*0.30+ (1000000*0.25)+ (250000 * 0.20) + (200000 * 0.15) +(200000 * 0.10) + (150000 * 0.05)+(150000 * 0.05));  
        }else if(netIncome >5000000){
            Tax = (int) ((netIncome-5000000)*0.35+(3000000 * 0.30) +(1000000 * 0.25) +(250000 * 0.20) +(200000 * 0.15) +(200000 * 0.10) +(150000 * 0.05)+ (150000 * 0.05));
        }
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