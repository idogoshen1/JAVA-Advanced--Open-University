
public class Dog extends Mammal {
	private Owner owner;
	
	public Dog(String name, int age, String color, Owner owner) {
		super(name, age, color);
		this.owner = owner;
	}

	public Dog(Dog dog) {
		super(dog);
		try {
			owner = (Owner) dog.getOwner().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void eat() {
		System.out.println(toString() + " eat");
	}

	@Override
	public void sleep() {
		System.out.println(toString() + " sleep");
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return super.toString() + " owned by: " + owner.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Dog))
			return false;
		Dog dog = (Dog) obj;
		return owner.equals(dog.getOwner()) && super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Dog(this);
	}
}
