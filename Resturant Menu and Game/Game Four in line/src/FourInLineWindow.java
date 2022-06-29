import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FourInLineWindow extends JFrame {
	// the window width
	static final int WINDOW_WIDTH = 1000;
	// the window height
	static final int WINDOW_HEIGHT = 500;
	// the number of columns
	static final int COLUMNS = 7;
	// the number of lines
	static final int LINES = 5;
	// the margin of the table from the edges of the panel
	static final int MARGIN = 5;
	// empty square
	static final int EMPTY = 0;
	// a blue square
	static final int BLUE = 1;
	// a red square
	static final int RED = 2;
	// the margin of the circle from its square
	static final int CIRCLE_MARGIN = 4;
	// the number of sequence of circles to win
	static final int NUMBER_OF_WIN_CIRCLES = 4;
	// the content of the game
	private int game[][];
	// the color of the current turn player
	private int currentTurn = BLUE;
	public FourInLineWindow() {
		// initialize the game
		initGame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// create the panel of the game
		JPanel gamePanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				int width = getWidth() - 2 * MARGIN;
				int height = getHeight() - 2 * MARGIN;
				int columnWidth = width / COLUMNS;
				int lineHeight = height / LINES;
				g.setColor(Color.BLACK);
				// draw the vertical lines
				for (int i = 0; i <= COLUMNS; i++) {
					g.drawLine(MARGIN + i * columnWidth, MARGIN, MARGIN + i * columnWidth, MARGIN + LINES * lineHeight);
				}
				// draw the horizontal lines
				for (int i = 0; i <= LINES; i++) {
					g.drawLine(MARGIN, MARGIN + i * lineHeight, COLUMNS * columnWidth + MARGIN, MARGIN + i * lineHeight);
				}
				int circlePerimeter = Math.min(columnWidth - 2 * CIRCLE_MARGIN, lineHeight - 2 * CIRCLE_MARGIN);
				// draw the circles
				for (int i = 0; i < LINES; i++) {
					for (int j = 0; j < COLUMNS; j++) {
						if (game[i][j] == EMPTY)
							continue;
						g.setColor(game[i][j] == BLUE ? Color.BLUE : Color.RED);
						g.fillOval(MARGIN + j * columnWidth + (columnWidth - circlePerimeter) / 2, MARGIN + i * lineHeight + (lineHeight - circlePerimeter) / 2, circlePerimeter, circlePerimeter);
					}
				}
			}
		};
		// put the game panel in the center of the window
		add(gamePanel, BorderLayout.CENTER);
		// panel for the all buttons
		JPanel downPanel = new JPanel();
		downPanel.setLayout(new BorderLayout());
		// add it to the buttom of the window
		add(downPanel, BorderLayout.SOUTH);
		// a panel for the buttons for each column
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, COLUMNS));
		// add the buttons for each column
		for (int i = 0; i < COLUMNS; i++) {
			// the text on the button is the column serial number
			JButton button = new JButton(String.valueOf(i + 1));
			buttonsPanel.add(button);
			// for the ActionListener of the button
			final int x = i;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					runStep(x);
				}
			});
		}
		downPanel.add(buttonsPanel, BorderLayout.NORTH);
		JPanel clearPanel = new JPanel();
		downPanel.add(clearPanel, BorderLayout.SOUTH);
		JButton clearButton = new JButton("Clear");
		clearPanel.add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initGame();
			}
		});
	}

	// initialize the game
	private void initGame() {
		game = new int[LINES][COLUMNS];
		currentTurn = BLUE;
		// refresh the screen
		repaint();
	}
	// run one turn
	private void runStep(int column) {
		// check if the column is full
		if (game[0][column] != EMPTY) {
			JOptionPane.showMessageDialog(null,
					"Column is full", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// look for the first empty square in the column
		for (int i = LINES - 1; i >= 0; i--) {
			if (game[i][column] == EMPTY) {
				game[i][column] = currentTurn;
				break;
			}
		}
		// refresh the screen
		repaint();
		if (checkIfWin()) {
			initGame();
		}
		else {
			// switch the player
			currentTurn = currentTurn == BLUE ? RED : BLUE;
		}
	}
	private boolean checkIfWin() {
		int count = 0; // count the length of a sequence
		int winner = BLUE;
		for (int i = 0; count != NUMBER_OF_WIN_CIRCLES && i < LINES; i++) {
			for (int j = 0; count != NUMBER_OF_WIN_CIRCLES && j < COLUMNS; j++) {
				if (game[i][j] == EMPTY)
					continue;
				// potential winner
				winner = game[i][j];
				count = 0;
				// check in the line
				if (j <= COLUMNS - NUMBER_OF_WIN_CIRCLES) {
					for (int k = 0; k < NUMBER_OF_WIN_CIRCLES; k++) {
						if (game[i][j + k] == game[i][j])
							count++;
						else
							break;
					}
					if (count == NUMBER_OF_WIN_CIRCLES) {
						break;
					}
				}
				// check in the column
				if (i <= LINES - NUMBER_OF_WIN_CIRCLES) {
					count = 0;
					for (int k = 0; k < NUMBER_OF_WIN_CIRCLES; k++) {
						if (game[i + k][j] == game[i][j])
							count++;
						else
							break;
					}
					if (count == NUMBER_OF_WIN_CIRCLES) {
						break;
					}
				}
				// check one diagonal
				if ((j <= COLUMNS - NUMBER_OF_WIN_CIRCLES) &&
					(i <= LINES - NUMBER_OF_WIN_CIRCLES)) {
					count = 0;
					for (int k = 0; k < NUMBER_OF_WIN_CIRCLES; k++) {
						if (game[i + k][j + k] == game[i][j])
							count++;
						else
							break;
					}
					if (count == NUMBER_OF_WIN_CIRCLES) {
						break;
					}
				}
				// check second diagonal
				if ((j >= COLUMNS - NUMBER_OF_WIN_CIRCLES) &&
						(i <= LINES - NUMBER_OF_WIN_CIRCLES)) {
					count = 0;
					for (int k = 0; k < NUMBER_OF_WIN_CIRCLES; k++) {
						if (game[i + k][j - k] == game[i][j])
							count++;
						else
							break;
					}
				}
			}
		}
		// show win message
		if (count == NUMBER_OF_WIN_CIRCLES) {
			String winnerName = ((winner == BLUE) ? "Blue" : "Red");
			JOptionPane.showMessageDialog(null,
					"The winner is " + winnerName, "WIN",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// create and open the window
		FourInLineWindow window = new FourInLineWindow();
		window.setVisible(true);
	}
}
