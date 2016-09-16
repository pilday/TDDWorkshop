import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PointTest {

	 @Rule
	 public final ExpectedException exception = ExpectedException.none();
	 
	@Test
	public void testDefaultConstructor() {
		Point testPoint = new Point();
		assertEquals(0, testPoint.getX(), 0);
		assertEquals(0, testPoint.getY(), 0);
	}
	
	@Test
	public void testAlternateConstructor() {
		Point testPoint = new Point(1.5, 3.5);
		assertEquals(1.5, testPoint.getX(), 0);
		assertEquals(3.5, testPoint.getY(), 0);
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
	
	@Test
	public void testHashCodeMethodWithEqualObjects(){
		Point testPointA = new Point(140.0, -20.5);
		Point testPointB = new Point(140.0, -20.5);
		int hashCodeA = testPointA.hashCode();
		int hashCodeB = testPointB.hashCode();
		assertEquals(hashCodeA, hashCodeB);
	}
	
	@Test
	public void testHashCodeMethodWithNotEqualObjects(){
		Point testPointA = new Point(120.0, 10);
		Point testPointB = new Point(140.0, -20.5);
		int hashCodeA = testPointA.hashCode();
		int hashCodeB = testPointB.hashCode();
		assertNotEquals(hashCodeA, hashCodeB);
	}
	
	@Test
	public void testToStringMethod(){
		Point testPoint = new Point(110.5, 25.3);
		String expectedString = "( +1.1050E+02, +2.5300E+01 )";
		String testPointString = testPoint.toString();
		assertEquals(expectedString, testPointString);
	}
	
	@Test
	public void testNormMethod(){
		Point testPoint = new Point(105.8, 22.44);
		double distanceGot = testPoint.norm();
		double distanceExpected = 108.1535;
		assertEquals(distanceExpected, distanceGot, 0.0001);
	}
	
	@Test
	public void testRotateMethodException() throws Throwable{
		Point testPoint = new Point(10.0, 20.0);
		exception.expect(InvalidParameterException.class);
		testPoint.rotate(200.0);
	}
	
	@Test
	public void testRotateMethod(){
		Point testPoint = new Point(-2.0, 3.0);
		double theta = 90.0;
		testPoint.rotate(theta);te(theta);
		Point expectedResultPoint = new Point(3.0, 2.0);
		assertEquals(testPoint, expectedResultPoint);
		
	}
	

}
