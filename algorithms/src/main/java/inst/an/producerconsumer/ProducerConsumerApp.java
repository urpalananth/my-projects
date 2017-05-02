package inst.an.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerApp {
	public static void main(String[] args) {
		Queue<Integer> intQueue = new LinkedList<>();
		Producer p1 = new Producer(intQueue);
		Consumer c1 = new Consumer(intQueue);
		
		Thread t1 = new Thread(p1, "producer");
		
		Thread t2 = new Thread(c1, "consumer");
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
