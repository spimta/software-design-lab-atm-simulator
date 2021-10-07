/* Create keypad and two cash button
 * realize all the button action handler
 * And make changes to the display screen*/
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// create keypad and two cash button
public class KeyPad extends JPanel {
	private static final long serialVersionUID = 1L;
	private ATM atm;
	private JPanel buttonPanel;
	private JPanel cashPanel;
	private JPanel cashBoxPanel;
	private JTextArea display;
	private JButton clearButton;
	private JButton enterButton;
	private JButton depositSlot;
	private JButton cashDispenser;
	String tmp;
	
	// create keypad and 2 cash button
	public KeyPad(ATM atm, JTextArea display) {
		this.atm = atm;
		this.display = display;
		setBackground(new Color(138, 223, 242));
		tmp = "";
		
		setLayout(new GridLayout(1,2));
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,3, 5, 5));
		buttonPanel.setBackground(new Color(138, 223, 242));
		
		// create keypad
		addDigitalButton("7");
		addDigitalButton("8");
		addDigitalButton("9");
		addDigitalButton("4");
		addDigitalButton("5");
		addDigitalButton("6");
		addDigitalButton("1");
		addDigitalButton("2");
		addDigitalButton("3");
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ClearButtonHandler());
		buttonPanel.add(clearButton);
		addDigitalButton("0");
		enterButton = new JButton("ENTER");
		enterButton.addActionListener(new EnterButtonHandler());
		buttonPanel.add(enterButton);
		
		// deposit and withdraw cash button
		cashDispenser = new JButton("take cash here");
		cashDispenser.addActionListener(new cashDispenserHandler());
		depositSlot = new JButton("Put deposit envelope here");
		depositSlot.addActionListener(new dipositSlotHandler());
		
		cashBoxPanel = new JPanel();
		cashPanel = new JPanel();
		
		Dimension expectedDimension = new Dimension(200, 100);
		cashBoxPanel.setPreferredSize(expectedDimension);
		cashBoxPanel.setMaximumSize(expectedDimension);
		cashBoxPanel.setMinimumSize(expectedDimension);
		cashBoxPanel.setLayout(new GridLayout(2,1,50,40));
		cashBoxPanel.setBackground(new Color(138, 223, 242));
		
		cashBoxPanel.add(cashDispenser);
		cashBoxPanel.add(depositSlot);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(Box.createVerticalGlue());
        box.add(cashBoxPanel);     
        box.add(Box.createVerticalGlue());
		
		
		cashPanel.setBackground(new Color(138, 223, 242));
		cashPanel.setLayout(new GridLayout());
		cashPanel.add(box);
		cashPanel.add(box);
		
		add(buttonPanel);
		add(cashPanel);
		

	}
	
	// add digital button
	private void addDigitalButton(final String label) {
		
		JButton button = new JButton(label);
		buttonPanel.add(button);
		DigitButtonHandler handler = new DigitButtonHandler();
		button.addActionListener(handler);
		
	}
	
	// Digit button handler
	private class DigitButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			tmp = tmp+b.getText();
			printState();
		}
	}
	
	// Clear button handler
	private class ClearButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			clearStringbuffer();
			printState();
		}
		
	}
	
	public int getInput() {
		if (tmp == "") return -1;
		return Integer.parseInt(tmp);
	}
	
	public void clearStringbuffer() {
		tmp = "";
	}
	
	// Enter button handler
	private class EnterButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int state = atm.getState();
			if (state == ATM.START) {
				atm.setAccountNumber(getInput());
				atm.setState(ATM.PIN);
			}
			
			else if (state == ATM.PIN) {
				atm.checkPin(getInput());
			}
			
			else if (state == ATM.PINCHECKFAILED) {
				atm.setState(ATM.START);
			}
			
			else if (state == ATM.ACCOUNT) {
				int num = getInput();
				atm.accountMenuSelection(num);
			}
			
			else if (state == ATM.ACCOUNTEXIT) {
				atm.reset();
			}
			
			else if (state == ATM.BALANCE) {
				atm.setState(ATM.ACCOUNT);
			}
			
			else if (state == ATM.WITHDRAW) {
				int num = getInput();
				atm.withdrawMenuSelection(num);
			}
			
			else if (state == ATM.WITHDRAWFAILED) {
				atm.setState(ATM.ACCOUNT);
			}
			
			else if (state == ATM.DEPOSITSUCCESS) {
				atm.setState(ATM.ACCOUNT);
			}
			
			else if (state == ATM.DEPOSITFAILED) {
				atm.setState(ATM.ACCOUNT);
			}
			
			clearStringbuffer();
			printState();
			
		}

	}
	
	// dipositSlot handler
	private class dipositSlotHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int state = atm.getState();
			if (state == ATM.DEPOSIT) {
				String input = JOptionPane.showInputDialog("put your cash");
				int depAmount = Integer.parseInt(input);
				atm.depositAmount(depAmount);
			}
			
			printState();
			
		}
		
	}
	
	// cashDispenserHandler
	private class cashDispenserHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int state = atm.getState();
			if (state == ATM.WITHDRAWSUCCESS) {
				JOptionPane.showMessageDialog(null, "Cash taken", "cash dispenser", JOptionPane.PLAIN_MESSAGE);
				atm.setState(ATM.ACCOUNT);
			}
			
			printState();
		}
		
	}

	// print each state prompt to the display screen
	public void printState() {
		int state = atm.getState();
		if (state == ATM.PINCHECKFAILED) {
			display.append("\n\n\tIncorrect pin, press enter to Continue");
		}
		else {
			if (state == ATM.PIN) {
				display.setText("Atomated teller machine user interface \nBy Yi Lin, \n\nWelcome, \n\t- Please enter yor account number: "+atm.getAccountNumber()+"\n\t- Please enter your pin: ");
			}
			else if (state == ATM.START) {
				display.setText("Atomated teller machine user interface \nBy Yi Lin, \n\nWelcome, \n\t- Please enter yor account number: ");
			}
			else if (state == ATM.ACCOUNT) {
				display.setText("Menu \n\t1 - Balance \n\t2 - Withdraw \n\t3 - Deposit \n\t4 - Exit \nYour Choice: ");
			}
			else if (state == ATM.ACCOUNTEXIT) {
				display.setText("\n\n\tAccount exit. \n\nPress enter to continue. ");
			}
			else if (state == ATM.BALANCE) {
				display.setText("\n\n\tYour current account balance is "+atm.getBalance());
			}
			else if (state == ATM.DEPOSIT) {
				display.setText("\n\n\tPlease put your cash or envelope in the diposit slot");
			}
			else if (state == ATM.DEPOSITSUCCESS) {
				display.setText("\n\n\tDeposit successfully, \n\nPress enter to continue. ");
			}
			else if (state == ATM.DEPOSITFAILED) {
				display.setText("\n\n\tDeposit failed, \n\nPress enter to continue. ");
			}
			else if (state == ATM.WITHDRAW) {
				display.setText("Withdraw Menu: \n\n\t1 - $20\t4 - $100\n\t2 - $40\t5 - $200\n\t3 - $60 \t6 - Cancel transaction\n\nYour Choice: ");
			}
			else if (state == ATM.WITHDRAWSUCCESS) {
				display.setText("withraw successfully \n\nPlease ake your cash from the cash dispenser to continue");
			}
			else if (state == ATM.WITHDRAWFAILED) {
				display.setText("withdraw failed, \nYou don't have enough balance in your account to make this transaction. \n\nPress enter to continue");
			}
		}
		display.setText(display.getText()+tmp);
		
		if (state == ATM.ACCOUNT) {
			display.setText(display.getText()+"\n\nName: Yi Lin\tEmplid: 23732353");
		}
		
	}

}
