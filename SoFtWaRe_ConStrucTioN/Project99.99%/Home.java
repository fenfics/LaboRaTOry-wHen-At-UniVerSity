import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Home extends JPanel {
    private String username;

    public Home(MainApplication mainApp) {
        setLayout(null);
        setBackground(Color.decode("#D8F5FF"));

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        // Home
        JLabel home = new JLabel("HOME");
        home.setBounds(483, 1, 200, 100);
        home.setFont(new Font("Tahoma", Font.BOLD, 42));
        home.setForeground(Color.BLACK);
        layeredPane.add(home, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม คำนวณภาษี
        JButton button5 = new JButton("คำนวณภาษี");
        button5.setFont(new Font("Tahoma", Font.BOLD, 15));
        button5.setBackground(Color.decode("#E5B6B3"));
        button5.setForeground(Color.WHITE);
        button5.setBounds(483, 222, 120, 50);
        layeredPane.add(button5, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม ประวัติการคำนวณภาษี
        JButton button6 = new JButton("ประวัติการคำนวณภาษี");
        button6.setFont(new Font("Tahoma", Font.BOLD, 15));
        button6.setBackground(Color.decode("#80C8CD"));
        button6.setForeground(Color.WHITE);
        button6.setBounds(435, 302, 220, 50);
        layeredPane.add(button6, JLayeredPane.PALETTE_LAYER);

        // ปุ่มกราฟการเปรียบเทียบภาษี
        JButton button7 = new JButton("กราฟการเปรียบเทียบภาษี");
        button7.setFont(new Font("Tahoma", Font.BOLD, 15));
        button7.setBackground(Color.decode("#80C8CD"));
        button7.setForeground(Color.WHITE);
        button7.setBounds(435, 382, 220, 50);
        layeredPane.add(button7, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม รายการลดหย่อน
        JButton button8 = new JButton("รายการลดหย่อน");
        button8.setFont(new Font("Tahoma", Font.BOLD, 15));
        button8.setBackground(Color.decode("#80C8CD"));
        button8.setForeground(Color.WHITE);
        button8.setBounds(455, 462, 180, 50);
        layeredPane.add(button8, JLayeredPane.PALETTE_LAYER);

        // กำหนดการทำงานให้ปุ่ม
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainApp.showPage("Salary"); // เรียก method ของ MainApplication
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // โหลดปีล่าสุดก่อนแสดงหน้าประวัติการคำนวณภาษี
                List<String> years = loadYearsForUsername(username);
                mainApp.setYearsForHistoryTax(years); // ส่งข้อมูลปีไปยังหน้า historyTax
                mainApp.showPage("historyTax");
            }
        });

        // ใน Home (เพิ่ม ActionListener ให้ปุ่มกราฟการเปรียบเทียบภาษี)
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // โหลดปีล่าสุดก่อนแสดงหน้าเปรียบเทียบภาษี
                List<String> years = loadYearsForUsername(username);
                mainApp.setYearsForCompareTax(years); // ส่งข้อมูลปีไปยังหน้า compareTax
                mainApp.showPage("compareTax");
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> years = loadYearsForUsername(username);
                mainApp.setYearsForPieChart(years); // ส่งข้อมูลปีไปยังหน้า pieChart
                mainApp.showPage("PieChart"); // เรียก method ของ MainApplication

            }
        });

        add(layeredPane);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> loadYearsForUsername(String username) {
        List<String> years = new ArrayList<>(); // ระบุชนิด List<String>

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("data.txt"), "UTF-8"))) {
            String line;
            boolean userFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(username)) {
                    userFound = true; // เจอชื่อผู้ใช้
                } else if (userFound && line.startsWith("ปี:")) {
                    // ดึงปีจากบรรทัดนี้
                    String year = line.split(",")[0].split(":")[1].trim();
                    years.add(year);
                } else if (userFound && line.trim().isEmpty()) {
                    break; // ออกจากลูปเมื่อเจอบรรทัดว่างหลังจากข้อมูลของผู้ใช้
                } else if (!line.startsWith("ปี:") && !line.trim().isEmpty()) {
                    userFound = false; // เจอชื่อผู้ใช้คนอื่น
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load year from file.: " + e.getMessage(), "\r\n" + "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Years loaded for " + username + ": " + years);

        return years;
    }

}

class DisplayGraphics extends JPanel {

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