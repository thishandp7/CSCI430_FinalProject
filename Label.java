import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;
public class Label extends Item {
  private Point startingPoint;
  private Point orignPoint;
  private Vector points = new Vector();
  private String text = "";
  public Label(Point point) {
    startingPoint = point;
  }
  public void addCharacter(char character) {
    text += character;
  }
  public void removeCharacter() {
    if (text.length() > 0) {
      text = text.substring(0, text.length() - 1);
    }
  }
  public boolean includes(Point point) {
    return distance(point, startingPoint) < 10.0;
  }
  public void render() {
    uiContext.draw(this);
  }
  public String getText() {
    return text;
  }
  public Point getStartingPoint() {
    return startingPoint;
  }

public void renderControlPoints() {
	points.clear();
	points.add(startingPoint);
	uiContext.drawControlPoints(points.elements());
}

public void moveObject(Point point) {
	this.startingPoint = new Point(orignPoint.x + point.x, orignPoint.y + point.y);
	
}

public void setOrginPoints() {
	orignPoint = this.startingPoint;
}
}