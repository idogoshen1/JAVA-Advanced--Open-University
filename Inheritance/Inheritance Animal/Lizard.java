
public class Lizard extends Animal implements Crawlable {

	public Lizard(String name, int age, String color) {
		super(name, age, color);
	}

	public Lizard(Lizard lizard) {
		super(lizard);
	}
	@Override
	public void crawl() {
		System.out.println(toString() + " crawl");
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
		if (!(obj instanceof Lizard))
			return false;
		return super.equals(obj);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Lizard(this);
	}
}
