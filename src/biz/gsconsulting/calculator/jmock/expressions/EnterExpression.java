package biz.gsconsulting.calculator.jmock.expressions;

public class EnterExpression extends Expression {
	private Expression finalExpression;
	private int finalOperand;
	
	public EnterExpression(Expression expression, int operand) {
		if (expression.isTerminal())
		{
			finalExpression = expression.repeat(expression.getOperand());
			finalOperand = expression.getOperand();
		}
		else
		{
			finalExpression = expression.repeat(operand);
			finalOperand = operand;
		}
	}

	@Override
	public int result() {
		return finalExpression.result();
	}

	@Override
	public Expression repeat(int operand) {
		return finalExpression.repeat(finalOperand);
	}
	
	@Override
	public boolean isTerminal()
	{
		return true;
	}

	@Override
	public int getOperand() {
		return finalOperand;
	}
}