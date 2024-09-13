import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculator extends JFrame implements ActionListener{
    JButton[] buttons;
    String[] cal = { "C", "%", "/", "*", "7", "8", "9", " x ", "4", "5", "6", " - ", "1", "2", "3", " + ", "+/-", "0",
            ".", "=" };
    JPanel buttonPanel;
    JPanel numPanel;
    JTextField displayTextField;
    String input = "";

    public Calculator() {
        super("Calculator");

        getContentPane().add(setBottonPanel());
        getContentPane().add(setNumPanel(), BorderLayout.NORTH);
        setSize(350, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    private JPanel setBottonPanel() { // ปุ่มเลข
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(5, 4, 6, 5));
            buttons = new JButton[25];
            for (int i = 0; i < cal.length; i++) {
                buttons[i] = new JButton(cal[i]);
                buttonPanel.add(buttons[i]);
                buttons[i].addActionListener(this);
                buttons[i].setFont(new Font("Bold", Font.PLAIN, 20));
            }
        }
        return buttonPanel;
    }

    private JPanel setNumPanel() { // ปุ่มรับค่า
        if (numPanel == null) {
            numPanel = new JPanel();
            displayTextField = new JTextField("0");
            displayTextField.setHorizontalAlignment(JTextField.RIGHT);
            displayTextField.setFont(new Font("Bold", Font.PLAIN, 35));
            numPanel.setLayout(new BorderLayout());
            numPanel.add(displayTextField);
        }
        return numPanel;
    }


        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            displayTextField.setText(displayTextField.getText() + button.getText());

       
    }
}

// import javax.swing.*;
// import java.awt.event.*;
// import java.util.Stack;
// import java.awt.*;

// public class Calculator extends JFrame implements ActionListener {
//     JButton[] buttons;
//     String[] cal = { "C", "%", "/", "*", "7", "8", "9", "-", "4", "5", "6", "+", "1", "2", "3", "=", "+/-", "0", ".", "(" };
//     JPanel buttonPanel;
//     JPanel numPanel;
//     JTextField displayTextField;
//     String input = "";
//     double result = 0;
//     String lastOperation = "";

//     public Calculator() {
//         super("Calculator");

//         getContentPane().add(setBottonPanel());
//         getContentPane().add(setNumPanel(), BorderLayout.NORTH);
//         setSize(350, 400);
//         setVisible(true);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
//     }

//     public static void main(String[] args) {
//         new Calculator();
//     }

//     private JPanel setBottonPanel() {
//         if (buttonPanel == null) {
//             buttonPanel = new JPanel();
//             buttonPanel.setLayout(new GridLayout(5, 4, 6, 5));
//             buttons = new JButton[20];
//             for (int i = 0; i < cal.length; i++) {
//                 buttons[i] = new JButton(cal[i]);
//                 buttonPanel.add(buttons[i]);
//                 buttons[i].addActionListener(this);
//                 buttons[i].setFont(new Font("Bold", Font.PLAIN, 20));
//             }
//         }
//         return buttonPanel;
//     }

//     private JPanel setNumPanel() {
//         if (numPanel == null) {
//             numPanel = new JPanel();
//             displayTextField = new JTextField();
//             displayTextField.setHorizontalAlignment(JTextField.RIGHT);
//             displayTextField.setFont(new Font("Bold", Font.PLAIN, 35));
//             numPanel.setLayout(new BorderLayout());
//             numPanel.add(displayTextField);
//         }
//         return numPanel;
//     }

//     public void actionPerformed(ActionEvent e) {
//         String command = e.getActionCommand();

//         if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
//             if (!input.equals(""))
//                 input = input + command;
//             else
//                 input = command;
//             displayTextField.setText(input);
//         } else if (command.charAt(0) == 'C') {
//             input = "";
//             result = 0;
//             lastOperation = "";
//             displayTextField.setText("");
//         } else if (command.charAt(0) == '=') {
//             if (!input.equals("")) {
//                 double temp = Double.parseDouble(input);
//                 switch (lastOperation) {
//                     case "+":
//                         result += temp;
//                         break;
//                     case "-":
//                         result -= temp;
//                         break;
//                     case "*":
//                         result *= temp;
//                         break;
//                     case "/":
//                         if (temp != 0)
//                             result /= temp;
//                         else
//                             displayTextField.setText("Error");
//                         break;
//                     case "%":
//                         result %= temp;
//                         break;
//                     default:
//                         result = temp;
//                 }
//             }
//             input = "";
//             displayTextField.setText(String.valueOf(result));
//         } else {
//             if (!input.equals("")) {
//                 double temp = Double.parseDouble(input);
//                 switch (lastOperation) {
//                     case "+":
//                         result += temp;
//                         break;
//                     case "-":
//                         result -= temp;
//                         break;
//                     case "*":
//                         result *= temp;
//                         break;
//                     case "/":
//                         if (temp != 0)
//                             result /= temp;
//                         else
//                             displayTextField.setText("Error");
//                         break;
//                     case "%":
//                         result %= temp;
//                         break;
//                     default:
//                         result = temp;
//                 }
//             }
//             lastOperation = command;
//             input = "";
//         }
//     }
// }
