package inst.an.algorithms.linkedlist;

public class LinkedList<T> {
	private Node<T> root;
	public void insert(T value){
		Node<T> newNode = new Node<T>(value);
		if( root == null){
			root = newNode;
			return;
		}
		Node<T> temp = root;
		
		for(; temp.getNext() != null; temp = temp.getNext()){}
		temp.setNext(newNode);
	}
	public void display() {
		for(Node<T> temp = root; temp != null; temp = temp.getNext()){
			System.out.print(temp.getValue() +" --> ");
		}
	}
	public int size() {
		int size = 0;
		for(Node<T> temp = root; temp != null; temp = temp.getNext()){
			size++;
		}
		return size;
	}
}
