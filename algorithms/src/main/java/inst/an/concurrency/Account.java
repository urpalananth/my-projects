package inst.an.concurrency;

public class Account {
	private long balance;
	
	public Account(int amount) {
		this.balance = amount;
	}

	public synchronized void deposit(long amount){
		if(amount > 0)
			balance += amount;
	}
	
	public synchronized boolean withdraw(long amount){
		if(amount > 0 && balance >= amount){
			balance -= amount;
			return true;
		}
		return false;
	}
	
	public synchronized long getBalance(){
		return balance;
	}
}
