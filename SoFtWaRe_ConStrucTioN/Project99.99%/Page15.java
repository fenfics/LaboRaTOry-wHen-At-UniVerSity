import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.io.*;
import java.util.*;
public class Page15 extends JPanel {
    private MainApplication mainApp;
    private JLabel minilabel1,minilabel2;
    private int getTotalValue;
    private int salaryAfterExpenses;
    private int totalDeduction;
    private int totalValue;
    private int ToTalValue3;
    private int netIncome;
    private int tax;
    private int maxFunds= 1684000;
    private JLabel minilabel3,minilabel31,minilabel32,minilabel33,minilabel34,minilabel4;
    private String username;
    private String year,bonus,anothersalary;
    private int salaryyear;
    public Page15(MainApplication mainApp) {
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
                mainApp.showPage("page14");
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
                mainApp.sendNetIncomeCalculated(netIncome);
                mainApp.sendNetIncomeCalculatedPage17(netIncome);
                mainApp.sendTaxtoPage17(tax);
                mainApp.sendTaxTopage16(tax);
                mainApp.showPage("page16");
            }
        });
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // คำนวณภาษี
        JLabel family = new JLabel("คำนวณภาษี");
        family.setFont(new Font("Tahoma", Font.BOLD, 24));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label1 = new JLabel("รวมเงินได้สุทธิ");
        label1.setBounds(160,140, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label1.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(label1,JLayeredPane.PALETTE_LAYER);

        // จำนวนเงิน
        minilabel1 = new JLabel("0");
        minilabel1.setBounds(600,140, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        minilabel1.setFont(new Font("Tahoma",Font.BOLD, 24));
        
        layeredPane.add(minilabel1,JLayeredPane.PALETTE_LAYER);

        //บาท
        JLabel bath = new JLabel("บาท");
        bath.setBounds(850,140, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        bath.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(bath,JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label2 = new JLabel("ภาษีที่ต้องจ่าย");
        label2.setBounds(160,210, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label2.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(label2,JLayeredPane.PALETTE_LAYER);

         //ข้อกำหนด
        JLabel warn = new JLabel("(ก่อนลดหย่อนภาษีด้วย SSF/ RMF /ThaiESG)");
        warn.setBounds(160,240, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn.setFont(new Font("Tahoma",Font.BOLD, 11));
        layeredPane.add(warn,JLayeredPane.PALETTE_LAYER);

        //จำนวนเงิน
         minilabel2 = new JLabel("0");
        minilabel2.setBounds(600,210, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        minilabel2.setFont(new Font("Tahoma",Font.BOLD, 24));
       
        layeredPane.add(minilabel2,JLayeredPane.PALETTE_LAYER);

        //บาท
        JLabel bath2 = new JLabel("บาท");
        bath2.setBounds(850,210, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        bath2.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(bath2,JLayeredPane.PALETTE_LAYER);

        //หัวข้อเรื่อง
        JLabel label3 = new JLabel("ภาษีที่ลดหย่อน");
        label3.setBounds(160,330, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label3.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(label3,JLayeredPane.PALETTE_LAYER);

        //รายละเอียด
        JLabel detail = new JLabel("รายการ");
        JLabel detail2 = new JLabel("ครอบครัว");
        JLabel detail3 = new JLabel("กองทุนPVD / เงินประกัน / ที่อยู่อาศัย");
        JLabel detail4 = new JLabel("ประกัน");
        JLabel detail5 = new JLabel("กองทุนอื่นๆ");
        detail.setBounds(160,350, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        detail2.setBounds(250, 350, 300, 100);
        detail3.setBounds(250, 370, 300, 100);
        detail4.setBounds(250, 390, 300, 100);
        detail5.setBounds(250,410,300,100);
        detail.setFont(new Font("Tahoma",Font.BOLD, 11));
        detail2.setFont(new Font("Tahoma",Font.BOLD, 11));
        detail3.setFont(new Font("Tahoma",Font.BOLD, 11));
        detail4.setFont(new Font("Tahoma",Font.BOLD, 11));
        detail5.setFont(new Font("Tahoma", Font.BOLD, 11));
        layeredPane.add(detail,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(detail2,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(detail3,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(detail4,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(detail5,JLayeredPane.PALETTE_LAYER);

        //จำนวนเงิน
        minilabel3 = new JLabel("0");
         minilabel31 = new JLabel("0");
         minilabel32 = new JLabel("0");
         minilabel33 = new JLabel("0");
         minilabel34 = new JLabel("0");
        minilabel3.setBounds(600,330, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        minilabel31.setBounds(640, 350, 500, 100);
        minilabel32.setBounds(640, 370, 500, 100);
        minilabel33.setBounds(640, 390, 500, 100);
        minilabel34.setBounds(640, 410, 500, 100);
        minilabel3.setFont(new Font("Tahoma",Font.BOLD, 24));
        minilabel31.setFont(new Font("Tahoma",Font.BOLD, 11));
        minilabel32.setFont(new Font("Tahoma",Font.BOLD, 11));
        minilabel33.setFont(new Font("Tahoma",Font.BOLD, 11));
        minilabel34.setFont(new Font("Tahoma",Font.BOLD, 11));
        layeredPane.add(minilabel3,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(minilabel31,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(minilabel32,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(minilabel33,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(minilabel34,JLayeredPane.PALETTE_LAYER);

        //บาท
        JLabel bath3 = new JLabel("บาท");
        JLabel bath31 = new JLabel("บาท");
        JLabel bath32 = new JLabel("บาท");
        JLabel bath33 = new JLabel("บาท");
        JLabel bath34 = new JLabel("บาท");
        bath3.setBounds(850,330, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        bath31.setBounds(870,350, 500, 100);
        bath32.setBounds(870,370, 500, 100);
        bath33.setBounds(870,390, 500, 100);
        bath34.setBounds(870,410, 500, 100);
        bath3.setFont(new Font("Tahoma",Font.BOLD, 24));
        bath31.setFont(new Font("Tahoma",Font.BOLD, 11));
        bath32.setFont(new Font("Tahoma",Font.BOLD, 11));
        bath33.setFont(new Font("Tahoma",Font.BOLD, 11));
        bath34.setFont(new Font("Tahoma",Font.BOLD, 11));
        layeredPane.add(bath3,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(bath31,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(bath32,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(bath33,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(bath34,JLayeredPane.PALETTE_LAYER);

        //หัวข้อ
        JLabel label4 = new JLabel("ภาษีลดหย่อน คงเหลือ");
        label4.setBounds(160,450, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        label4.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(label4,JLayeredPane.PALETTE_LAYER);

        //ข้อกำหนด
        JLabel warn2 = new JLabel("แนะนำการลดหย่อนเพิ่ม click NEXT");
        warn2.setBounds(160,470, 300, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        warn2.setFont(new Font("Tahoma",Font.BOLD, 11));
        layeredPane.add(warn2,JLayeredPane.PALETTE_LAYER);

        //จำนวนเงิน
        minilabel4 = new JLabel("0");
        minilabel4.setBounds(600,450, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        minilabel4.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(minilabel4,JLayeredPane.PALETTE_LAYER);
            
        //บาท
        JLabel bath4 = new JLabel("บาท");
        bath4.setBounds(850,450, 500, 100); //ปรับ widthเพื่อให้ sizeข้อความใหญ่ขึ้น
        bath4.setFont(new Font("Tahoma",Font.BOLD, 24));
        layeredPane.add(bath4,JLayeredPane.PALETTE_LAYER);
       
        add(layeredPane);
        
    }
    
    public String getMinilabel1Value() {
        return minilabel1.getText();
    }
    
    public void setMinilabel1Value(String value) {
        try {
            // ตรวจสอบว่า value เป็นตัวเลขหรือไม่ก่อนตั้งค่า
            Double.parseDouble(value.replace(",", "").trim());
            minilabel1.setText(value); // ตั้งค่าถูกต้อง
            
        } catch (NumberFormatException e) {
            minilabel1.setText("0");
        }
    }
    
    // New method to update minilabel1
    public void updateMinilabel1(String value) {
        minilabel1.setText(value);
    }
    public void updateMinilabel3(String value) {
        minilabel3.setText(value);
    }
    public void updateMinilabel31(String value) {
        minilabel31.setText(value);
    }
    public void updateMinilabel32(String value) {
        minilabel32.setText(value);
    }
    public void updateMinilabel33(String value) {
        minilabel33.setText(value);
    }
    public void updateMinilabel34(String value) {
        minilabel34.setText(value);
    }
    public void updateMinilabel4(String value){
        minilabel4.setText(value);
    }
    public void setMinilabel2Value(String value) {
        try {
            // ลบเครื่องหมายคั่นพัน (เช่น ",") ออกจาก string ก่อนทำการแปลงเป็นตัวเลข
            double doubleValue = Double.parseDouble(value.replace(",", "").trim());
            
            // แปลงเป็น int เพื่อให้แสดงผลในรูปแบบจำนวนเต็ม
            int intValue = (int) doubleValue;
    
            // ตั้งค่าลงใน minilabel2 เป็น string ที่แปลงแล้ว
            minilabel2.setText(String.valueOf(intValue)); 
        } catch (NumberFormatException e) {
            // หากมีข้อผิดพลาดในการแปลง จะตั้งค่าเป็น "0" แทน
            minilabel2.setText("0");
        }
    }
    public void receiveData(int salaryAfterExpenses, int totalDeduction, int totalValue, int getTotalValue, int ToTalValue3, int netIncome) {
        File file = new File("data.txt");
        List<String> fileContent = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        boolean userFound = false;
        this.salaryAfterExpenses = salaryAfterExpenses;
        this.totalDeduction = totalDeduction;
        updateMinilabel31(String.valueOf(totalDeduction));
        this.totalValue = totalValue;
        updateMinilabel32(String.valueOf(totalValue));
        this.getTotalValue = getTotalValue;
        updateMinilabel33(String.valueOf(getTotalValue));
        this.ToTalValue3 = ToTalValue3;
        updateMinilabel34(String.valueOf(ToTalValue3));
        this.netIncome = netIncome;
        //ลดหย่อนไปได้ทั้งหมด จาก ครอบครัว,กองทุน,ประกัน,กองทุนอื่นๆ
        int TotalDeduction = totalDeduction + totalValue + getTotalValue + ToTalValue3;
        updateMinilabel3(String.valueOf(TotalDeduction));
        int DeductionStillleft = maxFunds-TotalDeduction;
        updateMinilabel4(String.valueOf(DeductionStillleft));
        int netIncomeCalculated = salaryAfterExpenses - totalDeduction - totalValue - getTotalValue - ToTalValue3;
        // คำนวณภาษีที่ต้องจ่าย
        if (netIncome < 150000) {
            tax = 0; // ไม่ต้องเสียภาษี
        } else if (netIncome > 150000 && netIncome <= 300000) {
            tax = (int) ((netIncome - 150000) * 0.05); // คำนวณภาษี 5%
        } else if (netIncome > 300000 && netIncome <= 500000) {
            tax = (int) ((netIncome - 300000) * 0.10 + (150000 * 0.05)); // คำนวณภาษี 10% //(150000 * 0.05)=7500
        } else if (netIncome > 500000 && netIncome <= 750000) {
            tax = (int) ((netIncome - 500000) * 0.15 + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 15% //200000*0.10-จะได้2หมื่นและนำ(150000 * 0.05)มาใช้ 
        } else if (netIncome > 750000 && netIncome <= 1000000) {
            tax = (int) ((netIncome - 750000) * 0.20 + (250000 * 0.15) + (200000 * 0.10) + (150000 * 0.05)); // คำนวณภาษี 20% //(250000 * 0.15)=37500
        }else if(netIncome >1000000 && netIncome<=2000000){
            tax = (int) ((netIncome-1000000) * 0.25+ (250000 * 0.20) + (200000 * 0.15)+ (200000 * 0.10) +  (150000 * 0.05)+(150000 * 0.05));
        }else if(netIncome >2000000 && netIncome <=5000000){
            tax = (int) ((netIncome-2000000)*0.30+ (1000000*0.25)+ (250000 * 0.20) + (200000 * 0.15) +(200000 * 0.10) + (150000 * 0.05)+(150000 * 0.05));  
        }else if(netIncome >5000000){
            tax = (int) ((netIncome-5000000)*0.35+(3000000 * 0.30) +(1000000 * 0.25) +(250000 * 0.20) +(200000 * 0.15) +(200000 * 0.10) +(150000 * 0.05));
        }
        setMinilabel2Value(String.valueOf(tax));
        updateMinilabel1(String.valueOf(netIncomeCalculated));
        System.out.println("Total otherFunds:" +ToTalValue3);
        System.out.println("Total tax: " + tax);
       
       try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // ตรวจสอบว่าชื่อผู้ใช้อยู่ในบรรทัดนี้หรือไม่
                if (line.startsWith(username)) {
                    userFound = true;
                    // เขียนข้อมูลใหม่ใต้บรรทัดนี้
                    lines.add(line);  // เพิ่มบรรทัดชื่อผู้ใช้เดิม
                    lines.add("ปี: " + year + ", เงินเดือนรายปี : " + salaryyear + ", โบนัสเงินเดือน : " + bonus + ", รายได้อื่นๆ : " + anothersalary + ", ภาษีที่ลดหย่อน : " + TotalDeduction + ", ภาษีที่ต้องจ่าย : " + tax +
                            ", ลดหย่อนครอบครัว : " + totalDeduction + ", ลดหย่อนกองทุน : " + totalValue +
                            ", ลดหย่อนประกัน : " + getTotalValue + ", ลดหย่อนอื่นๆ: " + ToTalValue3 +
                            ", ภาษีที่ลดหย่อนคงเหลือ : " + DeductionStillleft);
                } else {
                    lines.add(line);  // เพิ่มบรรทัดเดิมที่ไม่ใช่ชื่อผู้ใช้ปัจจุบัน
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ถ้าไม่พบผู้ใช้งาน ให้เพิ่มข้อมูลใหม่
        if (!userFound) {
           System.out.println("ไม่พบผู้ใช้");
        }

        // เขียนข้อมูลทั้งหมดกลับไปที่ไฟล์
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public void getUsername(String username){
        this.username=username;
        System.out.println(username);
    }
    public void getData(String year,int salaryyear,String bonus,String anothersalary){
        this.year=year;
        this.salaryyear=salaryyear;
        this.bonus=bonus;
        this.anothersalary=anothersalary;
        System.out.println(year);
        System.out.println(salaryyear);
        System.out.println(bonus);
        System.out.println(anothersalary);
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