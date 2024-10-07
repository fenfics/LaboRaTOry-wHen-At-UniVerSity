import java.awt.*;
import javax.swing.*;
import java.util.List;

public class MainApplication extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Page15 page15;
    private Page12 page12;
    private Page13 page13;
    private Page14 page14;
    private Page16 page16;
    private Page17 page17;
    private Home h1;
    private Salary s1;
    private Family f1;
    private int totalContribution;
    private Login log;
    private Welcome welcome;
    private PIN pin;
    private Forgetpass forgetpass;
    private PieChart pie;
    private historyTax history;
    private compareTax compare;
    private String username; 
    private int  f4Value;

    public MainApplication() {
        initializeFrame();
        initializeComponents();
        addComponentsToCardPanel();
        setContentPane(cardPanel);
        setBackground(Color.decode("#D8F5FF"));
        showPage("Welcome");
    }

    private void initializeFrame() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/project.jpg")));
        setSize(1133, 744);
        setTitle("โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ");
        setBackground(Color.decode("#D8F5FF"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        welcome = new Welcome(this);
        log = new Login(this);
        pin = new PIN(this);
        forgetpass = new Forgetpass(this);
        h1 = new Home(this);
        history = new historyTax(this);
        compare = new compareTax(this);
        pie = new PieChart(this, "");
        s1 = new Salary(this);
        f1 = new Family(this);
        page12 = new Page12(this);
        page13 = new Page13(this);
        page14 = new Page14(this);
        page15 = new Page15(this);
        page16 = new Page16(this);
        page17 = new Page17(this);
        
    }

    private void addComponentsToCardPanel() {
        cardPanel.add(welcome, "Welcome");
        cardPanel.add(log, "Login");
        cardPanel.add(pin, "PIN");
        cardPanel.add(forgetpass, "Forgetpass");
        cardPanel.add(h1, "Home");
        cardPanel.add(s1, "Salary");
        cardPanel.add(f1, "Family");
        cardPanel.add(page12, "page12");
        cardPanel.add(page13, "page13");
        cardPanel.add(page14, "page14");
        cardPanel.add(page15, "page15");
        cardPanel.add(page16, "page16");
        cardPanel.add(page17, "page17");
        cardPanel.add(history, "historyTax");
        cardPanel.add(compare, "compareTax");
        cardPanel.add(pie, "PieChart");
      
    }

    public JPanel getPage(String pageName) {
        for (Component comp : cardPanel.getComponents()) {
            if (comp.getName() != null && comp.getName().equals(pageName)) {
                return (JPanel) comp;
            }
        }
        return null;
    }

    public void showPage(String pageName) {
        cardLayout.show(cardPanel, pageName);
        JPanel targetPage = getPage(pageName);
        if (targetPage != null) {
            targetPage.setVisible(true);
        }
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println("Username set to: " + username); // Debugging line
    }

    public String getUsername() {
        if (username == null || username.trim().isEmpty()) {
            System.err.println("Username is not set. Ensure username is properly set before calling this method.");
            return "";
        }
        return username;
    }

    public void onCompareButtonClick(String year1, String year2) {
        // Ensure the username is set before calling sendYearsToLineChart
        sendYearsToLineChart(year1, year2);
    }

    // Example of how you might set the username after login
    public void onLoginSuccess(String username) {
        setUsername(username); // Set the username after login
    }

    
    public void sendYearsToLineChart(String year1, String year2) {
        String currentUsername = getUsername();
        if (!currentUsername.isEmpty()) { // ตรวจสอบว่ามีการตั้งชื่อผู้ใช้
            LineChart lineChart = new LineChart(this, currentUsername, year1, year2);
            cardPanel.add(lineChart, "LineChart");
            showPage("LineChart");
        } else {
            JOptionPane.showMessageDialog(this, "Please log in before viewing the comparison chart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showPage(String pageName, int value) {
        totalContribution = value;
        if (pageName.equals("page14")) {
            page14.setPreviousPageValue(value);
        } else if (pageName.equals("page15")) {
            page15.updateMinilabel1(String.valueOf(totalContribution));
        }
        showPage(pageName);
    }

    public void showCompareTaxPage(String username) {
        this.username = username; // กำหนดค่าชื่อผู้ใช้ใน MainApplication
        compare.getUsername(username); // เรียกใช้เมธอด getUsername ใน compareTax
        // แสดงหน้าเปรียบเทียบภาษี
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "CompareTax");
    }

    public void userLoggedIn(String username) {
        this.username = username; // กำหนดชื่อผู้ใช้ใน MainApplication
        System.out.println("User logged in: " + username);
        showCompareTaxPage(username); // เรียกใช้ showCompareTaxPage พร้อมกับส่ง username
    }

    public void showPage15WithUpdatedTotal(int valueFromPage12) {
        page15.setMinilabel1Value(String.valueOf(valueFromPage12));
    }

    public void updateTotalContribution(int value) {
        this.totalContribution = value;
    }

    public int getTotalContribution() {
        return totalContribution;
    }

    public void sendSalaryToFamily(int salaryAfterExpenses) {
        f1.receiveSalary(salaryAfterExpenses);
        showPage("Family");
    }

    public void sendValuesToPage12(int salaryAfterExpenses, int totalDeduction) {
        try {
            page12.receiveData(salaryAfterExpenses, totalDeduction);
            showPage("page12");
        } catch (Exception e) {
            System.err.println("Error sending values to Page12: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendValuesToPage13(int salaryAfterExpenses, int totalDeduction, int totalValue) {
        try {
            page13.receiveData(salaryAfterExpenses, totalDeduction, totalValue);
            showPage("page13");
        } catch (Exception e) {
            System.err.println("Error sending values to Page13: " + e.getMessage());
            e.printStackTrace();
        }
    }

       public void sendValuesToPage14(int salaryAfterExpenses, int totalDeduction, int totalValue, int getTotalValue) {
            try {
                page14.receiveData(salaryAfterExpenses, totalDeduction, totalValue, getTotalValue);
                showPage("page14");
            } catch (Exception e) {
                System.err.println("Error sending values to Page14: " + e.getMessage());
                e.printStackTrace();
            }
        }   

    public void sendValuesToPage15(int salaryAfterExpenses, int totalDeduction, int totalValue, int getTotalValue, int ToTalValue3, int netIncome) {
        try {
            page15.receiveData(salaryAfterExpenses, totalDeduction, totalValue, getTotalValue, ToTalValue3, netIncome);
            showPage("page15");
        } catch (Exception e) {
            System.err.println("Error sending values to Page15: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void sendNetIncomeCalculated(int netIncome) {
        page16.receiveData(netIncome);
    }

    public void sendSalaryYearTopage12(int Salaryyear) {
        page12.get(Salaryyear);
    }

    public void sendSalaryYearToPage14(int Salaryyear2) {
        page14.get(Salaryyear2);
    }

    public void sendNetIncomeCalculatedPage17(int netIncome) {
        page17.get(netIncome);
    }

    public void sendTaxTopage16(int Tax) {
        page16.getTax(Tax);
    }

    public void sendTaxtoPage17(int Tax) {
        page17.getTax(Tax);
    }

    public void endSalaryToFamily(int salaryAfterExpenses) {
        System.out.println("Processing salary for family: " + salaryAfterExpenses);
    }
    public void sendSalaryYeartoPage16(int salaryYear1){
        page16.getSalary(salaryYear1);
    }
    public void sendUsernameToCompare(String username){
        compare.getUsername(username);
    }

    public void sendUsernameToHistory(String username){
        history.getUsernameToHistory(username);
    }

    public void sendSalaryTopage13(int salaryYearPage13){
        page13.getSalary(salaryYearPage13);
    }

    public void sendUsernameToPie(String username) {
    pie = new PieChart(this, username);
    cardPanel.add(pie, "PieChart");
    }
    public void sendUsernameToHome(String username){
        h1.setUsername(username);
    }
    public void setYearsForHistoryTax(List<String> years) {
        history.setYears(years);  // ส่งข้อมูลปีไปยัง historyTax
    }
    public void setYearsForCompareTax(List<String> years) {
        compare.setYears(years);  // ส่งข้อมูลปีไปยัง comparetax
    }
       public void sendUsernametoPage15(String username){
        page15.getUsername(username);
    }
    public void sendDataTopage15(String year,int salaryyear,String bonus,String anothersalary){
        page15.getData(year,salaryyear,bonus,anothersalary);
    }
    public void setYearsForPieChart(List<String> years) {
        pie.setYears(years);  // ส่งข้อมูลปีไปยัง pieChart
    }
   
    public void sendF4ToPage14(int valueF4) {
            page14.setF4Value(valueF4); // ส่งค่าจาก f4 page13 ไปยัง page14
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true);
        });
    }
    

   
}
