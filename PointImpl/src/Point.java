import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class Point {
	final private String STRING_FORMAT = "%5.4E";

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
		// pythagoras
		double xSquare = this.x * this.x;
		double ySquare = this.y * this.y;
				
		return Math.sqrt(xSquare + ySquare);
	}
	
	public void rotate(double theta) throws InvalidParameterException{
		// check if parameter is valid
		if(theta < -180 ) throw new InvalidParameterException("Theta parameter is not allowed to be smaller than -180");
		if(theta > 180) throw new InvalidParameterException("Theta parameter is not allowed to be bigger than +180");
	
		// convert theta from deg to rad
		double radianAngle = theta * Math.PI / 180;
		
		// values need to be saved temporary so they don't affect other outcomes
		double tempY = (y*Math.cos(radianAngle) - x*Math.sin(radianAngle));
		double tempX = (y*Math.sin(radianAngle) + x*Math.cos(radianAngle));
		
		this.x = tempX;
		this.y = tempY;
	}
	
	public void displace(Point p){
		this.x += p.getX();
		this.y += p.getY();
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
		return Double.valueOf(this.x * this.y).hashCode();
	}
	
	@Override
	public String toString(){
		//Locale.US parameter used to get a '.' as decimal number separator instead of a ','
		String xAsString = String.format(Locale.US, STRING_FORMAT, this.x);
		String yAsString = String.format(Locale.US, STRING_FORMAT, this.y);
		
		return String.format("( %s, %s )", xAsString, yAsString);
	}

}
