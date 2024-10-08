import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private StringBuilder currentInput;
    private double memory;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 40));
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4)); // Add another row for memory buttons

        String[] buttons = {
            "C", "CE", "/", "*",
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "+/-",
            "reset", "0", ".", "=",
        };

        currentInput = new StringBuilder();
        memory = 0;  // Initialize memory to zero

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setPreferredSize(new Dimension(80, 80));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                currentInput.setLength(0); // Clear all input
                displayField.setText("");
                break;
            case "CE":
                // Clear the last entry (last character)
                if (currentInput.length() > 0) {
                    currentInput.deleteCharAt(currentInput.length() - 1);
                    displayField.setText(currentInput.toString());
                }
                break;
            case "reset":
                currentInput.setLength(0);
                displayField.setText("");
                memory = 0;
                break;
            case "+/-":
                if (currentInput.length() > 0) {
                    if (currentInput.charAt(0) == '-') {
                        currentInput.deleteCharAt(0); // Remove the negative sign
                    } else {
                        currentInput.insert(0, '-'); // Add the negative sign
                    }
                    displayField.setText(currentInput.toString());
                }
                break;
            case "=":
                try {
                    String result = evaluateExpression(currentInput.toString());
                    displayField.setText(result);
                    currentInput.setLength(0);
                    currentInput.append(result);
                } catch (Exception ex) {
                    displayField.setText("Error");
                }
                break;
            default:
                if (command.equals(".") && currentInput.toString().contains(".")) {
                    // Prevent multiple decimals
                    break;
                }
                currentInput.append(command);
                displayField.setText(currentInput.toString());
                break;
        }
    }

    private String evaluateExpression(String expression) {
        double result = 0;
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");
        double currentNumber = 0;
        String operation = "+";

        for (String token : tokens) {
            if (token.matches("[0-9.]+")) {
                currentNumber = Double.parseDouble(token);
                switch (operation) {
                    case "+":
                        result += currentNumber;
                        break;
                    case "-":
                        result -= currentNumber;
                        break;
                    case "*":
                        result *= currentNumber;
                        break;
                    case "/":
                        if (currentNumber != 0) {
                            result /= currentNumber;
                        } else {
                            throw new ArithmeticException("Division by zero");
                        }
                        break;
                }
            } else {
                operation = token;
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
