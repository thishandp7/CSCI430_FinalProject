import java.io.*;
import java.util.Vector;
import java.awt.*;
public abstract class Item implements Serializable {
  private Vector points;
  protected static UIContext uiContext;
  public static void setUIContext(UIContext uiContext) {
    Item.uiContext = uiContext;
  }
  public abstract boolean includes(Point point);
  protected double distance(Point point1, Point point2) {
    double xDifference = point1.getX() - point2.getX();
    double yDifference = point1.getY() - point2.getY();
    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
  }
  public void render() {
    uiContext.draw(this);
  }
  public abstract void renderControlPoints();
  public abstract void moveObject(Point point);
  public abstract void setOrginPoints();
}