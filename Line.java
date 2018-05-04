import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;
public class Line extends Item {
  private Point point1;
  private Point point2;
  
  private Point orignPoint1;
  private Point orignPoint2;
  
  private Vector points = new Vector();
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Line(Point point1) {
    this.point1 = point1;
  }
  public Line() {
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void render() {
    uiContext.draw(this);
  }
  public void renderControlPoints() {
	  points.clear();
	  points.add(point1);
	  points.add(point2);
	  uiContext.drawControlPoints(points.elements());
  }
  public void setOrginPoints() {
	orignPoint1 = this.point1;
	orignPoint2 = this.point2;
  }
  public void setPoint1(Point point) {	
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }
  public void moveObject(Point point) {
	this.point1 = new Point(orignPoint1.x + point.x, orignPoint1.y + point.y);
	this.point2 = new Point(orignPoint2.x + point.x, orignPoint2.y + point.y);
  }
}

