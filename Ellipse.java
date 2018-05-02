import java.awt.*;
public class Ellipse extends Item{

	private Point point1;
	private Point point2;
	
	public Ellipse(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public Ellipse(Point point1) {
		this.point1 = point1;
	}
	
	public Ellipse() {
	}

	public boolean includes(Point point) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void render() {
		uiContext.draw(this);
	}
	
	public void setPoint1(Point point) {
		this.point1 = point;
	}
	
	public void setPoint2(Point point) {
		this.point2 = point;
	}
	
	public Point getPoint1() {
		return this.point1;
	}
	
	public Point getPoint2() {
		return this.point2;
	}
	
	public String toString() {
		return "Ellipse's diagonal line from " + this.point1 + " to " + this.point2;
	}

}
