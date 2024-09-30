import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class PIN {
    private static JTextField create2;  // ใช้ตัวแปรระดับคลาส
    private static JPasswordField confirm2;  // ใช้ตัวแปรระดับคลาส

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
        FinishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        FinishButton.setForeground(Color.WHITE);
        FinishButton.setBounds(511, 622, 110, 40);
        layeredPane.add(FinishButton, JLayeredPane.DEFAULT_LAYER);

        // CreateAccount
        JLabel Account = new JLabel("สร้างบัญชี");
        Account.setBounds(489, 1, 200, 100);
        Account.setFont(new Font("Tahoma", Font.BOLD, 42));
        Account.setForeground(Color.BLACK);
        layeredPane.add(Account, JLayeredPane.PALETTE_LAYER);

        // สร้างPIN
        JLabel create1 = new JLabel(" ชื่อ - นามสกุล");
        create1.setBounds(272, 180, 200, 100);
        create1.setFont(new Font("Tahoma", Font.BOLD, 24));
        create1.setForeground(Color.BLACK);
        layeredPane.add(create1, JLayeredPane.PALETTE_LAYER);

        create2 = new JTextField();  // ใช้ตัวแปรระดับคลาส
        create2.setFont(new Font("Tahoma", Font.BOLD, 24));
        create2.setBounds(272, 260, 600, 50);
        layeredPane.add(create2, JLayeredPane.PALETTE_LAYER);

        // ยืนยันPIN
        JLabel confirm1 = new JLabel(" PIN 6 หลัก");
        confirm1.setBounds(272, 310, 200, 100);
        confirm1.setFont(new Font("Tahoma", Font.BOLD, 24));
        confirm1.setForeground(Color.BLACK);
        layeredPane.add(confirm1, JLayeredPane.PALETTE_LAYER);

        confirm2 = new JPasswordField();  // ใช้ตัวแปรระดับคลาส
        confirm2.setFont(new Font("Tahoma", Font.BOLD, 24));
        confirm2.setBounds(272, 390, 600, 50);
        layeredPane.add(confirm2, JLayeredPane.PALETTE_LAYER);

        // การทำงานของปุ่ม Finish
        FinishButton.addActionListener(e -> {
            createAccount();
            frame.dispose();
            // เรียกเมธอด main ของคลาส Login หลังจากสร้างบัญชีเสร็จ
        });

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    // เมธอดสร้างบัญชี
    public static void createAccount() {
        String username = create2.getText();  // เข้าถึงตัวแปรระดับคลาส
        String password = new String(confirm2.getPassword());  // เข้าถึงตัวแปรระดับคลาส

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            return;
        }

        File file = new File(username + ".txt");
        if (file.exists()) {
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose another one.");
            return;
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(username + "\n" + password);
            JOptionPane.showMessageDialog(null, "Account created successfully.");
            Login.main(null);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error creating account.");
        }
    }
}