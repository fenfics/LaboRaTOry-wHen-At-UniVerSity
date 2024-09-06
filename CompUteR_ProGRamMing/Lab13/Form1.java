import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class Form1 extends JFrame implements ActionListener, KeyListener {

    JTextArea t;
    JButton b1;
    JButton b2;
    Stack<String> st;
    Stack<String> sd;

    public Form1() {
        sd = new Stack<>();
        st = new Stack<>();
        
        st.push(" "); // Initialize with an empty string

        Container cp = this.getContentPane();
        cp.setLayout(null);

        t = new JTextArea();
        b1 = new JButton("UNDO");
        b2 = new JButton("REDO");
        t.setBounds(10, 10, 160, 80);
        b1.setBounds(10, 110, 70, 40); 
        b2.setBounds(100, 110, 70, 40);

        cp.add(t);
        cp.add(b1);
        cp.add(b2);

        t.addKeyListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(200, 200);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) { // Undo
            if (!st.isEmpty() && !st.peek().isEmpty()) {
                sd.push(t.getText());//เพิ่มข้อความที่พิมเข้าในอีกสแต็ก
                t.setText(st.pop());
            }
        }

        if (e.getSource() == b2) { // Redo
            if (!sd.isEmpty()) {
                st.push(t.getText());
                t.setText(sd.pop());
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        // Push current text to stack only on certain keys
        if (e.getKeyChar() == ' ' || e.getKeyChar() == '\n') {
                st.push(t.getText());
                sd.clear(); // Clear redo stack on new change
            }
        }

    public void keyReleased(KeyEvent e) {
       
    }
}