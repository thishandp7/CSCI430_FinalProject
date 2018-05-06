import java.awt.Point;
import java.util.Vector;

public class Polygon extends Item{
	
	private Vector<Point> points = new Vector<Point>();
	private Vector<Point> originPoints = new Vector<Point>();
	private Vector<Point> newPoints = new Vector<Point>();
	public Polygon() {
		
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}
	
	public void removePoint(int index) {
		points.remove(index);
	}
	
	public String toString() {
		return "Polygon from " + points.firstElement() + " to " + points.lastElement();
	}
	
	public void render() {
		uiContext.draw(this);
	}
	
	@Override
	public boolean includes(Point point) {
		Point p;
		for(int i = 0; i < points.size(); i++) {
			p = points.elementAt(i);
			if(distance(point, p) < 10.0) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void renderControlPoints() {
		uiContext.drawControlPoints(points.elements());
		
	}

	@Override
	public void moveObject(Point point) {
		Point p, op;
		newPoints.clear();
		this.points.clear();
		for(int i = 0; i < originPoints.size(); i++) {
			op = originPoints.elementAt(i);
			newPoints.add(new Point(op.x + point.x, op.y + point.y));
		}
		this.points = newPoints;
	}

	@Override
	public void setOrginPoints() {
		Point p;
		originPoints.clear();
		for(int i = 0; i < points.size(); i++) {
			p = points.elementAt(i);
			originPoints.add(p);
		}
	}

	public int getPointCount() {
		return points.size();
	}
	
	public Point pointAt(int index) {
		return points.elementAt(index);
	}

	public Point getLastPoint() {
		return points.lastElement();
	}

}
