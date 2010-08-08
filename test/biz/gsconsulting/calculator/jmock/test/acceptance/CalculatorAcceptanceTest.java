package biz.gsconsulting.calculator.jmock.test.acceptance;

import org.junit.*;

import biz.gsconsulting.calculator.jmock.*;

public class CalculatorAcceptanceTest {

	protected CalculatorDriver ui;

	public CalculatorAcceptanceTest() {
		super();
	}

	@Before
	public void startApplication() {
		Calculator.main();
		ui = new CalculatorDriver();
	}

	@SuppressWarnings("unused")
	@After
	public void stopApplication() {
		ui.dispose();
	}

}