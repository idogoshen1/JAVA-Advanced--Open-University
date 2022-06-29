import java.util.ArrayList;

public class AnimalMain {

	public static void main(String[] args) {
		try {
			ArrayList<Animal> animals = new ArrayList<>();
			Bird bird1 = new Bird("Yossi", 5, "red");
			Bird bird2 = new Bird("Moshe", 3, "yellow");
			Owner owner1 = new Owner("Ido", 9272283);
			Dog dog1 = new Dog("Buky", 10, "brown", owner1);
			Dog dog2 = (Dog) dog1.clone();
			Fish fish1 = new Fish("Tuna", 4, "gray");
			Fish fish2 = new Fish("Shark", 12, "silver");
			Lizard lizard1 = new Lizard("Jhon", 7, "green");
			Lizard lizard2 = new Lizard("Jimmy", 6, "ofwhite");
			animals.add(bird1);
			animals.add(bird2);
			animals.add(dog1);
			animals.add(dog2);
			animals.add(fish1);
			animals.add(fish2);
			animals.add(lizard1);
			animals.add(lizard2);
			Animal animal;
			// activate eat on all animals
			for (int i = 0; i < animals.size(); i++) {
				animal = animals.get(i);
				animal.eat();
			}
			// activate sleep on all animals
			for (int i = 0; i < animals.size(); i++) {
				animal = animals.get(i);
				animal.sleep();
			}
			bird1.fly();
			fish1.swim();
			lizard1.crawl();
			System.out.println(dog1);
			System.out.println(dog2);
			dog1.getOwner().setPhone(65432);
			System.out.println(dog1);
			System.out.println(dog2);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

}
