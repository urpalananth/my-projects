package inst.an.producerconsumer;

import java.util.Queue;

public class Producer implements Runnable{
	private Queue<Integer> intQueue;
	public Producer(Queue<Integer> intQueue) {
		super();
		this.intQueue = intQueue;
	}
	@Override
	public void run() {
		while(true){
			synchronized (intQueue) {
				if(!intQueue.isEmpty())
					try {
						intQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					intQueue.add(1);
					System.out.println("Item produced .... so now the size is - "+intQueue.size());
					intQueue.notifyAll();
			}
		}
	}
	
}
