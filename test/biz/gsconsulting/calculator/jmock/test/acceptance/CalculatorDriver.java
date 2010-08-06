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

	public void displays(String digits) {
		JTextFieldDriver display = new JTextFieldDriver(this, JTextField.class, ComponentDriver.named(Calculator.DISPLAY));
		display.hasText(digits);
	}
}
