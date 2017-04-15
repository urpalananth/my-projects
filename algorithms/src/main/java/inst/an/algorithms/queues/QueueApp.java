package inst.an.algorithms.queues;
public class QueueApp {
	public static void main(String[] args) {
		Queue<String> q = new Queue<>();
		q.push("A");
		q.push("B");
		q.push("C");
		q.push("D");
		
		System.out.println(q.pop());
		System.out.println(q.pop());
		System.out.println(q.pop());
		System.out.println(q.pop());
	}
}
