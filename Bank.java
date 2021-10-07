/*Bank class
 *read accounts from accounts.txt
 *findAccount to check account number and pin is matched in in accounts.txt */
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Bank class
public class Bank {
	private ArrayList<Account> accounts = new ArrayList<>();
	
	// read from account.txt
	public void readAccount(String filename) throws IOException {
		Scanner in = new Scanner(new FileReader(getClass().getResource(filename).getPath()));
		while (in.hasNext()) {
			int number = in.nextInt();
			int pin = in.nextInt();
			Account a = new Account(number, pin);
			accounts.add(a);
		}
		in.close();
	}
	
	// check either the account number and pin is matched
	public Account findAccount(int accountNumber, int pin) {
		for (Account i : accounts) {
			if (i.match(accountNumber, pin)) return i;
		}
		return null;
	}
	

}
