import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import java.awt.*;

public class Calculator extends JFrame implements ActionListener {
    Stack<Integer> s1 = new Stack<>();
    JButton[] buttons;
    String[] cal = { "C", "%", "/", "*", "7", "8", "9", " x ", "4", "5", "6", " - ", "1", "2", "3", " + ", "+/-", "0",
            ".", "=" };
    JPanel buttonPanel;
    JPanel numPanel;
    JTextField displayTextField;

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
        s1.push(Integer.parseInt(button.getText())); // Convert button text to integer and push to stack
        displayTextField.setText(displayTextField.getText() + button.getText());
    
        if (button.getText().equals("+")) {
            if (s1.size() >= 2) {
                int num2 = s1.pop();
                int num1 = s1.pop();
                int sum = num1 + num2;
                s1.push(sum); // Push result back to stack
                displayTextField.setText(String.valueOf(sum)); // Display the result
            }
        }
    }

}
// /*import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.text.DecimalFormat;
// import java.util.EmptyStackException;
// import java.util.Stack;
// import java.lang.Math;

// public class ScientificCalculator {
//     private JFrame frame;
//     private JTextField display;
//     private String input = "";
//     private Stack<Double> memory = new Stack<>();

//     public ScientificCalculator() {
//         frame = new JFrame("Scientific Calculator");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setSize(400, 400);
//         frame.setLayout(new BorderLayout());

//         display = new JTextField();
//         display.setFont(new Font("Arial", Font.PLAIN, 20));
//         display.setEditable(false);
//         frame.add(display, BorderLayout.NORTH);

//         JPanel buttonPanel = new JPanel();
//         buttonPanel.setLayout(new GridLayout(6, 5));

//         String[] buttons = {
//                 "7", "8", "9", "/", "sqrt",
//                 "4", "5", "6", "*", "x^2",
//                 "1", "2", "3", "-", "x^y",
//                 "0", ".", "+/-", "+", "=",
//                 "sin", "cos", "tan", "log", "ln"
//         };

//         for (String button : buttons) {
//             JButton btn = new JButton(button);
//             btn.setFont(new Font("Arial", Font.PLAIN, 18));
//             btn.addActionListener(new ButtonClickListener());
//             buttonPanel.add(btn);
//         }

//         frame.add(buttonPanel, BorderLayout.CENTER);
//         frame.setVisible(true);
//     }

//     private class ButtonClickListener implements ActionListener {
//         public void actionPerformed(ActionEvent e) {
//             String command = e.getActionCommand();

//             if ("0123456789.".contains(command)) {
//                 input += command;
//             } else if ("+-*/".contains(command)) {
//                 input += " " + command + " ";
//             } else if ("sqrt x^2 x^y sin cos tan log ln".contains(command)) {
//                 input = command + "(" + input + ")";
//             } else if ("=".equals(command)) {
//                 try {
//                     input = evaluateExpression(input);
//                 } catch (ArithmeticException ex) {
//                     input = "Error";
//                 }
//             } else if ("+/-".equals(command)) {
//                 input = negateInput(input);
//             }

//             display.setText(input);
//         }

//         private String evaluateExpression(String expression) {
//             String result = "";
//             try {
//                 String[] parts = expression.split(" ");
//                 Stack<String> operators = new Stack<>();
//                 Stack<Double> values = new Stack<>();

//                 for (String part : parts) {
//                     if ("+-*/sqrtx^2x^ysincostanlogln".contains(part)) {
//                         operators.push(part);
//                     } else {
//                         values.push(Double.parseDouble(part));
//                     }

//                     while (!operators.isEmpty() && values.size() >= 2) {
//                         String operator = operators.pop();
//                         double b = values.pop();
//                         double a = values.pop();
//                         double res = calculate(a, b, operator);
//                         values.push(res);
//                     }
//                 }

//                 DecimalFormat df = new DecimalFormat("#.##########");
//                 result = df.format(values.pop());
//             } catch (NumberFormatException | EmptyStackException e) {
//                 result = "Error";
//             }
//             return result;
//         }

//         private double calculate(double a, double b, String operator) {
//             switch (operator) {
//                 case "+":
//                     return a + b;
//                 case "-":
//                     return a - b;
//                 case "*":
//                     return a * b;
//                 case "/":
//                     if (b == 0) throw new ArithmeticException("Division by zero");
//                     return a / b;
//                 case "sqrt":
//                     if (a < 0) throw new ArithmeticException("Square root of negative number");
//                     return Math.sqrt(a);
//                 case "x^2":
//                     return a * a;
//                 case "x^y":
//                     return Math.pow(a, b);
//                 case "sin":
//                     return Math.sin(Math.toRadians(a));
//                 case "cos":
//                     return Math.cos(Math.toRadians(a));
//                 case "tan":
//                     return Math.tan(Math.toRadians(a));
//                 case "log":
//                     if (a <= 0 || b <= 0 || a == 1) throw new ArithmeticException("Invalid logarithm");
//                     return Math.log(b) / Math.log(a);
//                 case "ln":
//                     if (a <= 0) throw new ArithmeticException("Invalid natural logarithm");
//                     return Math.log(a);
//                 default:
//                     throw new IllegalArgumentException("Invalid operator: " + operator);
//             }
//         }

//         private String negateInput(String input) {
//             if (input.isEmpty()) return input;

//             String[] parts = input.split(" ");
//             int lastIndex = parts.length - 1;
//             String lastPart = parts[lastIndex];

//             if (!lastPart.isEmpty() && Character.isDigit(lastPart.charAt(0))) {
//                 if (lastPart.charAt(0) == '-') {
//                     parts[lastIndex] = lastPart.substring(1);
//                 } else {
//                     parts[lastIndex] = "-" + lastPart;
//                 }
//                 return String.join(" ", parts);
//             } else {
//                 return input;
//             }
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(ScientificCalculator::new);
//     }
// }
