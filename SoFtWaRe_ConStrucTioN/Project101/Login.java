import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Login {
    private static JTextField namet1;  // Use class-level variables
    private static JPasswordField PIN2;

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

        // Login
        JLabel home = new JLabel("Login");
        home.setBounds(489, 1, 200, 100);
        home.setFont(new Font("Tahoma", Font.BOLD, 42));
        home.setForeground(Color.BLACK);
        layeredPane.add(home, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setBounds(427, 622, 120, 50);
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        // กล่องชื่อ+PIN
        JLabel namel1 = new JLabel("ชื่อ-นามสกุล");
        namel1.setBounds(272, 180, 200, 100);
        namel1.setFont(new Font("Tahoma", Font.BOLD, 24));
        namel1.setForeground(Color.BLACK);
        layeredPane.add(namel1, JLayeredPane.PALETTE_LAYER);

        namet1 = new JTextField();  // Use class-level variable here
        namet1.setFont(new Font("Tahoma", Font.BOLD, 24));
        namet1.setBounds(272, 260, 600, 50);
        layeredPane.add(namet1, JLayeredPane.PALETTE_LAYER);

        JLabel PIN1 = new JLabel("PIN");
        PIN1.setBounds(272, 310, 200, 100);
        PIN1.setFont(new Font("Tahoma", Font.BOLD, 24));
        PIN1.setForeground(Color.BLACK);
        layeredPane.add(PIN1, JLayeredPane.PALETTE_LAYER);

        PIN2 = new JPasswordField();  // Use class-level variable here
        PIN2.setFont(new Font("Tahoma", Font.BOLD, 24));
        PIN2.setBounds(272, 390, 600, 50);
        layeredPane.add(PIN2, JLayeredPane.PALETTE_LAYER);

        // กรณ๊
        JButton button3 = new JButton("*กรณีเข้าใช้งานครั้งแรกกดที่นี่เพื่อสมัครบัญชี");
        button3.setFont(new Font("Tahoma", Font.BOLD, 15));
        button3.setBounds(260, 440, 400, 30);
        button3.setOpaque(false);
        button3.setContentAreaFilled(false);
        button3.setBorder(null);
        layeredPane.add(button3, JLayeredPane.PALETTE_LAYER);

        // ลืมรหัสผ่าน
        JButton button4 = new JButton("*ลืมรหัสผ่าน");
        button4.setFont(new Font("Tahoma", Font.BOLD, 15));
        button4.setBounds(782, 440, 90, 30);
        button4.setOpaque(false);
        button4.setContentAreaFilled(false);
        button4.setBorder(null);
        layeredPane.add(button4, JLayeredPane.PALETTE_LAYER);

        // เพิ่ม ActionListener สำหรับปุ่ม START
        button2.addActionListener(e -> {
            login();
            frame.dispose();
        });

        button1.addActionListener(e -> {
            frame.dispose();
            Welcome.main(null);
        });

        button3.addActionListener(e -> {
            frame.dispose();
            PIN.main(null);
        });

        button4.addActionListener(e -> {
            frame.dispose();
            FOrgotPass.main(null);
        });

        frame.add(layeredPane);
        frame.setVisible(true);
    }

    public static void login() {
        String username = namet1.getText();
        String password = new String(PIN2.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            return;
        }

        File file = new File(username + ".txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Username does not exist.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String fileUsername = reader.readLine();
            String filePassword = reader.readLine();

            if (fileUsername.equals(username) && filePassword.equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                Home.main(null);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
                Login.main(null);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error logging in.");
        }
    }
}

// เส้น
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