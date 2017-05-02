package inst.an.concurrency;

import java.util.Arrays;

public class AccountService {
	/*
	  This version has synchronized, but there's possibility of getting in to live lock and deadlock
	  Live-lock - The thread waits for the account object and it never gets it. Ends up waiting forever.
	  Deadlock  - Transfer happening in opposite direction.
	 * 
	 */
	public static void transfer(Account from, Account to, int amount) {
		synchronized(from){
			synchronized(to){
				if(from.withdraw(amount))
					to.deposit(amount);
			}
		}
	}
	
	/*
	  This version has synchronized, but there's possibility of getting in to live lock and deadlock
	  Live-lock - The thread waits for the account object and it never gets it. Ends up waiting forever.
	  Deadlock  - Transfer happening in opposite direction.
	  		- This can be solved by maintaining the order of synchronized objects, like below
	 * 
	 */
	public static void transfer1(Account from, Account to, int amount) {
		Account [] accounts = new Account[] {from, to};
		Arrays.sort(accounts);
		
		synchronized(accounts[0]){
			synchronized(accounts[1]){
				if(from.withdraw(amount))
					to.deposit(amount);
			}
		}
	}
}
