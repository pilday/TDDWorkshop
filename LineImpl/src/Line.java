import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Line {

	private Point[] line;
	
	public Line(){
		line = new Point[0];
	}
	
	public Line(Point[] line){
		this.line = line;
	}
	
	public Point[] getLine() {
		return line;
	}
	
	public void add(Point p){
		ArrayList<Point> tempLine = new ArrayList<Point>(Arrays.asList(line));
		tempLine.add(p);
		this.line = (Point[]) tempLine.toArray(new Point[tempLine.size()]);
	}
	
	public int length(){
		return this.line.length;
	}
	
	@Override
	public boolean equals(Object other){
		
		if(!(other instanceof Line)){
			return false;
		}
		
		Line otherObject = (Line) other;
		Point[] compareArray = otherObject.getLine();
		ArrayList<Point> thisline = new ArrayList<Point>(Arrays.asList(line));
		ArrayList<Point> compareline = new ArrayList<Point>(Arrays.asList(compareArray));
		
		if(thisline.size() != compareline.size()){
			return false;
		}
		
		for(Point point : thisline){
			if(compareline.contains(point)){
				thisline.remove(point);
			}
		}
		
		return thisline.isEmpty();

	}
	
	@Override
	public int hashCode(){
		
		double hash = 0;
		
		for(Point p : line){
			hash =+ p.getX();
			hash =- p.getY();
		}
		
		return (int) hash;
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb =  new StringBuilder();
		
		if(line.length > 0){
			
		sb.append("(");
		for(int i = 0; i < line.length; i++){
			if(i < line.length - 1){
			sb.append(line[i].toString() + ",\n");
			}
			else{
			sb.append(line[i].toString() + ")");
			}
		}
		return sb.toString();
		}	
		return "";
	}
	
	public boolean isValid(){
		
		if(line.length <= 1){
			return false;
		}
		 return true;
	}
	
	public double slope(){

		      String currentValue = null; 

		      int count = line.length; 
		      double [] xArray = new double [count]; 
		      double [] yArray = new double [count]; 
		      int i = 0; 
		      
		      for (Point p : line) 
		      { 
		         xArray[i] = p.getX(); 
		         yArray[i] = p.getY();
		         ++i; 
		      } 
		      
		      i = 0; 

		      double xSum = 0; 
		      double ySum = 0; 
		      for (i = 0 ; i < xArray.length ; ++i) 
		      { 
		         xSum += xArray[i]; 
		         ySum += yArray[i]; 
		      } 
		      
		      double xMean = xSum / (double)xArray.length; 
		      double yMean = ySum / (double)yArray.length; 

		      double b1 = 0; 
		      double b2 = 0; 
		      for (i = 0 ; i < xArray.length ; ++i) 
		      { 
		         b1 += xArray[i]*yArray[i]; 
		         b2 += xArray[i]*xArray[i]; 
		      } 
		      b1 -= ((double)xArray.length)*xMean*yMean; 
		      b2 -= ((double)xArray.length)*xMean*xMean; 
		      double b = b1 /b2; 
		      return b;
		      
//		      double a = yMean - b*xMean; 
//		      System.out.println ("Geradengleichung : " + b + " * x + " + a); 
//
//		      double sb1 = 0; 
//		      double sb2 = 0; 
//		      double sa1 = 0; 
//		      for (i = 0 ; i < xArray.length ; ++i) 
//		      { 
//		         sb1 += (yArray[i] - b *xArray[i] - a) * (yArray[i] - b *xArray[i] - a); 
//		         sb2 += (xArray[i] - xMean) * (xArray[i] - xMean); 
//		         sa1 += xArray[i] * xArray[i]; 
//		      } 
//		      double sb = sb1 / (((double)xArray.length - 2) * sb2); 
//		      double sa = sb * sa1 / (double)xArray.length; 
//		      sb = Math.sqrt(sb); 
//		      sa = Math.sqrt(sa); 
//		      System.out.println ("Standardabweichung der Steigung : " + sb); 
//		      System.out.println ("Standardabweichung des y-Achsen-Abschnittes : " + sa); 
		      
		   } 
}
