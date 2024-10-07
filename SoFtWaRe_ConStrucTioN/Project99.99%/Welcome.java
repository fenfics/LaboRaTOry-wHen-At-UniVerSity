import java.awt.*;
import javax.swing.*;

public class Welcome extends JPanel {
    public Welcome(MainApplication mainApp) {
        setLayout(null);
        setBackground(Color.decode("#D8F5FF"));
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("La Belle Aurore", Font.ITALIC, 92));
        welcomeLabel.setForeground(Color.decode("#6390BA"));
        welcomeLabel.setBounds(450, 230, 500, 150);
        layeredPane.add(welcomeLabel, JLayeredPane.PALETTE_LAYER);

        JLabel welcomeLabel2 = new JLabel("Welcome");
        welcomeLabel2.setFont(new Font("La Belle Aurore", Font.ITALIC, 92));
        welcomeLabel2.setForeground(Color.decode("#9CC7EF"));
        welcomeLabel2.setBounds(452, 233, 500, 150);
        layeredPane.add(welcomeLabel2, JLayeredPane.PALETTE_LAYER);

        JLabel thaiLabel = new JLabel("โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ");
        thaiLabel.setFont(new Font("was@kaikhieaFont", Font.PLAIN, 40));
        thaiLabel.setBounds(385, 340, 700, 50);
        layeredPane.add(thaiLabel, JLayeredPane.PALETTE_LAYER);

        JButton startButton = new JButton("START");
        startButton.setBackground(Color.decode("#E5B6B3"));
        startButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setBounds(511, 400, 110, 40);

        startButton.addActionListener(e -> mainApp.showPage("Login"));

        layeredPane.add(startButton, JLayeredPane.PALETTE_LAYER);
        add(layeredPane);
    }
}