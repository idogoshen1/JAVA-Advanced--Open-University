
public abstract class Animal implements Cloneable {
	private String name;
	private int age;
	private String color;

	public Animal(String name, int age, String color) {
		this.name = name;
		this.age = age;
		this.color = color;
	}
	public Animal(Animal animal) {
		this.name = animal.getName();
		this.age = animal.getAge();
		this.color = animal.getColor();
	}
	public abstract void eat();
	public abstract void sleep();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return getClass().getSimpleName() + " named: " + name + ", age: " + age + ", color: " + color;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Animal))
			return false;
		Animal animal = (Animal) obj;
		return name.equals(animal.getName()) &&
				color.equals(animal.getColor()) &&
				(age == animal.getAge());
	}
}
