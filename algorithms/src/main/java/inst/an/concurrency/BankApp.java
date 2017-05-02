package inst.an.concurrency;

public class BankApp {
	public static void main(String[] args) {
		final Account account1 = new Account(1000);
		final Account account2 = new Account(1000);
		
		AccountService.transfer(account1, account2, 500);
		
		System.out.println(account1.getBalance());
		System.out.println(account2.getBalance());
	}
}
