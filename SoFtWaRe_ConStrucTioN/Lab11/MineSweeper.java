import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MineSweeper extends JFrame {
    private int row;
    private int col;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] flags;
    private int flagCount;

    private void init(int i) {
        mines = new boolean[row][col];
        flags = new boolean[row][col];
        flagCount = 0;
        Random r = new Random();
        while (i > 0) {
            int ranX = r.nextInt(row);
            int ranY = r.nextInt(col);
            if (!mines[ranX][ranY]) {
                mines[ranX][ranY] = true;
                i--;
            }
        }
    }

    private int count(int x, int y) {
        int x1 = x - 1 < 0 ? 0 : x - 1;
        int y1 = y - 1 < 0 ? 0 : y - 1;
        int x2 = x + 2 > row ? row : x + 2;
        int y2 = y + 2 > col ? col : y + 2;
        int c = 0;
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if (mines[i][j])
                    c++;
            }
        }
        return c;
    }

    private void reveal(int i, int j) {
        if (i < 0 || j < 0 || i >= row || j >= col || buttons[i][j].getBackground() == Color.GRAY)
            return;

        int c = count(i, j);
        buttons[i][j].setBackground(Color.GRAY);
        buttons[i][j].setEnabled(false);
        buttons[i][j].setText((c == 0 ? "" : c) + "");
        buttons[i][j].setFont(new Font("Consolas", Font.BOLD, 30));

        if (c == 0) {
            reveal(i - 1, j);
            reveal(i, j + 1);
            reveal(i + 1, j);
            reveal(i, j - 1);
        }

        checkWin();
    }

    private void checkWin() {
        int openedCells = 0;
        int correctFlags = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (buttons[i][j].getBackground() == Color.GRAY && !mines[i][j]) {
                    openedCells++;
                }
                if (mines[i][j] && flags[i][j]) {
                    correctFlags++;
                }
            }
        }

        if (openedCells + countTotalMines() == row * col) { 
            JOptionPane.showMessageDialog(this, "You win!");
            resetGame();
        }
    }

    private int countTotalMines() {
        int totalMines = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mines[i][j]) {
                    totalMines++;
                }
            }
        }
        return totalMines;
    }

    private void resetGame() {
        dispose();
        new MineSweeper("Mine Sweeper", row, col);
    }

    public MineSweeper(String title, int row, int col) {
        super(title);
        this.row = row;
        this.col = col;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(row, col));
        buttons = new JButton[row][col];
        init(20);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.GREEN);

                if (mines[i][j]) {
                    buttons[i][j].setText("B");
                }

                buttons[i][j].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        JButton b = (JButton) e.getSource();
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < col; j++) {
                                if (b == buttons[i][j]) {
                                    if (SwingUtilities.isRightMouseButton(e)) {
                                        toggleFlag(i, j);
                                    } else if (!flags[i][j]) {
                                        if (mines[i][j]) {
                                            buttons[i][j].setText("B");
                                            buttons[i][j].setBackground(Color.RED);
                                            JOptionPane.showMessageDialog(null, "You lose!");
                                            showAllMines();
                                            resetGame();
                                        } else {
                                            reveal(i, j);
                                        }
                                    }
                                    return;
                                }
                            }
                        }
                    }
                });

                jPanel.add(buttons[i][j]);
            }
        }

        add(jPanel);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void toggleFlag(int i, int j) {
        if (buttons[i][j].getBackground() == Color.GRAY) {
            return;
        }
        if (flags[i][j]) {
            flags[i][j] = false;
            buttons[i][j].setText("");
            buttons[i][j].setBackground(Color.GREEN);
            flagCount--;
        } else {
            flags[i][j] = true;
            buttons[i][j].setText("F");
            buttons[i][j].setBackground(Color.YELLOW);
            flagCount++;
        }
    }

    private void showAllMines() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mines[i][j]) {
                    buttons[i][j].setText("B");
                    buttons[i][j].setBackground(Color.RED);
                }
            }
        }
    }

    public static void main(String[] args) {
        new MineSweeper("Mine Sweeper", 10, 10);
    }
}
