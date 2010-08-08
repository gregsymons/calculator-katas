package biz.gsconsulting.calculator.jmock;

public class CalculatorModel implements ICalculatorModel {
	
	IObserveCalculators observer;
	final StringBuffer digits = new StringBuffer("0");
	
	@Override
	public void collectDigit(int digit) {
		String digitAsString = Integer.toString(digit);
		if (digits.toString().compareTo("0") == 0)
		{
			digits.replace(0, 1, digitAsString);
		}
		else
		{
			digits.append(digitAsString);
		}
			
		digitsUpdated();
	}
	
	@Override
	public void subscribe(IObserveCalculators o) {
		observer = o;
		digitsUpdated();
	}

	@Override
	public void clear() {
		digits.replace(0, digits.length(), "0");
		digitsUpdated();
	}

	private void digitsUpdated() {
		if (observer != null) observer.digitsUpdated(digits.toString());
	}

	@Override
	public void clearEntry() {
		clear();
	}

	@Override
	public void backspace() {
		if (digits.length() > 1) 
		{
			digits.setLength(digits.length() - 1);
		}
		else
		{
			digits.replace(0, digits.length(), "0");
		}
		
		digitsUpdated();
	}
}
