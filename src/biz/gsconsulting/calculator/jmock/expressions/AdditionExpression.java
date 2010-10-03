package biz.gsconsulting.calculator.jmock.expressions;

public class AdditionExpression extends Expression
{
	private Expression leftHandSide;
	private int addend;
	
	AdditionExpression(Expression leftHandSide, int addend)
	{
		this.leftHandSide = leftHandSide;
		this.addend = addend;
	}
	@Override
	public int result() {
		return leftHandSide.result() + addend;
	}
	@Override
	public Expression repeat(int operand) {
		return new AdditionExpression(this, operand);
	}
	@Override
	public int getOperand() {
		// TODO Auto-generated method stub
		return addend;
	}
}
