import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends JFrame {
    private MainApplication mainApp;
    private String username;
    private String year1;
    private String year2;
    private int chartWidth = 900;
    private int chartHeight = 500;
    private DefaultCategoryDataset dataset;

    public LineChart(MainApplication mainApp, String username, String year1, String year2) {
        super("กราฟแสดงการเปรียบเทียบภาษี");
        this.mainApp = mainApp;
        this.username = username;
        this.year1 = year1;
        this.year2 = year2;
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#D8F5FF"));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/project.jpg")));

        JPanel headerPanel = createHeaderPanel();
        JPanel chartPanel = createChartPanel();

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(chartPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setSize(1133, 744);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBackground(Color.decode("#D8F5FF"));
        headerPanel.setPreferredSize(new Dimension(1133, 100));

        // Create HOME button
        JButton homeButton = new JButton("HOME");
        homeButton.setBackground(Color.decode("#80C8CD"));
        homeButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBounds(20, 20, 120, 50);
        homeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame

                // Navigate back to the main application
                mainApp.showPage("Home");
                mainApp.setVisible(true);
            }
        });

        setVisible(true);

        // Create title label
        JLabel titleLabel = new JLabel("กราฟแสดงการเปรียบเทียบภาษี", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));
        titleLabel.setBounds(300, 1, 600, 100);

        // Add components to header panel
        headerPanel.add(homeButton);
        headerPanel.add(titleLabel);

        // Create a panel for the blue line
        JPanel linePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.decode("#6390BA"));
                g2d.setStroke(new BasicStroke(7));
                g.drawLine(0, 90, 1133, 90);
            }
        };
        linePanel.setBounds(0, 0, 1133, 744);
        headerPanel.add(linePanel);

        return headerPanel;
    }

    public void showChart() {
        if (dataset == null || dataset.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "ไม่พบข้อมูลสำหรับการสร้างกราฟ", "ข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        setVisible(true);
    }

    private JPanel createChartPanel() {
        dataset = createDatasetFromFile("data.txt");

        if (dataset.getRowCount() == 0) {
            JPanel errorPanel = new JPanel();
            errorPanel.add(new JLabel("ไม่พบข้อมูลสำหรับการสร้างกราฟ"));
            return errorPanel;
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                null,
                "ประเภท",
                "จำนวน (บาท)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.decode("#FFFFFF"));
        plot.setRangeGridlinePaint(Color.WHITE);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.decode("#A6C8E6")); // เปลี่ยนเป็นสี #A6C8E6 สำหรับเส้นแรก
        renderer.setSeriesPaint(1, Color.decode("#F5A8A7")); // เปลี่ยนเป็นสี #F5A8A7 สำหรับเส้นที่สอง
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));

        ChartPanel panel = new ChartPanel(lineChart);
        panel.setPreferredSize(new Dimension(chartWidth, chartHeight));
        panel.setMinimumSize(new Dimension(chartWidth, chartHeight));
        panel.setMaximumSize(new Dimension(chartWidth, chartHeight));
        panel.setBackground(Color.decode("#D8F5FF"));

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(Color.decode("#D8F5FF"));
        wrapperPanel.add(panel);

        return wrapperPanel;
    }

    private DefaultCategoryDataset createDatasetFromFile(String filePath) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        StringBuilder dataLog = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dataLog.append(line).append("\n");
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String year = parts[0].split(":")[1].trim();
                    if (year.equals(year1) || year.equals(year2)) {
                        double salary = 0;
                        double bonus = 0;
                        double otherIncome = 0;
                        double deductions = 0;
                        double tax = 0;

                        for (int i = 1; i < parts.length; i++) {
                            String[] valueParts = parts[i].split(":");
                            if (valueParts.length == 2) {
                                String category = valueParts[0].trim();
                                try {
                                    double value = Double.parseDouble(valueParts[1].trim());
                                    switch (category) {
                                        case "เงินเดือนรายปี":
                                            salary = value;
                                            break;
                                        case "โบนัส":
                                            bonus = value;
                                            break;
                                        case "รายได้อื่นๆ":
                                            otherIncome = value;
                                            break;
                                        case "ภาษีที่ลดหย่อน":
                                            deductions = value;
                                            break;
                                        case "ภาษีที่ต้องจ่าย":
                                            tax = value;
                                            break;
                                    }
                                } catch (NumberFormatException e) {
                                    dataLog.append("Error parsing value: ").append(valueParts[1].trim()).append("\n");
                                }
                            }
                        }

                        dataset.addValue(salary, year, "เงินเดือนรายปี");
                        dataset.addValue(bonus, year, "โบนัส");
                        dataset.addValue(otherIncome, year, "รายได้อื่นๆ");
                        dataset.addValue(deductions, year, "ภาษีที่ลดหย่อน");
                        dataset.addValue(tax, year, "ภาษีที่ต้องจ่าย");

                        dataLog.append("Added: ").append(year).append(", เงินเดือนรายปี: ").append(salary).append("\n");
                        dataLog.append("Added: ").append(year).append(", โบนัส: ").append(bonus).append("\n");
                        dataLog.append("Added: ").append(year).append(", รายได้อื่นๆ: ").append(otherIncome)
                                .append("\n");
                        dataLog.append("Added: ").append(year).append(", ภาษีที่ลดหย่อน: ").append(deductions)
                                .append("\n");
                        dataLog.append("Added: ").append(year).append(", ภาษีที่ต้องจ่าย: ").append(tax).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "ไม่สามารถอ่านไฟล์ข้อมูลได้: " + e.getMessage(), "ข้อผิดพลาด",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (dataset.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "ไม่พบข้อมูลที่ตรงกับปีที่ระบุ (" + year1 + ", " + year2
                    + ")\n\nข้อมูลที่อ่านได้:\n" + dataLog.toString(), "ข้อมูลว่างเปล่า", JOptionPane.WARNING_MESSAGE);
        }

        return dataset;
    }

}