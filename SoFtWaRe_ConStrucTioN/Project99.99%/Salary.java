import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Salary extends JPanel {

    private static JTextField sala2;
    private static JTextField bonus2;
    private static JTextField free2;
    private MainApplication mainApp;
    private int salaryYear;
    private int salaryYear1;
    private JTextField yearTextField;
    private String year, bonus, freelance;

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
        JButton button = new JButton("HOME");
        button.setBackground(Color.decode("#80C8CD"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setBounds(20, 20, 120, 50);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        JLabel Title = new JLabel("รายรับ");
        Title.setFont(new Font("Tahoma", Font.BOLD, 32));
        Title.setBounds(160, 15, 1000, 50);
        layeredPane.add(Title, JLayeredPane.PALETTE_LAYER);

        JTextField yearTextField = new JTextField();
        yearTextField.setFont(new Font("Tahoma", Font.BOLD, 28));
        yearTextField.setBounds(272, 540, 600, 50);
        layeredPane.add(yearTextField, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // กล่องเงินเดือน
        JLabel sala1 = new JLabel(" เงินเดือน(บาท)");
        sala1.setBounds(272, 100, 200, 100);
        sala1.setFont(new Font("Tahoma", Font.BOLD, 24));
        sala1.setForeground(Color.BLACK);
        layeredPane.add(sala1, JLayeredPane.PALETTE_LAYER);

        sala2 = new JTextField();
        sala2.setFont(new Font("Tahoma", Font.BOLD, 28));
        sala2.setBounds(272, 180, 600, 50);
        sala2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = sala2.getText();
                if (!text.matches("\\d+")) {
                    sala2.setText(text.replaceAll("[^\\d]", ""));
                }
            }
        });
        layeredPane.add(sala2, JLayeredPane.PALETTE_LAYER);

        // กล่องโบนัส(บาท)
        JLabel bonus1 = new JLabel(" โบนัส(บาท)");
        bonus1.setBounds(272, 220, 200, 100);
        bonus1.setFont(new Font("Tahoma", Font.BOLD, 24));
        bonus1.setForeground(Color.BLACK);
        layeredPane.add(bonus1, JLayeredPane.PALETTE_LAYER);

        bonus2 = new JTextField();
        bonus2.setFont(new Font("Tahoma", Font.BOLD, 28));
        bonus2.setBounds(272, 300, 600, 50);
        bonus2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = bonus2.getText();
                if (!text.matches("\\d+")) {
                    bonus2.setText(text.replaceAll("[^\\d]", ""));
                }
            }
        });
        layeredPane.add(bonus2, JLayeredPane.PALETTE_LAYER);

        // กล่องรายได้อื่นๆ
        JLabel free1 = new JLabel(" รายได้อื่นๆ เช่น การขายของออนไลน์, ฟรีแลนซ์ (บาท)");
        free1.setBounds(272, 340, 700, 100);
        free1.setFont(new Font("Tahoma", Font.BOLD, 24));
        free1.setForeground(Color.BLACK);
        layeredPane.add(free1, JLayeredPane.PALETTE_LAYER);

        free2 = new JTextField();
        free2.setFont(new Font("Tahoma", Font.BOLD, 28));
        free2.setBounds(272, 420, 600, 50);
        free2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = free2.getText();
                if (!text.matches("\\d+")) {
                    free2.setText(text.replaceAll("[^\\d]", ""));
                }
            }
        });
        layeredPane.add(free2, JLayeredPane.PALETTE_LAYER);

        // กล่องปี
        JLabel year1 = new JLabel(" ปี (พ.ศ.)");
        year1.setBounds(272, 460, 700, 100);
        year1.setFont(new Font("Tahoma", Font.BOLD, 24));
        year1.setForeground(Color.BLACK);
        layeredPane.add(year1, JLayeredPane.PALETTE_LAYER);

        // การทำงานของปุ่ม
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Home"); // เปลี่ยนหน้าไปที่ Home
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Home"); // เปลี่ยนหน้าไปที่หน้าเดิม
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String salary = sala2.getText().trim();
                bonus = bonus2.getText().trim();
                freelance = free2.getText().trim();
                year = yearTextField.getText().trim();
                int salaryYear = calculateSalary(); // คำนวณเงินเดือนก่อนหักค่าใช้จ่าย
                int salaryYear2 = calculateSalaryAfterExpenses(); // คำนวณเงินเดือนหลังหักค่าใช้จ่าย
                mainApp.sendSalaryToFamily(salaryYear2); // ส่งค่าไปยังหน้า Family
                mainApp.sendDataTopage15(year, salaryYear2, bonus, freelance);
                mainApp.sendSalaryYearTopage12(salaryYear1);
                mainApp.sendSalaryTopage13(salaryYear1);
                mainApp.sendSalaryYearToPage14(salaryYear1);
                mainApp.sendSalaryYeartoPage16(salaryYear1);
                // ดึงค่าจากช่องกรอกข้อมูล

                // ตรวจสอบว่าช่องกรอกข้อมูลไม่ว่างเปล่า
                if (salary.isEmpty() || year.isEmpty()) {
                    JOptionPane.showMessageDialog(Salary.this, "Please fill out the information completely.",
                            "Incomplete information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                mainApp.showPage("Family"); // เปลี่ยนหน้าไปหน้าถัดไป
            }
        });

        add(layeredPane); // เพิ่ม layeredPane เข้ากับ JPanel นี้
    }

    // เมธอดคำนวณเงินเดือน
    public int calculateSalary() {
        try {
            // ตรวจสอบว่า sala2 มีค่าหรือไม่ หากว่างให้กำหนดเป็น 0
            int salaryMonth = sala2.getText().trim().isEmpty() ? 0 : Integer.parseInt(sala2.getText().trim()); // เงินเดือน

            // ตรวจสอบว่า bonus2 มีค่าหรือไม่ หากว่างให้กำหนดเป็น 0
            int bonusMonth = bonus2.getText().trim().isEmpty() ? 0 : Integer.parseInt(bonus2.getText().trim()); // โบนัส

            // ตรวจสอบว่า free2 มีค่าหรือไม่ หากว่างให้กำหนดเป็น 0
            int freeMonth = free2.getText().trim().isEmpty() ? 0 : Integer.parseInt(free2.getText().trim()); // รายได้อื่นๆ

            // คำนวณเงินเดือนต่อปี (ก่อนหัก)
            salaryYear1 = (salaryMonth * 12);
            salaryYear = salaryYear1 + bonusMonth + freeMonth;
            System.out.println("รายได้ต่อปี (ก่อนหัก): " + salaryYear); // พิมพ์รายได้ต่อปี

            return salaryYear; // ส่งคืนค่า salaryYear (ก่อนหัก)

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "ข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    // เมธอดคำนวณเงินเดือนหลังหักค่าใช้จ่าย
    public int calculateSalaryAfterExpenses() {
        // คำนวณรายได้หลังหักค่าใช้จ่าย
        int salaryAfterExpenses = salaryYear - (salaryYear * 50 / 100); // หักค่าใช้จ่าย 50%
        System.out.println("รายได้หลังหักค่าใช้จ่าย: " + salaryAfterExpenses); // พิมพ์รายได้หลังหักค่าใช้จ่าย

        if (salaryAfterExpenses > 100000) {
            salaryYear -= 100000; // แก้ตรงนี้ให้ลบ 100,000 จาก salaryYear
            System.out.println("รายได้หลังหักเพิ่ม 100,000 บาท: " + salaryYear); // พิมพ์รายได้หลังหักเพิ่มจาก
                                                                                 // salaryYear
        } else {
            // หากไม่มากกว่า 100,000
            salaryAfterExpenses = 0; // กำหนดให้เป็น 0 หากน้อยกว่า 100,000
            System.out.println("รายได้หลังหักเพิ่ม (น้อยกว่า 1 แสน)");
        }

        // ส่งค่ากลับไปยังหน้า
        return salaryYear; // ส่งค่า salaryYear ไปยังหน้า 12
    }

    private boolean addUsernameToDataFile(String name) {
        ArrayList<String> data = new ArrayList<>();
        String year = yearTextField.getText().trim(); // ดึงค่าปี
        String salary = sala2.getText().trim(); // ดึงค่าเงินเดือน

        // ตรวจสอบว่าช่องกรอกข้อมูลไม่ว่างเปล่า
        if (name.isEmpty() || year.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "กรุณากรอกข้อมูลให้ครบถ้วน", "ข้อมูลไม่ครบ",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // เพิ่มข้อมูลใหม่เข้าไปใน ArrayList
        data.add(name + ", " + year + ", " + salary);

        // ทำการเรียงข้อมูลตามลำดับตัวอักษร
        Collections.sort(data);

        // เขียนข้อมูลทั้งหมด (ที่เรียงแล้ว) กลับไปในไฟล์
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            for (String entry : data) {
                writer.write(entry);
                writer.newLine();
            }
            System.out.println("Finished adding and sorting data in data.txt");
            return true;
        } catch (IOException e) {
            System.out.println("Failed to add and sort data in data.txt");
            e.printStackTrace();
            return false;
        }
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