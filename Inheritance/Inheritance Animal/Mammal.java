
public abstract class Mammal extends Animal {

	public Mammal(String name, int age, String color) {
		super(name, age, color);
	}
	public Mammal(Mammal mammal) {
		super(mammal);
	}
}
