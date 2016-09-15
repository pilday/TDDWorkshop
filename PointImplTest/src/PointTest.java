import static org.junit.Assert.*;

import org.junit.Test;


public class PointTest {

	@Test
	public void testStandardConstructor() {
		Point testPoint = new Point();
		assertEquals(0, testPoint.getX());
		assertEquals(0, testPoint.getY());
	}
	
	@Test
	public void testAlternateConstructor() {
		Point testPoint = new Point(1.5, 3.5);
		assertEquals(1.5, testPoint.getX());
		assertEquals(3.5, testPoint.getY());
	}
	
	@Test
	public void testEqualsMethodIsEqual() {
		Point testPointA = new Point(20.0, 105.5);
		Point testPointB = new Point(20.0, 105.5);
		assertTrue(testPointA.equals(testPointB));
	}
	
	@Test
	public void testEqualsMethodIsNotEqual() {
		Point testPointA = new Point(20.0, 105.5);
		Point testPointB = new Point(22.0, 105.5);
		assertFalse(testPointA.equals(testPointB));
	}

}
