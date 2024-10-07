import java.awt.*;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Collections;

public class historyTax extends JPanel {
    private MainApplication mainApp;
    private String username;
    private JComboBox<String> yearComboBox; // JComboBox สำหรับปี
    private JTextArea taxDataArea; // JTextArea สำหรับแสดงข้อมูล

    public historyTax(MainApplication mainApp) {
        this.mainApp = mainApp;
        setOpaque(true); // ทำให้แน่ใจว่าพาเนลหลักทึบแสง
        setBackground(Color.decode("#D8F5FF")); // ตั้งค่าสีพื้นหลัง
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);
        setSize(1133, 744);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);
        layeredPane.setOpaque(false);
        add(layeredPane);

        JLabel label = new JLabel("การเปรียบเทียบภาษี");
        label.setFont(new Font("Tahoma", Font.BOLD, 32));
        label.setBounds(160, 15, 1000, 50);
        label.setOpaque(false);
        layeredPane.add(label, JLayeredPane.PALETTE_LAYER);

        int frameWidth = 700;
        int frameHeight = 400;
        int x = (1133 - frameWidth) / 2;
        int y = 200;

        // taxDataArea1 พื้นหลัง
        JPanel taxDataAreaBackground = new JPanel();
        taxDataAreaBackground.setBounds(x, y, frameWidth, frameHeight);
        taxDataAreaBackground
                .setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "ข้อมูลภาษี",
                        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
                        new Font("Tahoma", Font.BOLD, 18), Color.BLACK));
        taxDataAreaBackground.setLayout(null);
        taxDataAreaBackground.setBackground(Color.WHITE);
        layeredPane.add(taxDataAreaBackground, JLayeredPane.DEFAULT_LAYER);

        // Update taxDataArea1
        taxDataArea = new JTextArea();
        taxDataArea.setFont(new Font("Tahoma", Font.PLAIN, 19));
        taxDataArea.setEditable(false);
        taxDataArea.setLineWrap(true);
        taxDataArea.setWrapStyleWord(true);
        taxDataArea.setOpaque(false);
        taxDataArea.setBounds(50, 25, frameWidth - 70, frameHeight - 35);

        // Center align text
        taxDataArea.setMargin(new Insets(10, 10, 10, 10));
        taxDataAreaBackground.add(taxDataArea);

        // Update yearComboBox1
        yearComboBox = new JComboBox<>();
        yearComboBox.setBounds((1133 - 400) / 2, 130, 400, 45);
        yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        yearComboBox.setBackground(Color.WHITE);
        yearComboBox.setOpaque(true);
        yearComboBox.addActionListener(e -> {
            if (yearComboBox.getSelectedItem() != null) {
                String selectedYear = yearComboBox.getSelectedItem().toString();
                updateTaxData(taxDataArea, selectedYear);
            } else {
                taxDataArea.setText(" ");
            }
        });
        layeredPane.add(yearComboBox, JLayeredPane.PALETTE_LAYER);

        // Home button
        JButton homeButton = new JButton("HOME");
        homeButton.setBackground(Color.decode("#80C8CD"));
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBounds(20, 20, 120, 50);
        homeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        homeButton.addActionListener(e -> mainApp.showPage("Home"));
        layeredPane.add(homeButton, JLayeredPane.PALETTE_LAYER);

        DisplayGraphics graphicsPanel = new DisplayGraphics();
        graphicsPanel.setBounds(0, 0, 1133, 744);
        graphicsPanel.setOpaque(false);
        layeredPane.add(graphicsPanel, JLayeredPane.DEFAULT_LAYER);
    }

    public void setYears(List<String> years) {
        // เรียงลำดับปีจากน้อยไปมาก
        Collections.sort(years);
        yearComboBox.removeAllItems(); // ลบรายการเก่าออกก่อน
        for (String year : years) {
            yearComboBox.addItem(year); // เพิ่มปีใหม่
        } // ตั้งค่าเป็น 'กรุณาเลือกปี'

        yearComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(new EmptyBorder(0, 10, 0, 0));
                if (index == -1 && value == null) {
                    setText("กรุณาเลือกปี");
                    setFont(new Font("Dialog", Font.BOLD, 15));
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
                return this;
            }
        });
        yearComboBox.setSelectedIndex(-1);
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
                        dataBuilder.append("\n");
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
        taxDataArea.setFont(new Font("TH Sarabun New", Font.BOLD, 30)); // ใช้ Sarabun หรือ Tahoma
                                                                        // ขึ้นอยู่กับฟอนต์ที่ระบบรองรับ
    }

    // ฟังก์ชันสำหรับรับค่า
    private String getValue(String data, int defaultValue) {
        String trimmedData = data.split(":").length > 1 ? data.split(":")[1].trim() : "";
        return trimmedData.isEmpty() ? String.valueOf(defaultValue) : String.valueOf(Integer.parseInt(trimmedData));
    }

    // ฟังก์ชันสำหรับจัดรูปแบบตัวเลข
    private String formatNumber(int number) {
        return String.format("%,d", number);
    }

    public void getUsernameToHistory(String username) {
        this.username = username;
        System.out.println("Username for historyTax: " + username);
    }

    class DisplayGraphics extends JPanel {
        public DisplayGraphics() {
            setOpaque(false); // ทำให้ DisplayGraphics panel โปร่งใส
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.decode("#6390BA"));
            g2d.setStroke(new BasicStroke(7));
            g2d.drawLine(0, 90, 1133, 90);
        }
    }
}