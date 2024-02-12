import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	//Physical elements of the calculator
	JButton addButton, subtractButton, multiplyButton, divideButton, decimalButton, clearButton, deleteButton, equalsButton;
	JButton numberButton[] = new JButton[11];
	JTextField output = new JTextField(16);
	
	//Invisible elements of the calculator
	String current = "", previous = "", operator;
	
	public Calculator(){
		
		//name of the window
		
		//The main panel
		JPanel mainPanel = new JPanel();
		
		
		//Action Listeners to handle the buttons
		NumberButtonHandler forNumbers = new NumberButtonHandler();
		OperatorButtonHandler forOperators = new OperatorButtonHandler();
		OtherButtonHandler forOthers = new OtherButtonHandler();
		
		//The rows for the calculator
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		
		
		//Initialize the Number Buttons, including creating them, adding listeners, and styling them
		decimalButton = new JButton(".");
		decimalButton.addActionListener(forNumbers);
		decimalButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		numberButton[10] = decimalButton;
		for(int i = 0; i<numberButton.length-1;i++) {
			numberButton[i] = new JButton(String.valueOf(i));
			numberButton[i].addActionListener(forNumbers);
			numberButton[i].setFont(new Font("Monospaced", Font.BOLD, 22));
		}
		
		//dealing with the buttons
		addButton = new JButton("+");
		addButton.addActionListener(forOperators);
		addButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		subtractButton = new JButton("-");
		subtractButton.addActionListener(forOperators);
		subtractButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		
		multiplyButton = new JButton("*");
		multiplyButton.addActionListener(forOperators);
		multiplyButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		divideButton = new JButton("/");
		divideButton.addActionListener(forOperators);
		divideButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		clearButton = new JButton("C");
		clearButton.addActionListener(forOthers);
		clearButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		deleteButton = new JButton("D");
		deleteButton.addActionListener(forOthers);
		deleteButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		equalsButton = new JButton("=");
		equalsButton.addActionListener(forOthers);
		equalsButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		
		//Set up the output TextField
		output.setEditable(false);
		output.setMaximumSize(new Dimension(185,40));
		output.setFont(new Font("Monospaced", Font.BOLD, 22));
		output.setDisabledTextColor(new Color(0,0,0));
		output.setMargin(new Insets(0,5,0,0));
		output.setText("0");
		
		//Set the layout of the row panes
		row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS ));
		row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS ));
		row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS ));
		row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS ));
		row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS ));
		
		//Add components to each row
		row1.add(Box.createHorizontalGlue());
		row1.add(clearButton);
		row1.add(deleteButton);
		
		row2.add(numberButton[7]);
		row2.add(numberButton[8]);
		row2.add(numberButton[9]);
		row2.add(divideButton);
		
		row3.add(numberButton[4]);
		row3.add(numberButton[5]);
		row3.add(numberButton[6]);
		row3.add(multiplyButton);
		
		row4.add(numberButton[1]);
		row4.add(numberButton[2]);
		row4.add(numberButton[3]);
		row4.add(subtractButton);
		
		row5.add(numberButton[10]);
		row5.add(numberButton[0]);
		row5.add(equalsButton);
		row5.add(addButton);
		
		//set up main panel
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(output);
		mainPanel.add(row1);
		mainPanel.add(row2);
		mainPanel.add(row3);
		mainPanel.add(row4);
		mainPanel.add(row5);
		
		//final touches
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200,280);
		this.setVisible(true);
		
		
	}//Constructor
	
	
	public void delete() {
		if(current.length() > 0) {
			current = current.substring(0,current.length()-1);
		}
	}//delete. backspace
	
	public void clear() {
		current = "";
		previous = "";
		operator = null;
	}//clear
	
	public void updateOutput() {
		output.setText(current);
	}//updates output
	
	public void appendToCurrent(String num) {
		if(current.contains(".") && num.equals(".")) {
			return;
		}
		current += num;
	}

	public void selectOperator(String newOperator) {
		if(current.isEmpty()) {
			operator = newOperator;
			return;
		}
		if(!previous.isEmpty()) {
			calculate();
		}

		operator = newOperator;
		previous = current;
		current = "";
	}
	
	
	public void calculate() {
		if(previous.length() < 1|| current.length() < 1) {
			return;
		}
		
		double ans = 0.0;
		double a = Double.parseDouble(previous);
		double b = Double.parseDouble(current);
		
		switch(operator) {
			case "+":
				ans = a + b;
				break;
			case "-":
				ans = a - b;
				break;
			case "*":
				ans = a * b;
				break;
			case "/":
				ans = a/b;
				break;
			default:
				break;
		}
		
		current = String.valueOf(ans);
		previous = "";
		operator = null;
		processOutput();
		
	}
	
	public void processOutput() {
		if(current.length() > 0) {
			String intPart = current.split("\\.")[0];
			String decPart = current.split("\\.")[1];
			if(decPart.equals("0")) {
				current = intPart;
			}
		} 
	}
	
	private class NumberButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedButton = (JButton) e.getSource();
			
			for(JButton button : numberButton) {
				if(selectedButton == button) {
					appendToCurrent(button.getText());
					updateOutput();
				}
			}
			
		}
		
	}//handles numbers
	
	
	private class OperatorButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedButton = (JButton) e.getSource();
			
			if(selectedButton == addButton) {
				selectOperator(addButton.getText());
			}
			else if(selectedButton == subtractButton) {
				selectOperator(subtractButton.getText());
			}
			else if(selectedButton == multiplyButton) {
				selectOperator(multiplyButton.getText());
			}
			else if(selectedButton == divideButton) {
				selectOperator(divideButton.getText());
			}
			
			updateOutput();
		}
		
	}//handles operations
	
	private class OtherButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selectedButton = (JButton) e.getSource();
			
			if(selectedButton == clearButton) {
				clear();
			}
			else if(selectedButton == deleteButton) {
				delete();
			}
			else if(selectedButton == equalsButton) {
				calculate();
			}
			
			updateOutput();
		}
		
	}//handles the rest
	
}//calculator


