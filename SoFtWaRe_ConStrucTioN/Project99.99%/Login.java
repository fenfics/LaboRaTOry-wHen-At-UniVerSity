import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;

public class Login extends JPanel {

    private static JTextField namet1;
    private static JPasswordField PIN2;
    private MainApplication mainApp;
    private String username;

    public Login(MainApplication mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }

    private void initializeUI() {
        setBackground(Color.decode("#D8F5FF"));
        setOpaque(true);
        setLayout(null);
        setPreferredSize(new Dimension(1133, 744));

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        add(layeredPane);

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        addLoginLabel(layeredPane);
        addFinishButton(layeredPane);
        addInputFields(layeredPane);
        addActionButtons(layeredPane);
    }

    private void addLoginLabel(JLayeredPane layeredPane) {
        JLabel home = new JLabel("Login");
        home.setBounds(489, 1, 200, 100);
        home.setFont(new Font("Tahoma", Font.BOLD, 42));
        home.setForeground(Color.BLACK);
        layeredPane.add(home, JLayeredPane.PALETTE_LAYER);
    }

    private void addFinishButton(JLayeredPane layeredPane) {
        JButton finishButton = new JButton("FINISH");
        finishButton.setBackground(Color.decode("#E5B6B3"));
        finishButton.setForeground(Color.WHITE);
        finishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        finishButton.setBounds(511, 622, 110, 40);
        finishButton.addActionListener(e -> login());
        layeredPane.add(finishButton, JLayeredPane.PALETTE_LAYER);
    }

    private void addInputFields(JLayeredPane layeredPane) {
        JLabel namel1 = new JLabel("Username");
        namel1.setBounds(272, 180, 200, 100);
        namel1.setFont(new Font("Tahoma", Font.BOLD, 24));
        namel1.setForeground(Color.BLACK);
        layeredPane.add(namel1, JLayeredPane.PALETTE_LAYER);

        namet1 = new JTextField();
        namet1.setFont(new Font("Tahoma", Font.BOLD, 24));
        namet1.setBounds(272, 260, 600, 50);
        layeredPane.add(namet1, JLayeredPane.PALETTE_LAYER);

        JLabel PIN1 = new JLabel("PIN");
        PIN1.setBounds(272, 310, 200, 100);
        PIN1.setFont(new Font("Tahoma", Font.BOLD, 24));
        PIN1.setForeground(Color.BLACK);
        layeredPane.add(PIN1, JLayeredPane.PALETTE_LAYER);

        PIN2 = new JPasswordField();
        PIN2.setFont(new Font("Tahoma", Font.BOLD, 24));
        PIN2.setBounds(272, 390, 600, 50);
        ((AbstractDocument) PIN2.getDocument()).setDocumentFilter(new LengthRestrictedDocument(6)); // จำกัดความยาวเป็น
                                                                                                    // 6 ตัว
        layeredPane.add(PIN2, JLayeredPane.PALETTE_LAYER);
    }

    private void addActionButtons(JLayeredPane layeredPane) {
        JButton button3 = new JButton("*กรณีเข้าใช้งานครั้งแรกกดที่นี่เพื่อสมัครบัญชี");
        button3.setFont(new Font("Tahoma", Font.BOLD, 15));
        button3.setBounds(260, 440, 400, 30);
        button3.setOpaque(false);
        button3.setContentAreaFilled(false);
        button3.setBorder(null);
        button3.addActionListener(e -> mainApp.showPage("PIN"));
        layeredPane.add(button3, JLayeredPane.PALETTE_LAYER);

        JButton button4 = new JButton("*ลืมรหัสผ่าน");
        button4.setFont(new Font("Tahoma", Font.BOLD, 15));
        button4.setBounds(782, 440, 90, 30);
        button4.setOpaque(false);
        button4.setContentAreaFilled(false);
        button4.setBorder(null);
        button4.addActionListener(e -> mainApp.showPage("Forgetpass"));
        layeredPane.add(button4, JLayeredPane.PALETTE_LAYER);
    }

    private void login() {
        username = namet1.getText();
        String password = new String(PIN2.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
        } else if (password.length() != 6) { // ตรวจสอบความยาว PIN
            JOptionPane.showMessageDialog(this, "PIN must be 6 digits number.");
        } else {
            if (checkPin(username, password)) {
                // ทำสิ่งที่ต้องการเมื่อ PIN ถูกต้อง
                JOptionPane.showMessageDialog(this, "Login successful! Welcome " + username + "!");
                sendUsernameToAllPage(username);
                mainApp.showPage("Home");

            } else {
                JOptionPane.showMessageDialog(this, "PIN is incorrect.");
            }
        }
    }

    private void sendUsernameToAllPage(String username) {
        mainApp.sendUsernametoPage15(username);
        mainApp.sendUsernameToPie(username);
        mainApp.sendUsernameToHome(username);
        mainApp.sendUsernameToCompare(username);
        mainApp.sendUsernameToHistory(username);
    }

    private boolean checkPin(String username, String pin) {
        try (BufferedReader br = new BufferedReader(new FileReader("alldata.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // ตัดคำว่า "Username: " และ "PIN: " ออก
                String[] data = line.split(","); // แยกข้อมูลด้วย ,
                if (data.length >= 2) {
                    String fileUsername = data[0].trim().replace("Username:", "").trim(); // ลบ "Username:" ออก
                    String filePin = data[1].trim().replace("PIN:", "").trim(); // ลบ "PIN:" ออก
                    if (fileUsername.equals(username) && filePin.equals(pin)) {
                        return true; // พบชื่อผู้ใช้และ PIN ที่ตรงกัน
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading user data: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // ไม่พบชื่อผู้ใช้และ PIN ที่ตรงกัน
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

    // คลาสสำหรับจำกัดความยาวของตัวอักษรในฟิลด์
    private static class LengthRestrictedDocument extends DocumentFilter {
        private final int maxLength;

        public LengthRestrictedDocument(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr)
                throws javax.swing.text.BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs)
                throws javax.swing.text.BadLocationException {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}