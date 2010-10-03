package biz.gsconsulting.calculator.jmock;

public interface ICalculatorModel {
	void subscribe(IObserveCalculators o);
	void collectDigit(int digit);
	void clear();
	void clearEntry();
	void backspace();
	void operator(String name);
}
