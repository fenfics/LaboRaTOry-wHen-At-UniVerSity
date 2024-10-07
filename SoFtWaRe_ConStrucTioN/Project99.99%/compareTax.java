import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.util.Collections;

public class compareTax extends JPanel {
    private MainApplication mainApp;
    private String username;
    private JComboBox<String> yearComboBox1;
    private JComboBox<String> yearComboBox2;
    private JTextArea taxDataArea1;
    private JTextArea taxDataArea2;

    public compareTax(MainApplication mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }

    private void initializeUI() {
        setSize(1133, 744);
        setLayout(null);
        setBackground(Color.decode("#D8F5FF"));
        setOpaque(true);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        layeredPane.setOpaque(true);
        layeredPane.setBackground(Color.decode("#D8F5FF"));
        add(layeredPane);

        JLabel label = new JLabel("การเปรียบเทียบภาษี");
        label.setFont(new Font("Tahoma", Font.BOLD, 32));
        label.setBounds(160, 15, 1000, 50);
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);

        // taxDataArea1 พื้นหลัง
        JPanel taxDataArea1Background = new JPanel();
        taxDataArea1Background.setBounds(70, 250, 460, 300); // เพิ่มความสูงที่นี่
        taxDataArea1Background.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taxDataArea1Background.setLayout(null);
        taxDataArea1Background.setBackground(Color.WHITE);
        layeredPane.add(taxDataArea1Background, JLayeredPane.DEFAULT_LAYER);

        taxDataArea1 = new JTextArea();
        taxDataArea1.setFont(new Font("Tahoma", Font.PLAIN, 19));
        taxDataArea1.setEditable(false);
        taxDataArea1.setBounds(10, 50, 440, 240); // เพิ่มความสูงของ JTextArea ให้พอดีกับความสูงใหม่
        taxDataArea1Background.add(taxDataArea1);

        // taxDataArea2 พื้นหลัง
        JPanel taxDataArea2Background = new JPanel();
        taxDataArea2Background.setBounds(600, 250, 460, 300); // เพิ่มความสูงที่นี่
        taxDataArea2Background.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taxDataArea2Background.setLayout(null);
        taxDataArea2Background.setBackground(Color.WHITE);
        layeredPane.add(taxDataArea2Background, JLayeredPane.DEFAULT_LAYER);

        taxDataArea2 = new JTextArea();
        taxDataArea2.setFont(new Font("Tahoma", Font.PLAIN, 19));
        taxDataArea2.setEditable(false);
        taxDataArea2.setBounds(10, 50, 440, 240); // เพิ่มความสูงของ JTextArea ให้พอดีกับความสูงใหม่
        taxDataArea2Background.add(taxDataArea2);

        // Update yearComboBox1
        yearComboBox1 = new JComboBox<>();
        yearComboBox1.setBounds(200, 200, 200, 40);
        yearComboBox1.setBackground(Color.WHITE);
        yearComboBox1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        yearComboBox1.setOpaque(true);
        yearComboBox1.addActionListener(e -> {
            if (yearComboBox1.getSelectedItem() != null) {
                String selectedYear = yearComboBox1.getSelectedItem().toString();
                updateTaxData(taxDataArea1, selectedYear);
            } else {
                taxDataArea1.setText(" ");
            }
        });
        layeredPane.add(yearComboBox1, JLayeredPane.PALETTE_LAYER);

        // Update yearComboBox2
        yearComboBox2 = new JComboBox<>();
        yearComboBox2.setBounds(720, 200, 200, 40);
        yearComboBox2.setBackground(Color.WHITE);
        yearComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        yearComboBox2.setOpaque(true);
        yearComboBox2.addActionListener(e -> {
            if (yearComboBox2.getSelectedItem() != null) {
                String selectedYear = yearComboBox2.getSelectedItem().toString();
                updateTaxData(taxDataArea2, selectedYear);
            } else {
                taxDataArea2.setText(" ");
            }
        });
        layeredPane.add(yearComboBox2, JLayeredPane.PALETTE_LAYER);

        // Home button
        JButton homeButton = new JButton("HOME");
        homeButton.setBackground(Color.decode("#80C8CD"));
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBounds(20, 20, 120, 50);
        homeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        homeButton.addActionListener(e -> mainApp.showPage("Home"));
        layeredPane.add(homeButton, JLayeredPane.PALETTE_LAYER);

        // Compare button
        JButton compareButton = new JButton("COMPARE");

