package biz.gsconsulting.calculator.jmock;

import biz.gsconsulting.calculator.jmock.expressions.*;

public class CalculatorModel implements ICalculatorModel {
	IObserveCalculators observer;
	final StringBuffer digits = new StringBuffer("0");
	Expression currentExpression = NullExpression.Instance();

	@Override
	public void collectDigit(int digit) {
		if (currentExpression.isTerminal())
		{
			currentExpression = NullExpression.Instance();
			resetDigits(digit);
		}
		else if (digitsValue() == 0) {
			resetDigits(digit);
		} else {
			appendDigit(digit);
		}
	}

	@Override
	public void subscribe(IObserveCalculators o) {
		observer = o;
		digitsUpdated();
	}

	@Override
	public void clear() {
		resetDigits(0);
	}

	@Override
	public void clearEntry() {
		clear();
	}

	@Override
	public void backspace() {
		if (digits.length() > 1) {
			removeLastDigit();
		} else {
			resetDigits(0);
		}
	}

	@Override
	public void operator(String name) {
		currentExpression = currentExpression.operator(name, digitsValue());
		if (currentExpression.isTerminal())
		{
			resetDigits(currentExpression.result());
		}
		else
		{
			clear();
		}
	}

	private int digitsValue() {
		return Integer.parseInt(digits.toString());
	}

	private void appendDigit(int digit) {
		digits.append(Integer.toString(digit));
		digitsUpdated();
	}

	private void resetDigits(int newValue) {
		digits.replace(0, digits.length(), Integer.toString(newValue));
		digitsUpdated();
	}
	
	private void removeLastDigit() {
		digits.setLength(digits.length() - 1);
		digitsUpdated();
	}

	private void digitsUpdated() {
		if (observer != null)
			observer.digitsUpdated(digits.toString());
	}
}
