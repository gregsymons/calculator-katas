package biz.gsconsulting.calculator.jmock.test.acceptance;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DigitButtonTests extends CalculatorAcceptanceTest { 
	@Parameters
	public static List<Object[]> examples()
	{
		return Arrays.asList(new Object[][] {
			{ 1 },
			{ 2 },
			{ 3 },
			{ 4 },
			{ 5 },
			{ 6 },
			{ 7 },
			{ 8 },
			{ 9 },
			{ 0 },
		});
	}
	private String expectedDisplay;
	private int digit;
	
	public DigitButtonTests(int digit)
	{
		this.digit = digit;
		this.expectedDisplay = String.valueOf(digit) + ".";
	}
	
	@Test
	public void shouldDisplayTheDigitThatIsPressed() {
		ui.pressDigit(digit);
		ui.displays(expectedDisplay);
	}
}
