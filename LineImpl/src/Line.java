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
	
	public void add(Point p){
		ArrayList<Point> tempLine = new ArrayList<Point>(Arrays.asList(line));
		tempLine.add(p);
		this.line = (Point[]) tempLine.toArray();
	}
	
	public int length(){
		return this.line.length;
	}
	
	@Override
	public boolean equals(Object other){
		
		if(!(other instanceof Point[])){
			return false;
		}
		
		Point[] compareArray = (Point[]) other;
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
}
