import java.util.ArrayList;

public class BigInt implements Comparable<BigInt> {
	// the zero number
	private static BigInt zero = new BigInt("0");
	// the one number
	private static BigInt one = new BigInt("1");

	// the number itself on an array list of figures
	private ArrayList<Integer> number = new ArrayList<>();
	private boolean positive = true;

	// create a new BigInt from a string
	public BigInt(String strNumber) {
		positive = !((strNumber.length() > 0) &&
				(strNumber.charAt(0) == '-'));
		int last = positive ? 0 : 1; // the end of the string
		// check if there is + sign
		if (positive && (strNumber.length() > 0) &&
				(strNumber.charAt(0) == '+'))
			last = 1;
		for (int i = strNumber.length() - 1; i >= last; i--) {
			char figure = strNumber.charAt(i);
			// check that this is a legal figure
			if ((figure >= '0') && (figure <= '9')) {
				number.add(figure - '0');
			}
			else {
				throw new IllegalArgumentException("Bad number");
			}
		}
	}
	// default constructor for internal usage
	private BigInt() {
	}
	// copy constructor for internal usage
	private BigInt(BigInt other) {
		this.positive = other.positive;
		ArrayList<Integer> otherNumber = other.getNumber();
		for (int i = 0; i < otherNumber.size(); i++) {
			number.add(otherNumber.get(i));
		}
	}
	// clean redundant zeros from the end
	private void cleanZeros() {
		int size = number.size();
		while((size > 0) && (number.get(size - 1) == 0)) {
			number.remove(size - 1);
			size = number.size();
		}
	}
	// add BigInt to this
	public BigInt plus(BigInt other) {
		BigInt answer = new BigInt();
		// both are positive
		if (positive && other.isPositive()) {
			answer.setPositive(positive);
			ArrayList<Integer> otherNumber = other.getNumber();
			ArrayList<Integer> newNumber = new ArrayList<>();
			// the number of figures of the bigger number
			int max = number.size() > otherNumber.size() ? number.size() : otherNumber.size();
			int rest = 0;
			int first = 0;
			int second = 0;
			int next;
			for (int i = 0; i < max; i++) {
				// the next figure from the first number
				if (i < number.size())
					first = number.get(i);
				else
					first = 0;
				// the next figure from the second number
				if (i < otherNumber.size())
					second = otherNumber.get(i);
				else
					second = 0;
				// the next figure
				next = (first + second + rest);
				// the rest for the next figure
				rest = next / 10;
				next %= 10;
				// add the next figure to the result
				newNumber.add(next);
			}
			// add the rest
			if (rest > 0)
				newNumber.add(rest);
			answer.setNumber(newNumber);
		}
		else if (positive && !other.isPositive()) {
			// they have opposite sign
			BigInt temp = new BigInt(other);
			temp.setPositive(true);
			answer = minus(temp);
		}
		else if (!positive && other.isPositive()) {
			// they have opposite sign
			BigInt temp = new BigInt(this);
			temp.setPositive(true);
			answer = other.minus(temp);
		}
		else {
			// both are negative
			BigInt temp1 = new BigInt(this);
			temp1.setPositive(true);
			BigInt temp2 = new BigInt(other);
			temp2.setPositive(true);
			answer = temp1.plus(temp2);
			answer.setPositive(false);
		}
		return answer;
	}
	// subtract BigInt from this
	public BigInt minus(BigInt other) {
		BigInt answer = new BigInt();
		// both are positive
		if (positive && other.isPositive()) {
			BigInt bigger, smaller;
			int compare = this.compareTo(other);
			// both are the same
			if (compare == 0)
				return answer;
			if (compare < 0) {
				bigger = other;
				smaller = this;
			}
			else {
				bigger = this;
				smaller = other;
			}
			// copy the bigger because it will be changed
			bigger = new BigInt(bigger);
			// the result will be positive if this is the bigger
			answer.setPositive(compare > 0);
			ArrayList<Integer> biggerNumber = bigger.getNumber();
			ArrayList<Integer> smallerNumber = smaller.getNumber();
			ArrayList<Integer> newNumber = new ArrayList<>();
			int size = biggerNumber.size();
			int first = 0;
			int second = 0;
			int next;
			for (int i = 0; i < size; i++) {
				first = biggerNumber.get(i);
				if (i < smallerNumber.size())
					second = smallerNumber.get(i);
				else
					second = 0;
				next = first - second;
				if (next < 0) {
					next += 10;
					// minus 1 in the rest of the number
					for (int j = i + 1; j < size; j++) {
						int num = biggerNumber.get(j);
						if (num == 0) {
							biggerNumber.set(j, 9);
						}
						else {
							biggerNumber.set(j, num - 1);
							break;
						}
					}
				}
				newNumber.add(next);
			}
			answer.setNumber(newNumber);
		}
		else if (positive && !other.isPositive()) {
			// they have opposite sign
			BigInt temp = new BigInt(other);
			temp.setPositive(true);
			answer = plus(temp);
		}
		else if (!positive && other.isPositive()) {
			// they have opposite sign
			BigInt temp = new BigInt(this);
			temp.setPositive(true);
			answer = other.plus(temp);
			answer.setPositive(false);
		}
		else {
			// both are negative
			BigInt temp2 = new BigInt(other);
			temp2.setPositive(true);
			answer = temp2.plus(this);
		}
		answer.cleanZeros();
		return answer;
	}
	// multiply BigInt by this
	public BigInt multiply(BigInt other) {
		BigInt answer = new BigInt();
		ArrayList<Integer> newNumber = new ArrayList<>();
		answer.setNumber(newNumber);
		int rest = 0;
		int next;
		for (int i = 0; i < number.size(); i++) {
			int num = number.get(i);
			BigInt newOther = new BigInt(other);
			ArrayList<Integer> otherNumber = newOther.getNumber();
			// insert zeros 
			for (int j = 0; j < i; j++) {
				otherNumber.add(0, 0);
			}
			for (int j = 0; j < otherNumber.size(); j++) {
				next = num * otherNumber.get(j) + rest;
				rest = next / 10;
				next %= 10;
				otherNumber.set(j,  next);
			}
			if (rest > 0)
				otherNumber.add(rest);
			answer = answer.plus(newOther);
		}
		// the sign of the result is positive if both have the
		// same sign otherwise it is negative
		answer.setPositive(positive == other.isPositive());
		answer.cleanZeros();
		return answer;
	}
	// divide BigInt by this
	BigInt divide(BigInt other) {
		if (other.compareTo(zero) == 0)
			throw new ArithmeticException("Divide by zero");
		BigInt answer = new BigInt();
		BigInt thisCopy = new BigInt(this);
		while(thisCopy.compareTo(other) >= 0) {
			answer = answer.plus(one);
			thisCopy = thisCopy.minus(other);
		}
		answer.cleanZeros();
		return answer;
	}
	@Override
	public String toString() {
		if (number.isEmpty())
			return "0";
		String answer = "";
		if (! positive)
			answer = "-";
		for (int i = number.size() - 1; i >= 0; i--) {
			answer += number.get(i);
		}
		return answer;
	}
	public void setPositive(boolean positive) {
		this.positive = positive;
	}
	public boolean isPositive() {
		return positive;
	}
	private ArrayList<Integer> getNumber() {
		return number;
	}
	private void setNumber(ArrayList<Integer> number) {
		this.number = number;
	}
	@Override
	public int compareTo(BigInt other) {
		if (positive && !other.isPositive())
			return 1;
		if (!positive && other.isPositive())
			return -1;
		if (positive && other.isPositive()) {
			ArrayList<Integer> otherNumber = other.getNumber();
			// check the number of figures
			if (number.size() > otherNumber.size())
				return 1;
			if (number.size() < otherNumber.size())
				return -1;
			int first;
			int second;
			for (int i = number.size() - 1; i >= 0; i--) {
				first = number.get(i);
				second = otherNumber.get(i);
				if (first > second)
					return 1;
				else if (first < second)
					return -1;
			}
		}
		else {
			// both are negative
			ArrayList<Integer> otherNumber = other.getNumber();
			// check the number of figures
			if (number.size() > otherNumber.size())
				return -1;
			if (number.size() < otherNumber.size())
				return 1;
			int first;
			int second;
			for (int i = number.size() - 1; i >= 0; i--) {
				first = number.get(i);
				second = otherNumber.get(i);
				if (first > second)
					return -1;
				else if (first < second)
					return 1;
			}
		}
		return 0;
	}
}
