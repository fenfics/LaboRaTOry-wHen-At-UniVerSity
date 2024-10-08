import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Salary extends JPanel {

    private static JTextField salaTextField;
    private static JTextField bonusTextField;
    private static JTextField freeTextField;
    private MainApplication mainApp;
    private int salaryYearBeforeExpense; // รายได้ก่อนหักค่าใช้จ่าย
    private int salaryYearAfterExpenses; // รายได้หลังหักค่าใช้จ่าย   
    private int salaryYear; // รายได้รายปี = รายเดือน*12
    private JTextField yearTextField; // กรอกปี
    private String year, bonus, freelance, salary;

    public Salary(MainApplication mainApp) {
        this.mainApp = mainApp;
        setBackground(Color.decode("#D8F5FF"));
        setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        // ปุ่ม Home
        JButton buttonHome = new JButton("HOME");
        buttonHome.setBackground(Color.decode("#80C8CD"));
        buttonHome.setForeground(Color.WHITE);
        buttonHome.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonHome.setBounds(20, 20, 120, 50);
        // การทำงานของปุ่ม Home
        buttonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Home"); // เปลี่ยนหน้าไปที่ Home
            }
        });
        layeredPane.add(buttonHome, JLayeredPane.PALETTE_LAYER);

        // รายรับ หัวข้อใหญ่เบิ้ม
        JLabel Title = new JLabel("รายรับ");
        Title.setFont(new Font("Tahoma", Font.BOLD, 32));
        Title.setBounds(160, 15, 1000, 50);
        layeredPane.add(Title, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton buttonBack = new JButton("BACK");
        buttonBack.setBackground(Color.decode("#E5B6B3"));
        buttonBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonBack.setForeground(Color.WHITE);
        buttonBack.setBounds(427, 622, 120, 50);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Home"); // กดกลับไปหน้าก่อนหน้า
            }
        });
        layeredPane.add(buttonBack, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton buttonNext = new JButton("NEXT");
        buttonNext.setBackground(Color.decode("#A5CBB0"));
        buttonNext.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonNext.setForeground(Color.WHITE);
        buttonNext.setBounds(587, 622, 120, 50);
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salary = salaTextField.getText().trim();
                bonus = bonusTextField.getText().trim();
                freelance = freeTextField.getText().trim();
                year = yearTextField.getText().trim();
                salaryYearBeforeExpense = calculateSalary(); // คำนวณเงินเดือนก่อนหักค่าใช้จ่าย
                salaryYearAfterExpenses = calculateSalaryAfterExpenses(); // คำนวณเงินเดือนหลังหักค่าใช้จ่าย
        
                // ตรวจสอบว่าช่องกรอกข้อมูลไม่ว่างเปล่า
                if (salary.isEmpty() || year.isEmpty()) {
                    JOptionPane.showMessageDialog(Salary.this, "Please fill out the information completely.",
                            "Incomplete information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
        
                // เรียกใช้เมธอดส่งข้อมูลทั้งหมด
                sendAllData(year, salaryYearAfterExpenses, salaryYearBeforeExpense, bonus, freelance);
        
                // เปลี่ยนหน้าไปหน้าถัดไป
                mainApp.showPage("Family");
            }
        });        
        layeredPane.add(buttonNext, JLayeredPane.PALETTE_LAYER);

        // เงินเดือน(บาท)
        JLabel salaLable = new JLabel(" เงินเดือน(บาท)");
        salaLable.setBounds(272, 100, 200, 100);
        salaLable.setFont(new Font("Tahoma", Font.BOLD, 24));
        salaLable.setForeground(Color.BLACK);
        layeredPane.add(salaLable, JLayeredPane.PALETTE_LAYER);

        //กรอกเงินเดือน
        salaTextField = new JTextField();
        salaTextField.setFont(new Font("Tahoma", Font.BOLD, 28));
        salaTextField.setBounds(272, 180, 600, 50);
        salaTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = salaTextField.getText();
                if (!text.matches("\\d+")) {
                    salaTextField.setText(text.replaceAll("[^\\d]", ""));
                }
            }
        });
        layeredPane.add(salaTextField, JLayeredPane.PALETTE_LAYER);

        // กล่องโบนัส(บาท)
        JLabel bonusLable = new JLabel(" โบนัส(บาท)");
        bonusLable.setBounds(272, 220, 200, 100);
        bonusLable.setFont(new Font("Tahoma", Font.BOLD, 24));
        bonusLable.setForeground(Color.BLACK);
        layeredPane.add(bonusLable, JLayeredPane.PALETTE_LAYER);

        //กรอกโบนัส
        bonusTextField = new JTextField();
        bonusTextField.setFont(new Font("Tahoma", Font.BOLD, 28));
        bonusTextField.setBounds(272, 300, 600, 50);
        bonusTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = bonusTextField.getText();
                if (!text.matches("\\d+")) {
                    bonusTextField.setText(text.replaceAll("[^\\d]", ""));
                }
            }
        });
        layeredPane.add(bonusTextField, JLayeredPane.PALETTE_LAYER);

        // รายได้อื่นๆ เช่น การขายของออนไลน์, ฟรีแลนซ์ (บาท)
        JLabel freeLabel = new JLabel(" รายได้อื่นๆ เช่น การขายของออนไลน์, ฟรีแลนซ์ (บาท)");
        freeLabel.setBounds(272, 340, 700, 100);
        freeLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        freeLabel.setForeground(Color.BLACK);
        layeredPane.add(freeLabel, JLayeredPane.PALETTE_LAYER);

        //กรอกรายได้อื่นๆ เช่น การขายของออนไลน์, ฟรีแลนซ์ (บาท)
        freeTextField = new JTextField();
        freeTextField.setFont(new Font("Tahoma", Font.BOLD, 28));
        freeTextField.setBounds(272, 420, 600, 50);
        freeTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = freeTextField.getText();
                if (!text.matches("\\d+")) {
                    freeTextField.setText(text.replaceAll("[^\\d]", ""));
                }
            }
        });
        layeredPane.add(freeTextField, JLayeredPane.PALETTE_LAYER);

        // ปี (พ.ศ.)
        JLabel year1 = new JLabel(" ปี (พ.ศ.)");
        year1.setBounds(272, 460, 700, 100);
        year1.setFont(new Font("Tahoma", Font.BOLD, 24));
        year1.setForeground(Color.BLACK);
        layeredPane.add(year1, JLayeredPane.PALETTE_LAYER);
        add(layeredPane); 

        // กรอกปี (พ.ศ.)
        yearTextField = new JTextField();
        yearTextField.setFont(new Font("Tahoma", Font.BOLD, 28));
        yearTextField.setBounds(272, 540, 600, 50);
        layeredPane.add(yearTextField, JLayeredPane.PALETTE_LAYER);
    }

    // คำนวณเงินเดือนก่อนหักค่าใช้จ่าย
    public int calculateSalary() {
        try {
            int salaryMonth = Integer.parseInt(salary);
            int bonusMonth = bonus.isEmpty() ? 0 : Integer.parseInt(bonus);
            int freeMonth = freelance.isEmpty() ? 0 : Integer.parseInt(freelance);
            // คำนวณเงินเดือนต่อปี (ก่อนหัก)
            salaryYear = (salaryMonth * 12);
            salaryYearBeforeExpense = salaryYear + bonusMonth + freeMonth;
            System.out.println("รายได้ต่อปี (ก่อนหัก) : " + salaryYearBeforeExpense); // พิมพ์รายได้ต่อปี

            return salaryYearBeforeExpense; // ส่งคืนค่า salaryYear (ก่อนหัก)

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "ข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    // เมธอดคำนวณเงินเดือนหลังหักค่าใช้จ่าย
    public int calculateSalaryAfterExpenses() {
        // คำนวณรายได้หลังหักค่าใช้จ่าย
        int Expenses = salaryYearBeforeExpense * 50 / 100 ; // หักค่าใช้จ่าย 50%
        System.out.println("ค่าใช้จ่าย 50% : " + Expenses); // พิมพ์รายได้หลังหักค่าใช้จ่าย

        //ถ้า หักค่าใช้จ่าย 50% มากกว่า 100000 ให้หักแค่ 100000
        if (Expenses > 100000) {
            salaryYearAfterExpenses = salaryYearBeforeExpense - 100000; // แก้ตรงนี้ให้ลบ 100,000 จาก salaryYear
            System.out.println("รายได้หลังหักสูงสุด 100,000 บาท : " + salaryYearAfterExpenses); // พิมพ์รายได้หลังหักเพิ่มจาก
                                                                                 // salaryYear
        } else { // <= 100,000
            salaryYearAfterExpenses = salaryYearBeforeExpense - Expenses; 
            System.out.println("รายได้หลังหักเพิ่ม " +Expenses+ " บาท : " + salaryYearAfterExpenses);
        }
        // ส่งค่ากลับไปยังหน้า
        return salaryYearAfterExpenses; // ส่งค่า salaryYear ไปยังหน้า 12
    }

    // เมธอดสำหรับการส่งข้อมูลทั้งหมดไปยังหน้าต่างๆ ที่ต้องการ
    public void sendAllData(String year, int salaryAfterExpenses, int salaryYear, String bonus, String freelance) {
        mainApp.sendSalaryToFamily(salaryAfterExpenses); // ส่งรายได้หลังหักค่าใช้จ่ายไปยังหน้า Family
        mainApp.sendDataTopage15(year, salaryAfterExpenses, bonus, freelance); // ส่งข้อมูลไปยังหน้า 15
        mainApp.sendSalaryYearTopage12(salaryYear); // ส่งรายได้รายปีไปยังหน้า 12
        mainApp.sendSalaryTopage13(salaryYear); // ส่งรายได้รายปีไปยังหน้า 13
        mainApp.sendSalaryYearToPage14(salaryYear); // ส่งรายได้รายปีไปยังหน้า 14
        mainApp.sendSalaryYeartoPage16(salaryYear); // ส่งรายได้รายปีไปยังหน้า 16
}


    // คลาสสำหรับวาดกราฟิก
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