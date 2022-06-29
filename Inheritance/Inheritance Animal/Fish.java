
public class Fish extends Animal implements Swimable {

	public Fish(String name, int age, String color) {
		super(name, age, color);
	}

	public Fish(Fish fish) {
		super(fish);
	}

	@Override
	public void swim() {
		System.out.println(toString() + " swim");
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
		if (!(obj instanceof Fish))
			return false;
		return super.equals(obj);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Fish(this);
	}
}
