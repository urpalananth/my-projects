package inst.an.algorithms.maxthreads;

public class MaxNumberOfThreads {
	public static void main(String[] args) {
		for(int i = 0; ;i++){
			System.out.println("Thread "+i+" getting created.");
			new Thread(i+"").start();
		}
	}
}
