package biz.gsconsulting.calculator.jmock;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CalculatorView extends JFrame implements IObserveCalculators {
	private static final int OPERATOR_COLUMN = 4;
	private static final int N_DIGIT_COLUMNS = 3;
	private static final int LAST_DIGIT_ROW = 5;
	private static final long serialVersionUID = 1355852206742381681L;
	private final JTextField display;
	private final ICalculatorModel model;
	private final Insets defaultInsets = new Insets(4, 4, 4, 4);

	public CalculatorView(final ICalculatorModel aModel)
	{
		super(Calculator.MAIN_WINDOW);
		model = aModel;
		
		setName(Calculator.MAIN_WINDOW);
		setLayout(new GridBagLayout());
		
		display = createDisplay();
		createDigitButtons();
		
		createOperator("+", Calculator.PLUS,  4);
		createOperator("-", Calculator.MINUS, 3);
		createOperator("x", Calculator.TIMES, 2);
		createOperator("/", Calculator.DIVIDES, 1);
		
		createClear();
		
		JButton clearEntry = createButton("CE", Calculator.CLEAR_ENTRY);
		clearEntry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clearEntry();
			}
		});
		addButtonAt(1, 1, clearEntry);
		
		JButton backspace = createButton("<-", Calculator.BACKSPACE);
		backspace.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.backspace();
			}
		});
		addButtonAt(0, 1, backspace);
		
		pack();
		
		model.subscribe((IObserveCalculators)this);
	}

	private void createClear() {
		JButton clear = createButton("Clr", Calculator.CLEAR);
		clear.addActionListener(new ActionListener() {
			{
				
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.clear();
			}
		});
		addButtonAt(2, 1, clear);
	}

	private void createOperator(String symbol, String name, final int ypos) {
		JButton button = createButton(symbol, name);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.clear();
			}
		});
		addButtonAt(OPERATOR_COLUMN, ypos, button);
	}

	private JButton createButton(String label, String name) {
		JButton button = new JButton(label);
		button.setName(name);
		setFontFor(button);
		return button;
	}

	@SuppressWarnings("serial") //because our subclass just adds an initializer
	private JTextField createDisplay() {
		final JTextField display = new JTextField("0.", 13);
		display.setEditable(false);
		display.setName(Calculator.DISPLAY);
		display.setHorizontalAlignment(JTextField.RIGHT);
		setFontFor(display);
		add(display, new GridBagConstraints() {{
			gridx = 0;
			gridy = 0;
			gridwidth=REMAINDER;
			insets = defaultInsets;
			fill = BOTH;
		}});
		return display;
	}

	private void setFontFor(final JComponent component) {
		component.setFont(component.getFont().deriveFont(32f).deriveFont(Font.BOLD));
	}

	private void createDigitButtons() {
		for (int digit = 0; digit <= 9; ++digit)
		{
			createDigitButton(digit, calculateXPositionFor(digit), calculateYPositionFor(digit));
		}		
	}

	private int calculateYPositionFor(int digit) {
		return LAST_DIGIT_ROW - (digit / N_DIGIT_COLUMNS + ((isEvenlyDivisibleByNColumns(digit))? 0 : 1));
	}

	private boolean isEvenlyDivisibleByNColumns(int digit) {
		return digit % N_DIGIT_COLUMNS == 0;
	}

	private int calculateXPositionFor(int digit) {
		return (digit % N_DIGIT_COLUMNS) - 1;
	}
	
	private void createDigitButton(final int digit, final int xpos, final int ypos) {
		JButton button = createButton(String.valueOf(digit), Calculator.DIGIT_PREFIX + digit);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.collectDigit(digit);
			}
		});
		addButtonAt(xpos, ypos, button);
	}

	@SuppressWarnings("serial")
	private void addButtonAt(final int xpos, final int ypos, JButton button) {
		add(button, new GridBagConstraints() {{
			gridx = xpos;
			gridy = ypos;
			weightx = 1.0;
			fill = BOTH;
			insets = defaultInsets;
		}});
	}

	@Override
	public void digitsUpdated(String newDigits) {
		display.setText(newDigits + ".");
	}
}
