package MyHelloWorld;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SwingApplication {
	private static String labelPrefix = "Number of button clicks:";
	private int numClicks = 0;
	public Component creatComponents(){
		final JLabel label = new JLabel(labelPrefix + "0");
		JButton button = new JButton("I'm a Swing button!");
		button.setMnemonic(KeyEvent.VK_Y);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				numClicks++;
				label.setText(labelPrefix + numClicks);
			}
		});
		
		label.setLabelFor(button);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		return panel;
	}
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch(Exception e){}
		
		JFrame frame = new JFrame("SwingApplication");
		SwingApplication application = new SwingApplication();
		Component contents = application.creatComponents();
		frame.getContentPane().add(contents,BorderLayout.CENTER);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
