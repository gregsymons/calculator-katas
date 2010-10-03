package biz.gsconsulting.calculator.jmock.test.unit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.api.*;
import org.jmock.integration.junit4.*;
import org.jmock.internal.*;
import org.junit.*;
import org.junit.runner.*;

import biz.gsconsulting.calculator.jmock.*;
import biz.gsconsulting.calculator.jmock.expressions.*;


@RunWith(JMock.class)
public class CalculatorModelTests {
	Mockery context = new JUnit4Mockery();
	private CalculatorModel model = new CalculatorModel();
	private IObserveCalculators observer = context.mock(IObserveCalculators.class);
	
	@Test
	public void collectDigitShouldNotifyObserversOfTheNewDigits()
	{
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("1"); 
			allowing(observer).digitsUpdated(with(any(String.class)));
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
	}
	
	@Test
	public void collectDigitsShouldNotComplainIfThereAreNoObservers()
	{
		Exception caught = null;
		try {
			model.collectDigit(1);
		} catch (Exception e) {
			caught = e;
		}
		
		assertThat(caught, is(nullValue()));
	}
	
	@Test
	public void collectDigitsShouldAppendNewDigits()
	{
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0");
			oneOf(observer).digitsUpdated("1");
			oneOf(observer).digitsUpdated("12");
		}}); 
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.collectDigit(2);
	}
	
	@Test
	public void shouldSetTheDigitsBackToZeroOnClear()
	{
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("1");
			oneOf(observer).digitsUpdated("0");
			allowing(observer).digitsUpdated(with(any(String.class)));
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.clear();
	}
	
	@Test
	public void shouldSetTheDigitsBackToZeroOnOperator()
	{
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("1");
			atLeast(2).of(observer).digitsUpdated("0");
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.operator(Calculator.PLUS);
	}
	
	@Test
	public void shouldUpdateObserverOnSubscribe()
	{
		context.checking(new Expectations() {{
				oneOf(observer).digitsUpdated(with(any(String.class)));
		}});
		
		model.subscribe(observer);
	}
	
	@Test
	public void shouldInitializeToZero()
	{
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0");
		}});
		
		model.subscribe(observer);
	}
	
	@Test
	public void shouldSetTheDigitsBackToZeroOnClearEntry()
	{
		final Sequence clearing = context.sequence("clearing");
		
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0"); inSequence(clearing);
			oneOf(observer).digitsUpdated("1"); inSequence(clearing);
			oneOf(observer).digitsUpdated("0"); inSequence(clearing);
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.clearEntry();
	}
	
	@Test
	public void shouldRemoveTheLastDigitOnBackspace()
	{
		final Sequence backspace = context.sequence("backspace");
		
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0"); inSequence(backspace);
			oneOf(observer).digitsUpdated("1"); inSequence(backspace);
			oneOf(observer).digitsUpdated("12"); inSequence(backspace);
			oneOf(observer).digitsUpdated("1"); inSequence(backspace);
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.collectDigit(2);
		model.backspace();
	}
	
	@Test
	public void shouldSetTheDigitsBackToZeroIfTheLastDigitIsBackspaced()
	{
		final Sequence backspace = context.sequence("backspace");
		
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0"); inSequence(backspace);
			oneOf(observer).digitsUpdated("1"); inSequence(backspace);
			oneOf(observer).digitsUpdated("0"); inSequence(backspace);
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.backspace();
	}
	
	@Test
	public void onePlusOneIsTwo() throws Exception {
		final Sequence s = context.sequence("onePlusOneIsTwo");
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0"); inSequence(s);
			oneOf(observer).digitsUpdated("1"); inSequence(s);
			oneOf(observer).digitsUpdated("0"); inSequence(s);
			oneOf(observer).digitsUpdated("1"); inSequence(s);
			oneOf(observer).digitsUpdated("2"); inSequence(s);
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.operator(Calculator.PLUS);
		model.collectDigit(1);
		model.operator(Calculator.ENTER);
	}
	
	@Test
	public void onePlusTwoIsThree() throws Exception {
		final Sequence s = context.sequence("onePlusTwoIsThree");
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0"); inSequence(s);
			oneOf(observer).digitsUpdated("1"); inSequence(s);
			oneOf(observer).digitsUpdated("0"); inSequence(s);
			oneOf(observer).digitsUpdated("2"); inSequence(s);
			oneOf(observer).digitsUpdated("3"); inSequence(s);
		}});
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.operator(Calculator.PLUS);
		model.collectDigit(2);
		model.operator(Calculator.ENTER);
	}
	
	@Test
	public void afterAnOperationCollectANewEntry() throws Exception {
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("3");
		}});

		onePlusOneIsTwo();
		model.collectDigit(3);
	}
	
	@Test
	public void enterRepeatsTheOperationWithTheSecondOperand() throws Exception {
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("5");
			oneOf(observer).digitsUpdated("7");
		}});
		
		onePlusTwoIsThree();
		model.operator(Calculator.ENTER);
		model.operator(Calculator.ENTER);
	}
	
	@Test
	public void pressingEnterStartsANewExpression() throws Exception {
		onePlusTwoIsThree();
		
		final Sequence s = context.sequence("pressingEnterStartsANewExpression");
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("1"); inSequence(s);
			oneOf(observer).digitsUpdated("0"); inSequence(s);
			oneOf(observer).digitsUpdated("1"); inSequence(s);
			oneOf(observer).digitsUpdated("2"); inSequence(s);
		}});
		
		model.collectDigit(1);
		model.operator(Calculator.PLUS);
		model.collectDigit(1);
		model.operator(Calculator.ENTER);
	}
}
