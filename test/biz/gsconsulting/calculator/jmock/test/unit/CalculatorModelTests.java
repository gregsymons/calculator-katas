package biz.gsconsulting.calculator.jmock.test.unit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.jmock.*;
import org.jmock.integration.junit4.*;
import org.junit.*;
import org.junit.runner.*;

import biz.gsconsulting.calculator.jmock.*;


@RunWith(JMock.class)
public class CalculatorModelTests {
	Mockery context = new JUnit4Mockery();
	
	@Test
	public void collectDigitShouldNotifyObserversOfTheNewDigits()
	{
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);

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
		CalculatorModel model = new CalculatorModel();
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
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
		
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("1");
			oneOf(observer).digitsUpdated("12");
			allowing(observer).digitsUpdated(with(any(String.class)));
		}}); 
		
		model.subscribe(observer);
		model.collectDigit(1);
		model.collectDigit(2);
	}
	
	@Test
	public void shouldSetTheDigitsBackToZeroOnClear()
	{
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
		
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
	public void shouldUpdateObserverOnSubscribe()
	{
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
		
		context.checking(new Expectations() {{
				oneOf(observer).digitsUpdated(with(any(String.class)));
		}});
		
		model.subscribe(observer);
	}
	
	@Test
	public void shouldInitializeToZero()
	{
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
		
		context.checking(new Expectations() {{
			oneOf(observer).digitsUpdated("0");
		}});
		
		model.subscribe(observer);
	}
	
	@Test
	public void shouldSetTheDigitsBackToZeroOnClearEntry()
	{
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
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
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
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
		CalculatorModel model = new CalculatorModel();
		final IObserveCalculators observer = context.mock(IObserveCalculators.class);
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
}
