package inst.an.algorithms.stack;

public class Node<T> {
	
	T value;
	private Node<T> next;
	
	public Node(T value) {
		this.value = value;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	public Node<T> getNext() {
		return this.next;
	}
	public T getValue() {
		return value;
	}
}
