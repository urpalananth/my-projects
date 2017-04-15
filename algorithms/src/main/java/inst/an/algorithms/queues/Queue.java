package inst.an.algorithms.queues;
/***
 * 
 * @author akumar36
 *
 * @param <T>
 */
public class Queue <T> {
	private Node<T> front, rear;
	public void push(T value){
		Node<T> newNode = new Node<>(value);
		if(front == null){
			front = rear = newNode;
		}else{
			rear.setNext(newNode);
			rear = newNode;
		}
	}
	public T pop(){
		if(front == null)
			return null;
		Node<T> returnNode = front;
		front = front.getNext();
		return returnNode.getValue();
	}
}
