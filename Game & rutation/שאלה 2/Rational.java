
public class Rational {
	private int numerator; // מונה
	private int denominator; // מכנה
	public Rational(int numerator, int denominator) {
		if (denominator > 0) {
			this.numerator = numerator;
			this.denominator = denominator;
		}
		else {
			this.numerator = 0;
			this.denominator = 1;
		}
	}
	public boolean greaterThan(Rational r) {
		return numerator * r.getDenominator() > denominator * r.getNumerator();
	}
	public boolean equals(Rational r) {
		return numerator * r.getDenominator() == denominator * r.getNumerator();
	}
	public Rational plus(Rational r) {
		int newNumerator = numerator * r.getDenominator() + denominator * r.getNumerator();
		int newDenominator = denominator * r.getDenominator();
		Rational answer = new Rational(newNumerator, newDenominator);
		return answer;
	}
	public Rational minus(Rational r) {
		int newNumerator = numerator * r.getDenominator() - denominator * r.getNumerator();
		int newDenominator = denominator * r.getDenominator();
		Rational answer = new Rational(newNumerator, newDenominator);
		return answer;
	}
	public Rational multiply(Rational r) {
		int newNumerator = numerator * r.getNumerator();
		int newDenominator = denominator * r.getDenominator();
		Rational answer = new Rational(newNumerator, newDenominator);
		return answer;
	}
	public int getNumerator() {
		return numerator;
	}
	public int getDenominator() {
		return denominator;
	}
	public String toString() {
		if (denominator == 1)
			return numerator + "";
		else if (numerator != 0)
			return numerator + "/" + denominator;
		else
			return "0";
	}
	private int gcd(int x, int y) {
		if (x == 0)
			return y;
		if (y == 0)
			return x;
		if (x > y)
			return gcd(y, x % y);
		else
			return gcd(x, y % x);
	}
	public Rational reduce() {
		int greatCommonDiv = gcd(numerator, denominator);
		int newNumerator = numerator / greatCommonDiv;
		int newDenominator = denominator / greatCommonDiv;
		Rational answer = new Rational(newNumerator, newDenominator);
		return answer;
	}
}
