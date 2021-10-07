/*ATMGui simulator application
 *Program entrence*/

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Main class
public class Main {
	public static void main(String [] args) {
		ATM atm;
		try {
			Bank theBank = new Bank();
			theBank.readAccount("accounts.txt");
			atm = new ATM(theBank);
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error opening accounts file.");
			return;
		}
		
		// show ATMFrame
		JFrame frame = new ATMFrame(atm);
		frame.setTitle("ATM Simulator GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
