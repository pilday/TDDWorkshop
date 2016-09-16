import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class Point {
	final private String STRING_FORMAT = "0.0000E00";

	private double x;
	private double y;
	
	public Point(){
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double norm(){
		return 0.0;
	}
	
	public void rotate(double theta) throws Exception{
		
	}
	
	public void displace(Point p){
		
	}
	
	@Override
	public boolean equals(Object other){
		Point comparePoint = (Point)other;
		
		if(comparePoint.getX() == this.x && comparePoint.getY() == this.y) 
			return true;
		return false;	
	}
	
	@Override
	public int hashCode() {
		return 0;
	};
	
	@Override
	public String toString(){
		//Locale.US parameter used to get a '.' as decimal number separator instead of a ','
		String xAsString = String.format(Locale.US, STRING_FORMAT, this.x);
		String yAsString = String.format(Locale.US, STRING_FORMAT, this.y);
		
		return String.format("( %s, %s )", xAsString, yAsString);
	}

}
