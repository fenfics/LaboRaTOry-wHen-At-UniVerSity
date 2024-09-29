import java.awt.*;
import java.io.*;
import javax.swing.*;

public class FOrgotPass {
    
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

        // ปุ่ม Finish
        JButton FinishButton = new JButton("Finish");
        FinishButton.setBackground(Color.decode("#E5B6B3"));
        FinishButton.setForeground(Color.WHITE);
        FinishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        FinishButton.setBounds(511, 622, 110, 40);
        layeredPane.add(FinishButton, JLayeredPane.DEFAULT_LAYER);

        // สร้าง PIN
        JLabel create1 = new JLabel("ชื่อ - นามสกุล");
        create1.setBounds(272, 180, 250, 100);
        create1.setFont(new Font("Tahoma", Font.BOLD, 24));
        create1.setForeground(Color.BLACK);
        layeredPane.add(create1, JLayeredPane.PALETTE_LAYER);

        JTextField create2 = new JTextField();
        create2.setFont(new Font("Tahoma", Font.BOLD, 24));
        create2.setBounds(272, 260, 600, 50);
        layeredPane.add(create2, JLayeredPane.PALETTE_LAYER);

        // ยืนยัน PIN
        JLabel confirm1 = new JLabel("รหัสใหม่ 6 หลัก");
        confirm1.setBounds(272, 310, 200, 100);
        confirm1.setFont(new Font("Tahoma", Font.BOLD, 24));
        confirm1.setForeground(Color.BLACK);
        layeredPane.add(confirm1, JLayeredPane.PALETTE_LAYER);

        JTextField confirm2 = new JTextField();
        confirm2.setFont(new Font("Tahoma", Font.BOLD, 24));
        confirm2.setBounds(272, 390, 600, 50);
        layeredPane.add(confirm2, JLayeredPane.PALETTE_LAYER);

        FinishButton.addActionListener(e -> {
            String username = create2.getText().trim();
            String newPassword = confirm2.getText().trim();

            if (newPassword.length() == 6) { // ตรวจสอบว่ารหัสใหม่มี 6 หลัก
                resetPassword(username, newPassword, "users.txt");
                JOptionPane.showMessageDialog(frame, "รีเซ็ตรหัสผ่านสำเร็จ!");
                frame.dispose();
                Login.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, "กรุณากรอกรหัสใหม่ให้ครบ 6 หลัก");
            }
        });

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    // ฟังก์ชันสำหรับรีเซ็ตรหัสผ่าน
    private static void resetPassword(String username, String newPassword, String filePath) {
        // อ่านข้อมูลผู้ใช้และรีเซ็ตรหัสผ่าน
        List<String> fileContent = new ArrayList<>();
        boolean userFound = false;

        // อ่านไฟล์และค้นหา username
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(username)) {
                    // เจอ username ที่ต้องการ ให้เก็บรหัสใหม่ลงในลิสต์
                    fileContent.add(userData[0] + "," + newPassword);
                    userFound = true;
                } else {
                    // เก็บข้อมูลเดิมที่ไม่ต้องแก้ไขลงลิสต์
                    fileContent.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("เกิดข้อผิดพลาดในการอ่านไฟล์: " + e.getMessage());
        }

        // ถ้าเจอ username ให้เขียนไฟล์ใหม่
        if (userFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String record : fileContent) {
                    bw.write(record);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("เกิดข้อผิดพลาดในการเขียนไฟล์: " + e.getMessage());
            }
        } else {
            System.out.println("ไม่พบผู้ใช้: " + username);
        }
    }
}
