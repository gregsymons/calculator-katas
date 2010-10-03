package biz.gsconsulting.calculator.jmock.expressions;

public class NullExpression extends Expression
{
	public static NullExpression Instance()
	{
		return instance;
	}
	@Override
	public int result() {
		return 0;
	}

	@Override
	public Expression repeat(int operand) {
		return this;
	}
	
	private static NullExpression instance = new NullExpression();
	private NullExpression()
	{
		
	}
	@Override
	public int getOperand() {
		return 0;
	}
}
