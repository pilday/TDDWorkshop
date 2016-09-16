import java.util.ArrayList;
import java.util.Arrays;

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
		return false;
	}
}
