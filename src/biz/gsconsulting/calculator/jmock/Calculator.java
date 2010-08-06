package biz.gsconsulting.calculator.jmock;

import javax.swing.*;

public class Calculator {

	public static void main(String... args) {
		CalculatorView calculator = new CalculatorView();
		calculator.setVisible(true);
		calculator.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static final String MAIN_WINDOW = "Calculator";
	public static final String DISPLAY = "display";

}
