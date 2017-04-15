package inst.an.algorithms.stack;

public class Stack<T> {
	private Node<T> top;
	public void push(T value){
		Node<T> newNode = new Node<>(value);
		newNode.setNext(top);
		top = newNode;
	}
	public T pop(){
		if(top == null)
			return null;
		Node<T> returnNode = top;
		top = top.getNext();
		return returnNode.getValue();
	}
}
