import java.awt.Point;
import java.util.Stack;

public class BsplineCommand extends Command{
	private Bspline bspline;
	private int pointCount = 0;
	private Point firstPoint;
	private Point currentPoint;
	private Stack<Point> pointTracer = new Stack<Point>();
	
	public BsplineCommand() {
		this(null);
		pointCount = 0;
	}
	
	public BsplineCommand(Point point) {
		bspline = new Bspline();
		bspline.addPoint(point);
		firstPoint = point;
		pointCount = 1;
	}

	@Override
	public boolean undo() {
		currentPoint = bspline.getLastPoint();
		pointTracer.push(currentPoint);
		bspline.removePoint(--pointCount);
		execute();
		return true;
	}

	@Override
	public boolean redo() {
		currentPoint = pointTracer.pop();
		bspline.addPoint(currentPoint);
		model.addItem(bspline);
		pointCount++;
		return true;
	}

	@Override
	public void execute() {
		model.removeItem(bspline);
		if(pointCount > 0) {
			model.addItem(bspline);
		}
		
	}

	public boolean hitEndPoint(Point mapPoint) {
		double xDifference = firstPoint.getX() - mapPoint.getX();
	    double yDifference = firstPoint.getY() - mapPoint.getY();
	    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference))) < 10.0;
	}

	public void closeCurve() {
		setNewPoint(new Point(firstPoint));
		
	}

	public void setNewPoint(Point mapPoint) {
		bspline.addPoint(mapPoint);
		pointCount++;
	}
	
}