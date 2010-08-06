package biz.gsconsulting.calculator.jmock.test.acceptance;

import org.junit.*;
import static org.junit.Assert.*;

import com.objogate.wl.swing.driver.*;

import biz.gsconsulting.calculator.jmock.*;


public class CalculatorAcceptanceTests {
	@Test
	public void shouldInitiallyDisplay0()
	{
		Calculator.main();
		CalculatorDriver ui = new CalculatorDriver();
		ui.displays("0.");
	}
}
