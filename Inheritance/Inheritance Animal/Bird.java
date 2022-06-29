
public class Bird extends Animal implements Flyable {

	public Bird(String name, int age, String color) {
		super(name, age, color);
	}

	public Bird(Bird bird) {
		super(bird);
	}

	@Override
	public void fly() {
		System.out.println(toString() + " fly");
	}

	@Override
	public void eat() {
		System.out.println(toString() + " eat");
	}

	@Override
	public void sleep() {
		System.out.println(toString() + " sleep");
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Bird))
			return false;
		return super.equals(obj);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Bird(this);
	}
}
