import javax.swing.JOptionPane;

public class Main {
	private static void run() {
		Bulls game = Bulls.generateRandomNumber();
		for (int num_Of_guess = 0;; num_Of_guess++) {
			String guess = JOptionPane.showInputDialog("please enter your guess :");
			if (guess == null) {
				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit Game",
						JOptionPane.YES_NO_OPTION)) {
					return;
				} else {
					continue;

				}

			}
			String output = Bulls.checks(guess);
			if (output == null) { // checks if good format
				output = game.playGuess(guess);
			}
			if (output == null) { // check if good format and good num
				JOptionPane.showMessageDialog(null, "you are  correct", "IDO", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				JOptionPane.showMessageDialog(null, "you are incorrect\n" + output, "IDO",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}

	}

	public static void main(String[] args) {

		run();
	}
}
