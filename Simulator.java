/*ATM Simulator
 *Simulate an atm via command line
 *mainly use for debug*/

import java.io.IOException;
import java.util.Scanner;

// ATM simulator
public class Simulator {
	public static void simulator(String[] args) {
		ATM atm;
		try {
			Bank bank = new Bank();
			bank.readAccount("accounts.txt");
			atm = new ATM(bank);
		} catch (IOException e) {
			System.out.println("Error opening accounts file.");
			return;
		}
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			int state = atm.getState();
			if (state == ATM.START) {
				System.out.println("Please enter account number: ");
				int number = input.nextInt();
				atm.setAccountNumber(number);
			}
			else if (state == ATM.PIN) {
				System.out.println("Please enter pin: ");
				int number = input.nextInt();
				atm.checkPin(number);
			}
			else if (state == ATM.PINCHECKFAILED) {
				System.out.println("Your Account number and pin does not matched in our system, please try again");
				int number = input.nextInt();
				atm.reset();
			}
			else if (state == ATM.ACCOUNT) {
				System.out.println("1 - balance \n2 - withdraw \n3 - deposit \n4 - exit");
				int number = input.nextInt();
				atm.accountMenuSelection(number);
			}
			
			else if (state == ATM.ACCOUNTEXIT) {
				System.out.println("exit? \n1 - yes \n2 - no");
				int number = input.nextInt();
				atm.exitMenuSelection(number);
			}
			else if (state == ATM.BALANCE) {
				System.out.println("your balance is "+atm.getBalance()+"\nenter any number to go back");
				int number = input.nextInt();
				atm.goBack();
			}
			
			else if (state == ATM.WITHDRAW) {
				System.out.println("your selection: ");
				int selection = input.nextInt();
				atm.withdrawMenuSelection(selection);
			}
			
			else if (state == ATM.WITHDRAWSUCCESS) {
				System.out.println("success");
				String s = input.next();
				atm.goBack();
			}
			
			else if (state == ATM.WITHDRAWFAILED) {
				System.out.println("failed");
				String s = input.next();
				atm.goBack();
			}
			
			else if (state == ATM.DEPOSIT) {
				System.out.println("please deposit");
				int selection = input.nextInt();
				atm.goBack();
			}
		}
	}

}