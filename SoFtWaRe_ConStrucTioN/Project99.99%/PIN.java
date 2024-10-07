import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

public class PIN extends JPanel {

    private static JTextField nameField;
    private static JPasswordField pinField;
    private MainApplication mainApp;

    public PIN(MainApplication mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);
        setBackground(Color.decode("#D8F5FF"));

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        add(layeredPane);

        // เพิ่มเส้นที่ด้านบน
        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        addHeaderLabel(layeredPane);
        addInputFields(layeredPane);
        addFinishButton(layeredPane);
    }

    private void addHeaderLabel(JLayeredPane layeredPane) {
        JLabel accountLabel = new JLabel("สร้างบัญชี");
        accountLabel.setBounds(489, 1, 200, 100);
        accountLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        accountLabel.setForeground(Color.BLACK);
        layeredPane.add(accountLabel, JLayeredPane.PALETTE_LAYER);
    }

    private void addInputFields(JLayeredPane layeredPane) {
        // ชื่อ-นามสกุล
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setBounds(272, 180, 200, 100);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        nameLabel.setForeground(Color.BLACK);
        layeredPane.add(nameLabel, JLayeredPane.PALETTE_LAYER);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.BOLD, 24));
        nameField.setBounds(272, 260, 600, 50);
        layeredPane.add(nameField, JLayeredPane.PALETTE_LAYER);

        // PIN
        JLabel pinLabel = new JLabel("PIN 6 หลัก");
        pinLabel.setBounds(272, 310, 200, 100);
        pinLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        pinLabel.setForeground(Color.BLACK);
        layeredPane.add(pinLabel, JLayeredPane.PALETTE_LAYER);

        pinField = new JPasswordField();
        pinField.setFont(new Font("Tahoma", Font.BOLD, 24));
        pinField.setBounds(272, 390, 600, 50);
        ((AbstractDocument) pinField.getDocument()).setDocumentFilter(new LengthRestrictedDocument(6)); // จำกัดความยาวเป็น
                                                                                                        // 6 ตัว
        layeredPane.add(pinField, JLayeredPane.PALETTE_LAYER);
    }

    private void addFinishButton(JLayeredPane layeredPane) {
        JButton finishButton = new JButton("Finish");
        finishButton.setBackground(Color.decode("#E5B6B3"));
        finishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        finishButton.setForeground(Color.WHITE);
        finishButton.setBounds(511, 622, 110, 40);
        finishButton.addActionListener(e -> handleFinishButton());
        layeredPane.add(finishButton, JLayeredPane.PALETTE_LAYER);
    }

    private boolean validateInput(String name, String pin) {
        if (name.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out the information completely.");
            return false;
        }

        if (pin.length() != 6) { // ตรวจสอบว่าความยาวของ PIN ต้องเท่ากับ 6
            JOptionPane.showMessageDialog(this, "PIN must be 6 digits number.");
            return false;
        }

        try {
            Integer.parseInt(pin);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "PIN must be numbers only.");
            return false;
        }

        return true;
    }

    private void handleFinishButton() {
        String name = nameField.getText();
        String pin = new String(pinField.getPassword());

        if (validateInput(name, pin)) {
            if (addAccountToFile(name, pin) && addUsernameToDataFile(name)) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                mainApp.showPage("Login");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Account created successfully! Unable to create account. Please try again.");
            }
        }
    }

    private boolean addAccountToFile(String name, String pin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alldata.txt", true))) {
            writer.write("Username: " + name + ", PIN: " + pin);
            writer.newLine();
            System.out.println("Finished creating account in alldata.txt");
            return true;
        } catch (IOException e) {
            System.out.println("Failed to create account in alldata.txt");
            e.printStackTrace();
            return false;
        }
    }

    private boolean addUsernameToDataFile(String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true))) {
            writer.write(name);
            writer.newLine();
            System.out.println("Finished adding username to data.txt");
            return true;
        } catch (IOException e) {
            System.out.println("Failed to add username to data.txt");
            e.printStackTrace();
            return false;
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

    // คลาสสำหรับจำกัดความยาวของตัวอักษรในฟิลด์
    private class LengthRestrictedDocument extends DocumentFilter {
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