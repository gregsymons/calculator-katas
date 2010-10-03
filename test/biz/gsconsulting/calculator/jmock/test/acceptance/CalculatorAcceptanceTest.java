package biz.gsconsulting.calculator.jmock.test.acceptance;

import org.junit.*;

import biz.gsconsulting.calculator.jmock.*;

public class CalculatorAcceptanceTest {

	protected CalculatorDriver ui;

	@Before
	public void startApplication() {
		Calculator.main();
		ui = new CalculatorDriver();
	}

	@After
	public void stopApplication() {
		ui.dispose();
	}

}