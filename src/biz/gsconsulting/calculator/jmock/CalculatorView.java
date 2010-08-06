package biz.gsconsulting.calculator.jmock;

import javax.swing.*;

public class CalculatorView extends JFrame {
	public CalculatorView()
	{
		super(Calculator.MAIN_WINDOW);
		setName(Calculator.MAIN_WINDOW);
		setSize(400, 400);
		JTextField display = new JTextField("0.");
		display.setName(Calculator.DISPLAY);
		add(display);
	}
}
