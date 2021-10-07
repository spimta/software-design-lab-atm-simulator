
// Account class
public class Account {
	private int accountNumber;
	private int pin;
	private double balance;
	
	public Account(int accountNumber, int pin) {
		this.accountNumber = accountNumber;
		this.pin = pin;
	}
	
	public Account() {
		this.accountNumber = 0;
		this.pin = 0;
	}
	
	public boolean isNewAccount() {
		if (accountNumber == 0) {
			return true;
		}
		return false;
	}
	
	public void accountInitializer(int accountNumber, int pin) {
		this.accountNumber = accountNumber;
		this.pin = pin;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public int getPin() {
		return pin;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public boolean deposit(int depAmount) {
		balance += depAmount;
		return true;
	}
	
	public boolean withdraw(int witAmount) {
		if (balance - witAmount >= 0) {
			balance -= witAmount;
			return true;
		}
		return false;
	}
	
	public boolean match(int accountNumber, int pin) {
		if (this.accountNumber == accountNumber && this.pin == pin) return true;
		return false;
	}

}
