import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Line {

	private Point[] line;
	private double slope = 0;
	private double intercept = 0;
	private boolean regressionCalculated = false;

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
		regressionCalculated = false;
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
				compareline.remove(point);
			}
		}

		return compareline.isEmpty();

	}

	@Override
	public int hashCode(){

		double hash = 0;

		for(Point p : line){
			hash += p.hashCode();
		}

		return (int) hash;
	}

	@Override
	public String toString(){

		StringBuilder sb =  new StringBuilder();

		if(line.length > 0){

			sb.append("(");
			for(int i = 0; i < line.length; i++){
				if(i == 0){
					sb.append(line[i].toString() + "," + System.lineSeparator());
				}
			    else if(i < line.length - 1){
					sb.append(" " + line[i].toString() + "," + System.lineSeparator());
				}
				else{
					sb.append(" " + line[i].toString() + ")");
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
		try{
			calculateRegression();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	public double slope() throws ArithmeticException{
		
		if(regressionCalculated){
			return this.slope;
		}
		
		else{
			calculateRegression();
			regressionCalculated = true;
			return this.slope;
		}
	}

	public double intercept() throws ArithmeticException{
		
		if(regressionCalculated){
			return this.intercept;
		}
		
		else{
			calculateRegression();
			regressionCalculated = true;
			return this.intercept;
		}
	}


	private void calculateRegression() throws ArithmeticException{
		
		try{

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
			this.slope = b1 /b2; 
			this.intercept = yMean - this.slope*xMean; 
		}

		catch(Exception e){
			throw new Exception("Regression couldn't be calculated.");
		}
	}
} 

