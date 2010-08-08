package biz.gsconsulting.calculator.jmock;

import java.util.*;

public interface ICalculatorModel {
	void subscribe(IObserveCalculators o);
	void collectDigit(int digit);
	void clear();
	void clearEntry();
	void backspace();
}
