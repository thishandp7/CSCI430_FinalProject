import java.awt.Point;
import java.util.Vector;

public class Polygon extends Item{
	
	private Vector<Point> points = new Vector();
	private Vector<Point> originPoints = new Vector();
	
	public Polygon() {
		
	}
	
	public void addPoint(Point point) {
		points.add(point);
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
		for(int i = 0; i < points.size(); i++) {
			
		}
		
	}

	@Override
	public void setOrginPoints() {
		Point p;
		for(int i = 0; i < points.size(); i++) {
			p = points.elementAt(i);
			originPoints.add(p);
		}
	}

}
