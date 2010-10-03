package biz.gsconsulting.calculator.jmock.expressions;

import biz.gsconsulting.calculator.jmock.*;
import biz.gsconsulting.calculator.jmock.*;

public abstract class Expression
{
	public abstract int result();
	public abstract Expression repeat(int operand);
	public abstract int getOperand();

	public Expression operator(String name, int operand) {
		if (name.equals(Calculator.PLUS)) return new AdditionExpression(this, operand);
		if (name.equals(Calculator.MINUS)) return new SubtractionExpression(this, operand);
		if (name.equals(Calculator.TIMES)) return new MulitiplicationExpression(this, operand);
		if (name.equals(Calculator.DIVIDES)) return new DivisionExpression(this, operand);
		if (name.equals(Calculator.ENTER)) return new EnterExpression(this, operand);
		
		throw new CalculatorError("Unsupported operation '" + name + "' in " + this.getClass().toString());
	}
	
	public boolean isTerminal() {
		return false;
	}
}
