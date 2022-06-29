import java.util.ArrayList;
import java.util.Collections;

public class Bulls {
	private final String targetNumber;
	private static final int Num_Of_Digit = 4;

	public Bulls(String targetNumber) {
		this.targetNumber = targetNumber;
		//System.out.println(targetNumber); // line for checks whats random num is
	}

	public static String checks(String guess) {
		if (guess.length() != Num_Of_Digit) {
			return "wrong length";
		}
		for (int i = 0; i < guess.length(); i++) {
			for (int j=i+1; j< guess.length(); j++) {
				if (guess.charAt(i) < '0' || guess.charAt(j) > '9') {
					return "the guess should be with numbers";
				} else if (guess.charAt(i) == guess.charAt(j)) {
					return "the sequence cant be with same number";
				}
			}
		}
		return null;
	}
	public String playGuess(String guess) {
		int bulls = 0;
		int hit = 0;
		for (int i = 0; i < targetNumber.length(); i++) {
			if (targetNumber.charAt(i) == guess.charAt(i)) {
				bulls += 1;
			} else if (guess.contains(targetNumber.charAt(i) + "")) {
				hit += 1;
			}
		}
		if (bulls == targetNumber.length()) {
			return null;
		} else {
			return String.format("%d bulls and %d hits", bulls, hit);
		}
	}

	public static Bulls generateRandomNumber() {
		ArrayList<Integer> digits = new ArrayList<>();

		for (int i = 0; i <= 9; i++) {
			digits.add(i);
		}

		Collections.shuffle(digits);

		return new Bulls("" + digits.get(0) + digits.get(1) + digits.get(2) + digits.get(3));
	}
}