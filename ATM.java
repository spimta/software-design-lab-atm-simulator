
/*ATM class
 * record ATM current state
 * perform action on currentAccount*/


// ATM class
public class ATM {
	private Bank bank;
	private Account currentAccount;
	private int accountNumber;
	private int state;
	
	// state
	public static final int START = 1;
	public static final int PIN = 2;
	public static final int ACCOUNT = 3;
	public static final int PINCHECKFAILED = 4;
	public static final int BALANCE = 5;
	public static final int WITHDRAW = 6;
	public static final int DEPOSIT = 7;
	public static final int ACCOUNTEXIT = 8;
	public static final int WITHDRAWSUCCESS = 9;
	public static final int WITHDRAWFAILED = 10;
	public static final int DEPOSITSUCCESS = 11;
	public static final int DEPOSITFAILED = 12;
	
	// Constructor
	ATM(Bank bank) {
		this.bank = bank;
		reset();
	}
	
	// get current state
	public int getState() {
		return state;
	}
	
	// get current account balance
	public double getBalance() {
		return currentAccount.getBalance();
	}
	
	// reset the ATM
	public void reset() {
		currentAccount = null;
		accountNumber = -1;
		state = START;
	}
	
	// set account number
	public void setAccountNumber(int number) {
		assert state == START;
		accountNumber = number;
		state = PIN;
	}
	
	// get account number
	public int getAccountNumber() {
		return accountNumber;
	}
	
	// check the pin
	public void checkPin(int pin) {
		assert state == PIN;
		currentAccount = bank.findAccount(accountNumber, pin);
		if (currentAccount == null)
			state = PINCHECKFAILED;
		else
			state = ACCOUNT;
	}
	
	// Wrong pin
	public void checkPinFailed() {
		assert state == PINCHECKFAILED;
		state = START;
	}
	
	// account main menu
	public void accountMenuSelection(int selection) {
		assert state == ACCOUNT;
		if (selection == 1) state = BALANCE;
		else if (selection == 2) state = WITHDRAW;
		else if (selection == 3) state = DEPOSIT;
		else if (selection == 4) goBack();
		else state = ACCOUNT;
	}
	
	// show balance
	public void balanceMenuSelection(int selecion) {
		assert state == BALANCE;
		state = ACCOUNT;
	}
	
	// logout account
	public void exitMenuSelection(int selection) {
		assert state == ACCOUNTEXIT;
		if (selection == 1) state = START;
		else state = ACCOUNT;
	}
	
	// withdraw menu
	public void withdrawMenuSelection(int selection) {
		assert state == WITHDRAW;
		if (selection == 1)  {
			if (currentAccount.withdraw(20)) state = WITHDRAWSUCCESS;
			else state = WITHDRAWFAILED;
		}
		
		else if (selection == 2)  {
			if (currentAccount.withdraw(40)) state = WITHDRAWSUCCESS;
			else state = WITHDRAWFAILED;
		}
		
		else if (selection == 3)  {
			if (currentAccount.withdraw(60)) state = WITHDRAWSUCCESS;
			else state = WITHDRAWFAILED;
		}
		
		else if (selection == 4)  {
			if (currentAccount.withdraw(100)) state = WITHDRAWSUCCESS;
			else state = WITHDRAWFAILED;
		}
		
		else if (selection == 5)  {
			if (currentAccount.withdraw(200)) state = WITHDRAWSUCCESS;
			else state = WITHDRAWFAILED;
		}
		
		else if (selection == 6) {
			goBack();
		}
		
	}
	
	// deposit
	public void depositAmount(int amount) {
		assert state == DEPOSIT;
		if (amount >= 0) {
			currentAccount.deposit(amount);
			state = DEPOSITSUCCESS;
		}
		else
			state = DEPOSITFAILED;
		
	}
	
	// back to previous state
	public void goBack() {
		if (state == BALANCE || state == WITHDRAW || state == DEPOSIT  || state == WITHDRAWSUCCESS || state == WITHDRAWFAILED) state = ACCOUNT;
		else if (state == ACCOUNT) state = ACCOUNTEXIT;
	}
	
	// set the state
	public void setState(int state) {
		this.state = state;
	}
	

}
