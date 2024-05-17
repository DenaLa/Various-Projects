//tutorial from Winston Lievsay on youtube

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TimerProgram extends JFrame {
	
	JLabel promptLabel, timerLabel;
	int counter;
	JTextField tf;
	JButton button;
	Timer timer;
	
	public TimerProgram() {
		setLayout(new GridLayout(2,2,5,5));
		
		promptLabel = new JLabel("Enter Seconds: ", SwingConstants.CENTER);
		add(promptLabel);
		
		tf = new JTextField(5);
		add(tf);
		
		button = new JButton("Start Timer");
		add(button);
		
		timerLabel = new JLabel("Waiting...", SwingConstants.CENTER);
		add(timerLabel);
		
		
		ButtonEvent event = new ButtonEvent();
		button.addActionListener(event);
		
		
		
	}
	
	public class ButtonEvent implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int count = (int)(Double.parseDouble(tf.getText()));
			timerLabel.setText("Time left " + count);
			
			TimeClass tc = new TimeClass(count);
			timer = new Timer(1000, tc);
			timer.start();
		}
		
		

		
	}
	
	
	public class TimeClass implements ActionListener{
		int counter;
		
		public TimeClass(int counter) {
			this.counter = counter;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			counter--;
			
			if(counter >= 1) {
				timerLabel.setText("Time left: " + counter);
			}
			else {
				timer.stop();
				timerLabel.setText("Time's Up!");
				Toolkit.getDefaultToolkit().beep();
			}
			
		}
		 
		
	}
	
	
	public static void main(String args[]) {
		TimerProgram gui = new TimerProgram();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(250, 100);
		gui.setTitle("Timer");
		gui.setVisible(true);
	}
	
}//whole class


