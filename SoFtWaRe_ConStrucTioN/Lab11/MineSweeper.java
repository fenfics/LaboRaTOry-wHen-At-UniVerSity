import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Mine extends JFrame {
	private int row;
	private int col;
	private JButton[][] buttons;
	private boolean[][] mines;

	private void init(int i) {
		mines = new boolean[row][col];
		Random r = new Random();
		while (i > 0) {
			int ranX = r.nextInt(row - 1);
			int ranY = r.nextInt(col - 1);
			if (!mines[ranX][ranY]) {
				mines[ranX][ranY] = true;
				i--;
			}
		}

		// mines[0][0] = true ;
	}

	private int count(int x, int y) {
		int x1 = x - 1 < 0 ? 0 : x - 1;
		int y1 = y - 1 < 0 ? 0 : y - 1;
		int x2 = x + 2 > row - 1 ? row - 1 : x + 2;
		int y2 = y + 2 > col - 1 ? col - 1 : y + 2;
		System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
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

		if (i == -1 || j == col || i == row || j == -1)
			return;

		if (buttons[i][j].getBackground() == Color.GRAY)
			return;
		int c = count(i, j);

		buttons[i][j].setBackground(Color.GRAY);
		buttons[i][j].setEnabled(false);
		buttons[i][j].setText((c == 0 ? "" : c) + "");

		buttons[i][j].setFont(new Font("Consolas", Font.BOLD, 30));
		if (c > 0)
			return;
		reveal(i - 1, j);
		reveal(i, j + 1);
		reveal(i + 1, j);
		reveal(i, j - 1);

	}

	public Mine(String title, int row, int col) {
		super(title);
		this.col = col;
		this.row = row;
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(row, col));
		buttons = new JButton[row][col];
		init(20);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(Color.GREEN);
				if (mines[i][j])
					buttons[i][j].setText("B");
					buttons[i][j].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
						for (int i = 0; i < row; i++) {
							for (int j = 0; j < col; j++) {
								if (b == buttons[i][j]) {
									reveal(i, j);
									return;
								}
								if (buttons[i][j].getText()=="B") {
									JOptionPane.showMessageDialog(null, "You lose");
									System.exit(0);
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

	public static void main(String[] args) {
		new Mine("Mine Sweeper", 10, 10);
	}

}
