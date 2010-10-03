package biz.gsconsulting.calculator.jmock.test.acceptance;

import static org.junit.Assert.*;

import org.junit.*;

public class DataEntryTests extends CalculatorAcceptanceTest {

	@Test
	public void shouldInitiallyDisplay0()
	{
		ui.displays("0");
	}

	@Test 
	public void shouldCollectDigits()
	{
		ui.pressDigit(1);
		ui.pressDigit(1);
		ui.displays("11");
	}
	
	@Test
	public void shouldClearWhenPlusIsPressed()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.plus();
		ui.displays("0");
	}
	
	@Test 
	public void shouldClearWhenMinusIsPressed()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.minus();
		ui.displays("0");
	}
	
	@Test
	public void shouldClearWhenTimesIsPressed()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.times();
		ui.displays("0");
	}
	
	@Test
	public void shouldClearWhenDividesIsPressed()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.divides();
		ui.displays("0");
	}
	
	@Test
	public void shouldClearWhenClearIsPressed()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.clear();
		ui.displays("0");
	}
	
	@Test
	public void shouldClearWhenClearEntryIsPressed()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.clearEntry();
		ui.displays("0");
	}
	
	@Test
	public void shouldRemoveTheMostRecentDigitWhenBackspaceIsPressed()
	{
		ui.pressDigit(1);
		ui.pressDigit(2);
		ui.displays("12");
		ui.backspace();
		ui.displays("1");
	}
	
	@Test
	public void shouldDisplayZeroIfTheLastDigitIsBackspaced()
	{
		ui.pressDigit(1);
		ui.displays("1");
		ui.backspace();
		ui.displays("0");
	}
	
	@Test
	public void pressingADigitAfterGettingAResultShouldDisplayTheDigit() throws Exception {
		ui.pressDigit(1);
		ui.plus();
		ui.pressDigit(1);
		ui.enter();
		ui.pressDigit(1);
		
		ui.displays("1");
	}
	
	@Test
	public void pressingADigitAfterGettingAResultShouldStartANewEntry() throws Exception {
		pressingADigitAfterGettingAResultShouldDisplayTheDigit();
		ui.plus();
		ui.displays("0");
	}
	
	@Test
	public void repeatedlyPressingEnterAfterAnOperationRepeatsTheOperationWithTheSecondOperand() throws Exception {
		ui.pressDigit(1);
		ui.plus();
		ui.pressDigit(2);
		ui.enter();
		ui.displays("3");
		ui.enter();
		ui.displays("5");
		ui.enter();
		ui.displays("7");
	}
}
