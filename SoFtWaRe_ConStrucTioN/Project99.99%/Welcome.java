import java.awt.*;
import javax.swing.*;

public class Welcome extends JPanel {
    
    public Welcome(MainApplication mainApp) {
        // ตั้งค่า Layout และสีพื้นหลัง
        setLayout(null);
        setBackground(Color.decode("#D8F5FF"));
        
        //JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        
        // JLabel แสดงข้อความ "Welcome" 
        JLabel welcomeLabel = createWelcomeLabel("#6390BA", 450, 230);
        layeredPane.add(welcomeLabel, JLayeredPane.PALETTE_LAYER);

        // JLabel เงา "Welcome" 
        JLabel welcomeLabel2 = createWelcomeLabel("#9CC7EF", 452, 233);
        layeredPane.add(welcomeLabel2, JLayeredPane.PALETTE_LAYER);

        // JLabel โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ
        JLabel thaiLabel = new JLabel("โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ");
        thaiLabel.setFont(new Font("was@kaikhieaFont", Font.PLAIN, 40));
        thaiLabel.setBounds(385, 340, 700, 50);
        layeredPane.add(thaiLabel, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม START
        JButton startButton = createStartButton(mainApp);
        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);

        // เพิ่ม JLayeredPane ลงใน JPanel
        add(layeredPane);
    }

    // สร้าง JLabel "Welcome"
    private JLabel createWelcomeLabel(String colorCode, int x, int y) {
        JLabel label = new JLabel("Welcome");
        label.setFont(new Font("La Belle Aurore", Font.ITALIC, 92));
        label.setForeground(Color.decode(colorCode));
        label.setBounds(x, y, 500, 150);
        return label;
    }

    // สร้างปุ่ม START
    private JButton createStartButton(MainApplication mainApp) {
        JButton button = new JButton("START");
        button.setBackground(Color.decode("#E5B6B3"));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBounds(511, 400, 110, 40);
        button.addActionListener(e -> mainApp.showPage("Login"));
        return button;
    }
}