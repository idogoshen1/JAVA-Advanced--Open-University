import java.util.Scanner;
import java.util.Stack;

public class LinkedListTest {
	static <T extends Comparable<T>> T max(LinkedList<T> list) {
		Node<T> head = list.getHead();
		if (head == null)
			return null;
		T max = head.getValue();
		while(head != null) {
			if (max.compareTo(head.getValue()) < 0)
				max = head.getValue();
			head = head.getNext();
		}
		return max;
	}

	public static void main(String[] args) {
		LinkedList<String> list1 = new LinkedList<>();
		LinkedList<String> list2 = new LinkedList<>();
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < 6; i++) {
			System.out.print("Enter a string: ");
			String s = scanner.next();
			list1.add(s);
		}
		System.out.println(list1);
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < 6; i++) {
			try {
				// push into the stack to get
				// the elements in opposite order
				stack.push(list1.remove());
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
		}
		while(!stack.empty()) {
			list2.add(stack.pop());
		}
		System.out.println(list2);

		// build a list of persons and find the oldest one
		LinkedList<Person> personsList = new LinkedList<Person>();
		personsList.add(new Person("Yossi", 5435677, 1990));
		personsList.add(new Person("Moshe", 5678987, 1956));
		personsList.add(new Person("Yael", 937474, 2010));
		personsList.add(new Person("Michal", 456789, 2001));
		Person maxPerson = max(personsList);
		System.out.println("Max person: " + maxPerson);
	}

}
