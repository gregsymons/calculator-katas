package biz.gsconsulting.calculator.jmock.test.acceptance;

import org.junit.*;

public class AdditionTests extends CalculatorAcceptanceTest {	
	@Test
	public void onePlusOneIsTwo() throws Exception {
		ui.pressDigit(1);
		ui.plus();
		ui.pressDigit(1);
		ui.enter();
		
		ui.displays("2");
	}
	
	@Test
	public void onePlusTwoIsThree() throws Exception {
		ui.pressDigit(1);
		ui.plus();
		ui.pressDigit(2);
		ui.enter();
		
		ui.displays("3");
	}
}
