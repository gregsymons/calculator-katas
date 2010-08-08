package biz.gsconsulting.calculator.jmock;

import javax.swing.*;

public class Calculator {

	public static void main(String... args) {
		CalculatorModel model = new CalculatorModel();
		CalculatorView calculator = new CalculatorView(model);
		calculator.setVisible(true);
		calculator.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static final String MAIN_WINDOW = "Calculator";
	public static final String DISPLAY = "display";
	public static final String DIGIT_PREFIX = "digit_";
	public static final String PLUS = "plus";
	public static final String MINUS = "minus";
	public static final String TIMES = "times";
	public static final String DIVIDES = "divides";
	public static final String CLEAR = "clear";
	public static final String CLEAR_ENTRY = "clear_entry";
	public static final String BACKSPACE = "backspace";

}
