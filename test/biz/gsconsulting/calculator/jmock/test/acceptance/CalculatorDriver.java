package biz.gsconsulting.calculator.jmock.test.acceptance;

import javax.swing.*;

import biz.gsconsulting.calculator.jmock.*;

import com.objogate.wl.swing.*;
import com.objogate.wl.swing.gesture.*;
import com.objogate.wl.swing.driver.*;

public class CalculatorDriver extends JFrameDriver {
	@SuppressWarnings("unchecked")
	public CalculatorDriver() {
		super(new GesturePerformer(), new AWTEventQueueProber(), 
				named(Calculator.MAIN_WINDOW),
				showingOnScreen());
	}

	public void displays(final String digits) {
		@SuppressWarnings("unchecked")
		JTextFieldDriver display = new JTextFieldDriver(this, JTextField.class, named(Calculator.DISPLAY));
		String actualDigits;
		if (!digits.contains("."))
		{
			actualDigits = digits + ".";
		}
		else
		{
			actualDigits = digits;
		}
		display.hasText(actualDigits);
	}

	public void pressDigit(int digit) {
		clickButtonNamed(Calculator.DIGIT_PREFIX + String.valueOf(digit));		
	}

	public void plus() {
		clickButtonNamed(Calculator.PLUS);
	}

	public void minus() {
		clickButtonNamed(Calculator.MINUS);
	}

	public void times() {
		clickButtonNamed(Calculator.TIMES);
	}

	public void divides() {
		clickButtonNamed(Calculator.DIVIDES);
	}

	public void clear() {
		clickButtonNamed(Calculator.CLEAR);
	}

	public void clearEntry() {
		clickButtonNamed(Calculator.CLEAR_ENTRY);
	}

	public void backspace() {
		clickButtonNamed(Calculator.BACKSPACE);
	}

	public void enter() {
		clickButtonNamed(Calculator.ENTER);
	}
	
	@SuppressWarnings("unchecked")
	private JButtonDriver findButtonNamed(String name) {
		return new JButtonDriver(this, JButton.class, named(name));
	}
	
	private void clickButtonNamed(String name)	{
		JButtonDriver button = findButtonNamed(name);
		button.click();
	}
}
