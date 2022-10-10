package practical1;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberToolsTest {

	// JUnit tests might be familiar if you did CSCU9A3
	// Here, we have a load of test methods
	// JUnit will look for any method with a @Test annotation and will run it
	// If the method throws an exception, the test has failed; otherwise it passes
	// JUnit methods like assertEquals() are used to test conditions and throw
	// exceptions as necessary.
	
	// in practice we'd want more wide-ranging tests than these!
	
	// tests involving doubles need some rounding to handle precision errors
	private static final double TOLERANCE = 0.000001;
	
	@Test
	public void testParse() {
		double[] expected = {1,2,3,4,5};
		double[] actual = NumberTools.parseString("1,2,3,4,5");
		assertArrayEquals(expected, actual, TOLERANCE);

		expected = new double[] {1.5,2.5,3,4,5};
		actual = NumberTools.parseString("1.5,2.5,3,4,5");
		assertArrayEquals(expected, actual, TOLERANCE);
		
		expected = new double[] {-1.5,0,3,4,5};
		actual = NumberTools.parseString("-1.5,0,3,4,5");
		assertArrayEquals(expected, actual, TOLERANCE);
		
		expected = new double[] {67};
		actual = NumberTools.parseString("67");
		assertArrayEquals(expected, actual, TOLERANCE);
	}
	
	@Test
	public void testComputeStatistic() {
		double expected = 4;
		double actual = NumberTools.computeStatistic("mean", new double[] {0,2,4,6,8});
		assertEquals(expected, actual, TOLERANCE);
		
		expected = 3.16227766016838;
		actual = NumberTools.computeStatistic("sd", new double[] {0,2,4,6,8});
		assertEquals(expected, actual, TOLERANCE);
		
		expected = 20;
		actual = NumberTools.computeStatistic("sum", new double[] {0,2,4,6,8});
		assertEquals(expected, actual, TOLERANCE);
	}

}
