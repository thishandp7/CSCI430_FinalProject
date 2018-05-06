import java.awt.*;
import java.text.*;
public class PolygonCommand extends Command{
	private Polygon polygon = new Polygon();
	private int pointCount = 0;
	private Point firstPoint;
	public PolygonCommand() {
		this(null);
		pointCount = 0;
	}
	
	public PolygonCommand(Point point) {
		System.out.println("First Point");
		polygon.addPoint(point);
		firstPoint = point;
		pointCount++;
	}
	
	public void setNewPoint(Point point) {
		polygon.addPoint(point);
		pointCount++;
		System.out.println("Point# " + pointCount);
	}
	
	@Override
	public boolean undo() {
		//model.removeItem(polygon);
		return true;
	}

	@Override
	public boolean redo() {
		//execute();
		return true;
	}

	@Override
	public void execute() {
		//model.addItem(polygon);
		
	}

	public void closePolygon() {
		System.out.println("Close Poly");
		
	}

	public boolean hitEndPoint(Point mapPoint) {
		double xDifference = firstPoint.getX() - mapPoint.getX();
	    double yDifference = firstPoint.getY() - mapPoint.getY();
	    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference))) < 10.0;
	}

}
