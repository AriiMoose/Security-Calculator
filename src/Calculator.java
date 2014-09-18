import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener
{
	public static void main(String[] args)
	{
		// New instance of the calculator
		Calculator c = new Calculator();
	}
	
	// Set up five rows of components
	JPanel[] row = new JPanel[5];
	
	// Set up number of buttons
	JButton[] button = new JButton[19];
	
	// Array of strings to denote buttons
	String[] buttonString = {"7", "8", "9", "+",
	                         "4", "5", "6", "-",
	                         "1", "2", "3", "*",
	                         ".", "/", "C", "SQRT",
	                         "+/-", "=", "0"};
	
	// Arrays for widths and heights of buttons
	int[] dimW = {300, 45, 100, 90};
	int[] dimH = {35, 40};
	
	// Declare & initialise dimensions
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]);
	
	// Array for addition, subtraction, multiplication and division booleans
	boolean[] function = new boolean[4];
	
	// Temporary doubles for calculations
	double[] temporary = {0, 0};
	
	// Create display
	JTextArea display = new JTextArea(1, 20);
	
	// Set font
	Font font = new Font("Times New Roman", Font.BOLD, 14);
	
	// Calculator Constructor
	Calculator()
	{
		super("Secure Calculator");
		setDesign();
		setSize(380, 250);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		GridLayout grid = new GridLayout(5, 5);
		setLayout(grid);
		
		// Initialise booleans
		for(int i = 0; i < 4; i++)
			function[i] = false;
		
		// Layout for first row
		FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
		
		// Layout for the rest of the rows
		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
		
		// Initialise JPanel rows
		for(int i = 0; i < 5; i++)
			row[i] = new JPanel();
		
		// Assign layouts to rows
		row[0].setLayout(f1);
		
		for(int i = 0; i < 5; i++)
			row[i].setLayout(f2);
		
		// Initialise buttons
		for(int i = 0; i < 19; i++)
		{
			button[i] = new JButton();
			button[i].setText(buttonString[i]);
			button[i].setFont(font);
			button[i].addActionListener(this);
		}
		
		// Set up display
		display.setFont(font);
		display.setEditable(false);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setPreferredSize(displayDimension);
		
		// Set size for regular buttons
		for(int i = 0; i < 14; i++)
			button[i].setPreferredSize(regularDimension);
		
		// Set size for right column buttons
		for(int i = 14; i < 18; i++)
			button[i].setPreferredSize(rColumnDimension);
		
		// Set size for '0' button
		button[18].setPreferredSize(zeroButDimension);
		
		// Add components to the panel, and add panels to the frame
		// Add the display to row1, and add row1 to the frame
		row[0].add(display);
		add(row[0]);
		
		// Add components to remaining rows and add them to the frame
		for(int i = 0; i < 4; i++)
			row[1].add(button[i]);
		row[1].add(button[14]);
		add(row[1]);
		
		for(int i = 4; i < 8; i++)
			row[2].add(button[i]);
		row[2].add(button[15]);
		add(row[2]);
		
		for(int i = 8; i < 12; i++)
			row[3].add(button[i]);
		row[3].add(button[16]);
		add(row[3]);
		
		row[4].add(button[18]);
		for(int i = 12; i < 14; i++)
			row[4].add(button[i]);
		row[4].add(button[17]);
		add(row[4]);
		
		setVisible(true);
	}
	
	public final void setDesign()
	{
		try
		{
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		
		catch(Exception e)
		{
		}
	}
	
	// Set up button functionality
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == button[0])
			display.append("7");
		if(ae.getSource() == button[1])
			display.append("8");
		if(ae.getSource() == button[2])
			display.append("9");
		if(ae.getSource() == button[3])
		{
			// Add function[0]
			temporary[0] = Double.parseDouble(display.getText());
			function[0] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[4])
			display.append("4");
		if(ae.getSource() == button[5])
			display.append("5");
		if(ae.getSource() == button[6])
			display.append("6");
		if(ae.getSource() == button[7])
		{
			// Subtract function[1]
			temporary[0] = Double.parseDouble(display.getText());
			function[1] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[8])
			display.append("1");
		if(ae.getSource() == button[9])
			display.append("2");
		if(ae.getSource() == button[10])
			display.append("3");
		if(ae.getSource() == button[11])
		{
			// Multiply function[2]
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[12])
			display.append(".");
		if(ae.getSource() == button[13])
		{
			// Divide function [3]
			temporary[0] = Double.parseDouble(display.getText());
			function[2] = true;
			display.setText("");
		}
		
		if(ae.getSource() == button[14])
			clear();
		if(ae.getSource() == button[15])
			getSqrt();
		if(ae.getSource() == button[16])
			getPosNeg();
		if(ae.getSource() == button[17])
			getResult();
		if(ae.getSource() == button[18])
			display.append("0");
	}
	
	// Calculator functions
	
	public void clear()
	{
		try
		{
			display.setText(""); // Sets blank display text
			for(int i = 0; i < 4; i++)
				function[i] = false; // Resets functions to false
			for(int i = 0; i < 2; i++)
				temporary[i] = 0; // Resets temporary values to 0
		}
		catch (NullPointerException e)
		{
		}
	}
	
	public void getSqrt()
	{
		try
		{
			// Create variable for value and get it's square root
			double value = Math.sqrt(Double.parseDouble(display.getText())); 
			display.setText(Double.toString(value)); // Sets display to new value
		}
		catch(NumberFormatException e)
		{
		}
	}
	
	public void getPosNeg()
	{
		try
		{
			// Create variable for current value
			double value = Double.parseDouble(display.getText());
			if(value != 0 )
			{
				value = value * (-1); // Multiply by -1 to get opposite value
				display.setText(Double.toString(value)); // Set display to new value
			}
			
			else
			{
			}
		}
		
		catch (NumberFormatException e)
		{
		}
	}
	
	public void getResult()
	{
		double result = 0; // Variable for result
		
		// Second temp number from display
		temporary[1] = Double.parseDouble(display.getText());
		String temp0 = Double.toString(temporary[0]); // Used for text of first temp
		String temp1 = Double.toString(temporary[1]); // Used for text of second temp
		
		try
		{
			if(temp0.contains("-"))
			{
				String[] temp00 = temp0.split("-", 2); // Split string in two at '-'
				temporary[0] = (Double.parseDouble(temp00[1]) * -1); // Put string back in double with real value
			}
			
			if(temp1.contains("-"))
			{
				String[] temp11 = temp1.split("-", 2);
				temporary[1] = (Double.parseDouble(temp11[1]) * -1);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
		}
		
		try
		{
			if(function[2] == true) // Start with multiplication
				result = temporary[0] * temporary[1];
			
			else if(function[3] == true) // Division
				result = temporary[0] / temporary[1];
				
			else if(function[0] == true) // Addition
				result = temporary[0] + temporary[1];
				
			else if(function[1] == true) // Division
				result = temporary[0] - temporary[1];
			
			display.setText(Double.toString(result)); // Set displa to result
			
			for(int i = 0; i < 4; i++)
				function[i] = false; // Reset all functions back to false
		}
		
		catch(NumberFormatException e)
		{
		}
	}
}
