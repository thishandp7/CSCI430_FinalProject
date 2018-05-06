import java.awt.*;
import java.text.*;
import java.util.Stack;
public class PolygonCommand extends Command{
	private Polygon polygon;
	private int pointCount = 0;
	private Point firstPoint;
	private Point currentPoint;
	private boolean delete;
	private Stack<Point> pointTracer = new Stack<Point>();
	public PolygonCommand() {
		this(null);
		pointCount = 0;
	}
	
	public PolygonCommand(Point point) {
		polygon = new Polygon();
		System.out.println(polygon.getPointCount());
		polygon.addPoint(point);
		firstPoint = point;
		pointCount++;
	}
	
	public void setNewPoint(Point point) {
		polygon.addPoint(point);
		pointCount++;
	}
	
	@Override
	public boolean undo() {
		currentPoint = polygon.getLastPoint();
		pointTracer.push(currentPoint);
		polygon.removePoint(--pointCount);
		if(pointCount == 1) {
			delete = true;
		}
		execute();
		return true;
	}

	@Override
	public boolean redo() {
		currentPoint = pointTracer.pop();
		polygon.addPoint(currentPoint);
		model.addItem(polygon);
		pointCount++;
		delete = false;
		return true;
	}

	@Override
	public void execute() {
		model.removeItem(polygon);
		System.out.println(polygon.getPointCount());
		if(pointCount != 0 && !delete) {
			model.addItem(polygon);
		}
	}

	public void closePolygon() {
		setNewPoint(new Point(firstPoint));
	}

	public boolean hitEndPoint(Point mapPoint) {
		double xDifference = firstPoint.getX() - mapPoint.getX();
	    double yDifference = firstPoint.getY() - mapPoint.getY();
	    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference))) < 10.0;
	}

}