        compareButton.setBackground(Color.decode("#A5CBB0"));
        compareButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        compareButton.setForeground(Color.WHITE);
        compareButton.setBounds(500, 622, 120, 50);
        compareButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username == null || username.isEmpty()) {
                    JOptionPane.showMessageDialog(compareTax.this, "Error: Username is not set.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String year1 = (String) yearComboBox1.getSelectedItem();
                String year2 = (String) yearComboBox2.getSelectedItem();

                if (year1 == null || year2 == null) {
                    JOptionPane.showMessageDialog(compareTax.this, "Please select both years for comparison.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create and show the LineChart
                SwingUtilities.invokeLater(() -> {
                    LineChart lineChart = new LineChart(mainApp, username, year1, year2);
                    lineChart.setVisible(true);

                    // Close the current frame
                    Window window = SwingUtilities.getWindowAncestor(compareTax.this);
                    if (window instanceof JFrame) {
                        ((JFrame) window).dispose();
                    }
                });
            }
        });
        layeredPane.add(compareButton, JLayeredPane.PALETTE_LAYER);

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);
    }

    public void setYears(List<String> years) {
        // เรียงลำดับปีจากน้อยไปมาก
        Collections.sort(years);

        // ลบรายการเก่าออกจาก JComboBox ทั้งสองอัน
        yearComboBox1.removeAllItems();
        yearComboBox2.removeAllItems();

        // เพิ่มปีใหม่เข้าไปใน JComboBox ทั้งสองอัน
        for (String year : years) {
            yearComboBox1.addItem(year);
            yearComboBox2.addItem(year);
        }

        // กำหนดการแสดงผลให้กับ yearComboBox1
        yearComboBox1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(0, 10, 0, 0));
                if (index == -1 && value == null) {
                    setText("กรุณาเลือกปี");
                    setFont(new Font("Dialog", Font.BOLD, 15)); // เปลี่ยนฟอนต์เป็น Dialog
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });
        yearComboBox1.setSelectedIndex(-1); // ตั้งค่าเป็น "กรุณาเลือกปี"

        // กำหนดการแสดงผลให้กับ yearComboBox2
        yearComboBox2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(0, 10, 0, 0));
                if (index == -1 && value == null) {
                    setText("กรุณาเลือกปี");
                    setFont(new Font("Tahoma", Font.BOLD, 15)); // เปลี่ยนฟอนต์เป็น Dialog
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });
        yearComboBox2.setSelectedIndex(-1); // ตั้งค่าเป็น "กรุณาเลือกปี"
    }

    private void updateTaxData(JTextArea taxDataArea, String year) {
        StringBuilder dataBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("data.txt"), "UTF-8"))) {
            String line;
            boolean userFound = false;
            boolean yearFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals(username)) {
                    userFound = true; // พบชื่อผู้ใช้
                } else if (userFound) {
                    if (line.startsWith("ปี:") && line.contains(year)) {
                        yearFound = true;
                        String[] dataParts = line.split(",");

                        // กำหนดความกว้างสำหรับการจัดชิดขวา
                        int labelWidth = 35; // ความกว้างสำหรับชื่อรายการ
                        int valueWidth = 15; // ความกว้างสำหรับค่าตัวเลข

                        // รูปแบบ: ข้อความจัดชิดซ้ายและค่าจัดชิดขวา
                        String format = "%-" + labelWidth + "s%" + valueWidth + "s\n\n";

                        // เพิ่มข้อมูลที่จัดเรียงตามหลัก
                        dataBuilder.append(String.format(format, "\tเงินเดือนรายปี",
                                formatNumber(Integer.parseInt(getValue(dataParts[1], 0)))));
                        dataBuilder.append(String.format(format, "\tโบนัสเงินเดือน",
                                formatNumber(Integer.parseInt(getValue(dataParts[2], 0)))));
                        dataBuilder.append(String.format(format, "\tรายได้อื่นๆ",
                                formatNumber(Integer.parseInt(getValue(dataParts[3], 0)))));
                        dataBuilder.append(String.format(format, "\tภาษีที่ลดหย่อนรวม",
                                formatNumber(Integer.parseInt(getValue(dataParts[8], 0)))));
                    } else if (yearFound && line.trim().isEmpty()) {
                        break; // ออกจากลูปเมื่อพบบรรทัดว่างหลังจากข้อมูลปี
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to update tax information: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // กำหนดฟอนต์ Sarabun ที่รองรับภาษาไทย
        taxDataArea.setText(dataBuilder.toString());
        taxDataArea.setFont(new Font("TH Sarabun New", Font.BOLD, 21)); // ใช้ Sarabun หรือ Tahoma
                                                                        // ขึ้นอยู่กับฟอนต์ที่ระบบรองรับ
    }

    // ฟังก์ชันสำหรับจัดรูปแบบตัวเลข
    private String formatNumber(int number) {
        return String.format("%,d", number);
    }

    // ฟังก์ชันสำหรับรับค่า
    private String getValue(String data, int defaultValue) {
        String trimmedData = data.split(":").length > 1 ? data.split(":")[1].trim() : "";
        return trimmedData.isEmpty() ? String.valueOf(defaultValue) : String.valueOf(Integer.parseInt(trimmedData));
    }

    public void getUsername(String username) {
        this.username = username;
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