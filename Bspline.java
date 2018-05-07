import java.awt.Point;
import java.util.HashMap;
import java.util.Vector;

public class Bspline extends Item{
	
	private HashMap<Integer, Point> points = new HashMap<Integer, Point>();
	private Vector<Point> originPoints = new Vector<Point>();
	private HashMap<Integer, Point> newPoints = new HashMap<Integer, Point>();
	private boolean complete;
	public Bspline() {
		
	}
	
	public void addPoint(Point point) {
		points.put(0, point);
	}
	
	public void addPointAt(int index, Point point) {
		points.put(index, point);
	}
	
	public void removePoint(int index) {
		points.remove(index);
	}
	
	public String toString() {
		return "Curve from " + points.get(0) + " to " + points.get(points.size() - 1);
	}
	
	public void render() {
		uiContext.draw(this);
	}
	
	@Override
	public boolean includes(Point point) {
		Point p;
		for(int i = 0; i < points.size(); i++) {
			p = points.get(i);
			if(distance(point, p) < 10.0) {
				return true;
			}

		}
		return false;
	}


	@Override
	public void renderControlPoints() {
		Vector<Point> controlPoints = new Vector<Point>();
		
		for(int i = 0 ; i < points.size(); i++) {
			controlPoints.addElement(points.get(i));
		}
		uiContext.drawControlPoints(controlPoints.elements());
		
	}

	@Override
	public void moveObject(Point point) {
		Point p, op;
		newPoints.clear();
		this.points.clear();
		for(int i = 0; i < originPoints.size(); i++) {
			op = originPoints.elementAt(i);
			newPoints.put(i,new Point(op.x + point.x, op.y + point.y));
		}
		this.points = newPoints;	
	}

	@Override
	public void setOrginPoints() {
		Point p;
		originPoints.clear();
		for(int i = 0; i < points.size(); i++) {
			p = points.get(i);
			originPoints.add(p);
		}
		
	}
	
	public int getPointCount() {
		return points.size();
	}
	
	public Point pointAt(int index) {
		return points.get(index);
	}

	public Point getLastPoint() {
		return points.get(points.size() - 1);
	}
	
	public void clear() {
		points.clear();
		
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
}