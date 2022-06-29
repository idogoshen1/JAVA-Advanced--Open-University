
public class LinkedList<T> {
	private Node<T> head;
	private Node<T> tail;

	public LinkedList() {
	}

	public Node<T> getHead() {
		return head;
	}

	public Node<T> getTail() {
		return tail;
	}
	public void add(T value) {
		Node<T> node = new Node<T>(value);
		if (tail != null) {
			tail.setNext(node);
			tail = node;
		}
		else {
			head = node;
			tail = node;
		}
	}
	public T remove() throws EmptyListException {
		if (head == null)
			throw new EmptyListException();
		Node<T> node = head;
		head = head.getNext();
		if (head == null)
			tail = null;
		return node.getValue();
	}

	@Override
	public String toString() {
		Node<T> node = head;
		String answer = "";
		while(node != null) {
			answer += node.getValue().toString();
			node = node.getNext();
			if (node != null)
				answer += ",";
		}
		return answer;
	}
}
