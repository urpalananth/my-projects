package inst.an.producerconsumer;

import java.util.Queue;

public class Consumer implements Runnable{
	private Queue<Integer> intQueue;
	public Consumer(Queue<Integer> intQueue) {
		super();
		this.intQueue = intQueue;
	}
	@Override
	public void run() {
		while(true){
			synchronized (intQueue) {
				if(intQueue.isEmpty())
					try {
						intQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					intQueue.poll();
					System.out.println("Item consumed .... so now the size is - "+intQueue.size());
					intQueue.notifyAll();
			}
		}
	}
	
}
