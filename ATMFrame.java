/*The main Frame of the application
 *Use gridLayout and boxLayout to design the frame
 *add display screen, keypad... to the frame*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// ATMFrame
public class ATMFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private KeyPad keypad;
	private JTextArea display;
	private ATM atm;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 400;
	
	public ATMFrame(ATM atm) {
		this.atm = atm;
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		
		display = new JTextArea();
		display.setEditable(false);
		display.setFont(new Font("Caliri",Font.BOLD,12));
		display.setForeground(new Color(40, 130, 83));
		keypad = new KeyPad(atm, display);
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		
        Dimension expectedDimension = new Dimension(450, 150);
        panel.setPreferredSize(expectedDimension);
        panel.setMaximumSize(expectedDimension);
        panel.setMinimumSize(expectedDimension);
        

        Box box = new Box(BoxLayout.Y_AXIS);

        box.add(Box.createVerticalGlue());
        box.add(panel);     
        box.add(Box.createVerticalGlue());
        
        panel.setLayout(new BorderLayout());
        panel.add(display);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

		northPanel.setBackground(new Color(138, 223, 242));
	    
		northPanel.add(box);
		
		setLayout(new GridLayout(2,1));
		add(northPanel);
		add(keypad);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		keypad.printState();
		
	}
	

}