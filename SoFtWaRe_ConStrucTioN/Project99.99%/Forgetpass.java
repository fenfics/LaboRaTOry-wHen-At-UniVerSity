import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Forgetpass extends JPanel {
    private static final String FILE_PATH = "alldata.txt";
    private static boolean found = false;
    private MainApplication mainApp;
    private JTextField nameField;
    private JPasswordField newPinField;

    public Forgetpass(MainApplication mainApp) {
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
        JLabel titleLabel = new JLabel("Reset PIN");
        titleLabel.setBounds(489, 1, 500, 100);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setForeground(Color.BLACK);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);
    }

    private void addInputFields(JLayeredPane layeredPane) {
        // ชื่อ-นามสกุล
        JLabel nameLabel = new JLabel("ชื่อ - นามสกุล");
        nameLabel.setBounds(272, 180, 250, 100);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        nameLabel.setForeground(Color.BLACK);
        layeredPane.add(nameLabel, JLayeredPane.PALETTE_LAYER);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.BOLD, 24));
        nameField.setBounds(272, 260, 600, 50);
        layeredPane.add(nameField, JLayeredPane.PALETTE_LAYER);

        // PIN ใหม่
        JLabel newPinLabel = new JLabel("รหัสใหม่ 6 หลัก");
        newPinLabel.setBounds(272, 310, 200, 100);
        newPinLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        newPinLabel.setForeground(Color.BLACK);
        layeredPane.add(newPinLabel, JLayeredPane.PALETTE_LAYER);

        newPinField = new JPasswordField();
        newPinField.setFont(new Font("Tahoma", Font.BOLD, 24));
        newPinField.setBounds(272, 390, 600, 50);
        layeredPane.add(newPinField, JLayeredPane.PALETTE_LAYER);
    }

    private void addFinishButton(JLayeredPane layeredPane) {
        JButton finishButton = new JButton("FINISH");
        finishButton.setBackground(Color.decode("#E5B6B3"));
        finishButton.setForeground(Color.WHITE);
        finishButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        finishButton.setBounds(511, 622, 110, 40);
        finishButton.addActionListener(e -> handleFinishButton());
        layeredPane.add(finishButton, JLayeredPane.PALETTE_LAYER);
    }

    private void handleFinishButton() {
        String name = nameField.getText().trim();
        String newPin = new String(newPinField.getPassword());

        if (validateInput(name, newPin)) {
            if (updatePin(name, newPin)) {
                JOptionPane.showMessageDialog(this, "Fished update");
                mainApp.showPage("Login");
            } else {
                JOptionPane.showMessageDialog(this, "Username not found in system");
            }
        }
    }

    private boolean validateInput(String name, String pin) {
        if (name.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all information.");
            return false;
        }

        if (pin.length() != 6) {
            JOptionPane.showMessageDialog(this, "PIN must be 6 digits.");
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

    private boolean updatePin(String name, String newPin) {
        found = false;
        List<String> lines = new ArrayList<>();
        try {
            Files.lines(Paths.get(FILE_PATH)).forEach(line -> {
                if (line.startsWith("Username: " + name + ",")) {
                    lines.add("Username: " + name + ", PIN: " + newPin);
                    found = true;
                } else {
                    lines.add(line);
                }
            });

            if (found) {
                Files.write(Paths.get(FILE_PATH), lines);
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating the data.");
        }

        return false;
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