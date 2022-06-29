
public class Person implements Comparable<Person> {
	private String name;
	private int ID;
	private int yearOfBirth;

	public Person(String name, int iD, int yearOfBirth) {
		this.name = name;
		ID = iD;
		this.yearOfBirth = yearOfBirth;
	}

	@Override
	public int compareTo(Person person) {
		if (yearOfBirth < person.yearOfBirth)
			return 1;
		if (yearOfBirth > person.yearOfBirth)
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", ID=" + ID + ", yearOfBirth=" + yearOfBirth + "]";
	}
}
