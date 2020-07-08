import java.awt.Point;
import java.util.Stack;

public class BsplineCommand extends Command{
	private Bspline bspline;
	private int pointCount = 0;
	private Point firstPoint;
	private Point currentPoint;
	private int totalPoints = 0;
	private boolean deleteOnUndo;
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
		if(deleteOnUndo) {
			model.removeItem(bspline);
		}
		else {
			if(pointCount <= 2) {
				currentPoint = bspline.getLastPoint();
				pointTracer.push(currentPoint);
				bspline.removePoint(pointCount--);
				currentPoint = bspline.getLastPoint();
				pointTracer.push(currentPoint);
				bspline.removePoint(pointCount--);
			}
			currentPoint = bspline.getLastPoint();
			pointTracer.push(currentPoint);
			bspline.removePoint(pointCount--);
			execute();
		}
		return true;
	}

	@Override
	public boolean redo() {
		if(deleteOnUndo) {
			model.addItem(bspline);
		}
		else {
			pointCount++;
			currentPoint = pointTracer.pop();
			bspline.addPointAt(pointCount, currentPoint);
			model.addItem(bspline);
		}
		
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
		setNewPoint(++pointCount, new Point(firstPoint));
		bspline.setComplete(true);
		deleteOnUndo = true;
	}

	public void setNewPoint(int count, Point mapPoint) {
		bspline.addPointAt(count, mapPoint);
		//System.out.println("bspline " + ", " + pointCount + ", " + bspline.getPointCount());
		pointCount = count;
	}

	public Line proposedNextPoint(Point mapPoint) {
		if(pointCount > 1) {
			Point last = bspline.pointAt(pointCount - 2);
			return new Line(last, mapPoint);
		}
		else {
			return null;
		}
		
	}
	
}