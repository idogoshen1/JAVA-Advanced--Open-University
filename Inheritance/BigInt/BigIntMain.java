import java.util.Scanner;

public class BigIntMain {

	public static BigInt read() {
		Scanner reader = new Scanner(System.in);
		String numberStr;
		BigInt answer = null;
		while (true) {
			System.out.print("Enter a number: ");
			try {
				numberStr = reader.next();
				answer = new BigInt(numberStr);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("Bad number");
			}
		}
		return answer;
	}
	public static void main(String[] args) {
		BigInt a = read();
		BigInt b = read();
		System.out.println(a + " * " + b + " = " + a.multiply(b));
		System.out.println(a + " + " + b + " = " + a.plus(b));
		System.out.println(a + " - " + b + " = " + a.minus(b));
		try {
			System.out.println(b + " / " + a + " = " + b.divide(a));
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println(a + " / " + b + " = " + a.divide(b));
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(a + ".compareTo(" + b + ") = " + b.compareTo(a));
	}

}
