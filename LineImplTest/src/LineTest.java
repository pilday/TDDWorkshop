import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.omg.CORBA.Environment;

public class LineTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private Point pointArray[];
	
	@Before
	public void Setup(){
		// Make sure the array has the same state before every unit test
		pointArray = new Point[]{
				new Point(),
				new Point(1, 1),
				new Point(-1, -1),
				new Point(123456789, 987654321),
				new Point(0, 0)
		};
	}
	
	@Test
	public void TestLengthMethod() {
		Line line = new Line(pointArray);
		
		assertEquals(pointArray.length, line.length());
	}
	
	@Test
	public void TestLengthMethodEmptyLine() {
		Line line = new Line();
		
		assertEquals(0, line.length());
	}
	
	@Test
	public void TestLengthAfterAdd(){
		Line line = new Line(pointArray);
		Point point = new Point();
		
		line.add(point);
		
		assertEquals(pointArray.length + 1, line.length());
	}
	
	@Test
	public void TestLengthAfterAddToEmpty(){
		Line line = new Line();
		Point point = new Point();
		
		line.add(point);
		
		assertEquals(1, line.length());
	}
	
	@Test
	public void TestEqualsSelf(){
		Line line = new Line(pointArray);
		
		assertTrue(line.equals(line));
	}
	
	@Test
	public void TestEqualsSameArray(){
		Line line = new Line(pointArray);
		Line line2 = new Line(pointArray);
		
		assertTrue(line.equals(line2));
	}
	
	@Test
	public void TestEqualsDifferentOrder(){
		Point point1 = new Point(0,0);
		Point point2 = new Point(1,1);
		Point point3 = new Point(2,2);
		
		Line line1 = new Line();
		line1.add(point1);
		line1.add(point2);
		line1.add(point3);
		
		Line line2 = new Line();
		line2.add(point3);
		line2.add(point2);
		line2.add(point1);
		
		assertTrue(line1.equals(line2));
	}
	
	@Test
	public void TestNotEquals(){
		Line line = new Line(pointArray);
		
		Point differentPointArray[] = new Point[] { 
				new Point(),
				new Point(1, 1)
		};	
		Line line2 = new Line(differentPointArray);
		
		assertFalse(line.equals(line2));
	}
	
	@Test
	public void TestNotEqualsOneMoreElement(){
		Line line = new Line(pointArray);

		Line lineWithAdditionalElement = new Line(pointArray);
		lineWithAdditionalElement.add(new Point());
		
		assertFalse(line.equals(lineWithAdditionalElement));
	}
	
	@Test
	public void HashCodeWithConstructor(){
		Line line1 = new Line(pointArray);
		Line line2 = new Line(pointArray);
		
		int hashLine1 = line1.hashCode();
		int hashLine2 = line2.hashCode();
		
		assertEquals(hashLine1, hashLine2);
	}
	
	@Test
	public void TestHashCodeWithAddMethod(){
		Point point1 = new Point(0,0);
		Point point2 = new Point(1,1);
		Point point3 = new Point(2,2);
		
		Line line1 = new Line();
		line1.add(point1);
		line1.add(point2);
		line1.add(point3);
		
		Line line2 = new Line();
		line2.add(point3);
		line2.add(point2);
		line2.add(point1);
		
		int hashLine1 = line1.hashCode();
		int hashLine2 = line2.hashCode();
		
		assertEquals(hashLine1, hashLine2);
	}
	
	@Test
	public void TestToString(){
		StringBuilder sb = new StringBuilder();
		 
		for (int i = 0; i < pointArray.length; i++){
			if (i==0){ // first line
				sb.append("(");
			}
			else{ 
				sb.append(" ");
			}
			
			sb.append(pointArray[i].toString());
			
			if (i != pointArray.length - 1){
				sb.append(",");
				sb.append(System.lineSeparator());
			}
			else{ // last line
				sb.append(")");
			}
		}
		
		Line line = new Line(pointArray);
		
		assertEquals(sb.toString(), line.toString());
	}
	
	@Test
	public void TestIsValidTooFewPoints(){
		Line line0Points = new Line();
		Line line1Point = new Line(new Point[] {new Point()});
		
		assertFalse(line0Points.isValid());
		assertFalse(line1Point.isValid());
	}
	
	@Test
	public void TestIsValidInfiniteSlope(){
		Point point1 = new Point(1,1);
		Point point2 = new Point(1,2);
		
		Line line = new Line();
		
		line.add(point1);
		line.add(point2);
		
		assertFalse(line.isValid());
	}
	
	@Test 
	public void TestValidSlope(){
		Point point1 = new Point(1,3);
		Point point2 = new Point(3,1);
		
		Line line = new Line();
		line.add(point1);
		line.add(point2);
		
		assertEquals(-1.0, line.slope(), 0);
	}
	
	@Test
	public void TestValidSlopeRecalculation(){
		Point point1 = new Point(1,3);
		Point point2 = new Point(3,1);
		
		Line line = new Line();
		line.add(point1);
		line.add(point2);
		
		line.slope();
		
		Point point3 = new Point(1,1);
		Point point4 = new Point(3,3);
		
		line.add(point3);
		line.add(point4);
		
		assertEquals(0, line.slope(), 0);
	}
	
	@Test
	public void TestSlopeExceptionNoValues(){
		Line line = new Line();
		
		exception.expect(ArithmeticException.class);
		line.slope();
	}
	
	@Test
	public void TestSlopeExceptionsInfiniteSlope(){
		Point point1 = new Point(1,3);
		Point point2 = new Point(1,2);
		
		Line line = new Line();
		line.add(point1);
		line.add(point2);
		
		exception.expect(ArithmeticException.class);
		line.slope(); 
	}
	
	@Test
	public void TestValidIntercept(){
		Point point1 = new Point(2,2);
		Point point2 = new Point(1,3);
		
		Line line = new Line();
		
		line.add(point1);
		line.add(point2);
		
		assertEquals(4.0, line.intercept(), 0);
	}
	
	@Test
	public void TestInterceptException(){
		Point point1 = new Point(1,1);
		Point point2 = new Point(1,2);
		
		Line line = new Line();
		
		line.add(point1);
		line.add(point2);
		
		exception.expect(ArithmeticException.class);
		line.intercept();
	}
}
