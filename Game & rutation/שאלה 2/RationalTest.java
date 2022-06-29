import java.util.Scanner;

public class RationalTest {
	private static Rational read() {
		Scanner reader = new Scanner(System.in);
		int numerator;
		int denominator;
		System.out.print("Enter numerator: ");
		numerator = reader.nextInt();
		do {
			System.out.print("Enter denominator: ");
			denominator = reader.nextInt();
		}while(denominator <= 0);
		return new Rational(numerator, denominator);
	}

	public static void main(String[] args) {
		Rational a, b;
		a = read();
		b = read();
		Rational c = a.plus(b);
		System.out.println(a.reduce() + "+" + b.reduce() + "=" + c.reduce());
		c = a.minus(b);
		System.out.println(a.reduce() + "-" + b.reduce() + "=" + c.reduce());
		c = a.multiply(b);
		System.out.println(a.reduce() + "*" + b.reduce() + "=" + c.reduce());

		if (a.greaterThan(b))
			System.out.println("a is greater than b");
		else if (b.greaterThan(a))
			System.out.println("b is greater than a");
		else
			System.out.println("a and b are equal");
	}

}
