import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.util.Collections;
import java.util.List;

public class PieChart extends JPanel {
    private MainApplication mainApp;
    private JLayeredPane layeredPane;
    private JComboBox<String> yearComboBox;
    private DefaultPieDataset dataset;
    private ChartPanel chartPanel; 
    private String username;

    public PieChart(MainApplication mainApp, String username) {
        this.mainApp = mainApp;
        this.username = username;
        setBackground(Color.decode("#D8F5FF"));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1133, 744));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1133, 744));
        add(layeredPane, BorderLayout.CENTER);

        // Title
        JLabel titleLabel = new JLabel("กราฟแสดงการลดหย่อน");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
        titleLabel.setBounds(390, 1, 500, 100);
        layeredPane.add(titleLabel, JLayeredPane.DEFAULT_LAYER);

        // Year ComboBox
        yearComboBox = new JComboBox<>();
        yearComboBox.setBounds(450, 130, 250, 35);
        yearComboBox.addItem("กรุณาเลือกปี");
        yearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        yearComboBox.addActionListener(e -> {
            if (yearComboBox.getSelectedItem() != null) {
                String selectedYear = yearComboBox.getSelectedItem().toString();
                updateDataset(selectedYear);
            } else {
                // ลบกราฟออกหรือลบข้อมูลใน dataset
                if (chartPanel != null) {
                    layeredPane.remove(chartPanel);
                    chartPanel = null; // รีเซ็ต chartPanel
                    layeredPane.revalidate(); // รีเฟรชเลเยอร์
                    layeredPane.repaint(); // วาดใหม่
                }
                dataset.clear(); // เคลียร์ข้อมูลใน dataset
                System.out.println("Chart removed, no year selected."); // Debug
            }
        });

        layeredPane.add(yearComboBox, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(yearComboBox, JLayeredPane.PALETTE_LAYER);

        // Dataset and Chart
        dataset = new DefaultPieDataset();
        createChart(); // สร้างกราฟเริ่มต้น
        chartPanel.setBounds(200, 200, 700, 400);
        layeredPane.add(chartPanel, JLayeredPane.PALETTE_LAYER);

        // Back Button
        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.decode("#A5CBB0"));
        backButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        backButton.setBounds(500, 622, 120, 50);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> mainApp.showPage("Home"));
        layeredPane.add(backButton, JLayeredPane.PALETTE_LAYER);

        // Add blue line
        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);
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

    public void setUsername(String username) {
        this.username = username;
        System.out.println("Username for piechart: " + username);
    }

    private void addToDataset(String key, String part) {
    String[] valuePart = part.split(":");
    if (valuePart.length == 2) {
        try {
            double value = Double.parseDouble(valuePart[1].trim().replace(",", "")); // เพิ่ม trim() เพื่อลบช่องว่างที่อาจมีหลังเครื่องหมาย :
            if (value > 0) {
                dataset.setValue(key, value);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + valuePart[1]);
        }
    } else {
        System.out.println("Value part is not in expected format: " + part);
    }
}

    private void updateDataset(String year) {
    dataset.clear();
    System.out.println("Updating dataset for year: " + year);
    if (year != null && !year.equals("กรุณาเลือกปี")) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt"), "UTF-8"))) {
            String line;
            boolean foundUsername = false;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                System.out.println("Reading line: " + line);
                if (line.equals(username)) {
                    foundUsername = true;
                    System.out.println("Found username: " + username);
                } else if (foundUsername && line.startsWith("ปี: " + year)) {
                    System.out.println("Found year data: " + line);
                    String[] parts = line.split(",");
                    for (String part : parts) {
                        part = part.trim();
                        System.out.println("Processing part: " + part);
                        if (part.startsWith("ลดหย่อนครอบครัว :")) {
                            addToDataset("ลดหย่อนครอบครัว", part);
                        } else if (part.startsWith("ลดหย่อนกองทุน :")) {
                            addToDataset("ลดหย่อนกองทุน", part);
                        } else if (part.startsWith("ลดหย่อนประกัน :")) {
                            addToDataset("ลดหย่อนประกัน", part);
                        } else if (part.startsWith("ลดหย่อนอื่นๆ :")) {
                            addToDataset("ลดหย่อนอื่นๆ", part);
                        }
                    }
                    break;
                } else if (foundUsername && line.isEmpty()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    System.out.println("Final dataset: " + dataset);
    createChart();
}

    private void createChart() {
        System.out.println("Creating chart with dataset: " + dataset);
        JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} บาท ({2})"));
        plot.setBackgroundPaint(new Color(230, 230, 250));

        // เพิ่มสีให้กับแต่ละส่วนของพาย
        plot.setSectionPaint("ลดหย่อนครอบครัว", new Color(0xA6C8E6));
        plot.setSectionPaint("ลดหย่อนกองทุน", new Color(0xC0ECCD) );
        plot.setSectionPaint("ลดหย่อนประกัน", new Color(0xF9F0C1));
        plot.setSectionPaint("ลดหย่อนอื่นๆ", new Color(0xF4CDA6));

        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(200, 200, 700, 400);
        layeredPane.add(chartPanel, JLayeredPane.PALETTE_LAYER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
}

