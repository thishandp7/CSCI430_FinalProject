import java.awt.*;
import java.text.*;
public class EllipseCommand extends Command{
	private Ellipse ellipse;
	private int pointCount;
	
	public EllipseCommand() {
		this(null, null);
		pointCount = 0;
	}
	
	public EllipseCommand(Point point) {
		this(point, null);
		pointCount = 1;
	}
	
	public EllipseCommand(Point point1, Point point2) {
		ellipse = new Ellipse(point1, point2);
		pointCount = 2;
	}
	
	public void setDiagonalLinePoint(Point point) {
		if(++pointCount == 1) {
			ellipse.setPoint1(point);
		}
		else if(pointCount == 2) {
			ellipse.setPoint2(point);
		}
	}
	
	public boolean undo() {
		model.removeItem(ellipse);
		return true;
	}

	public boolean redo() {
		execute();
		return true;
	}

	public void execute() {
		model.addItem(ellipse);
	}
	
	public boolean end() {
		if(ellipse.getPoint1() == null) {
			undo();
			return false;
		}
		if(ellipse.getPoint2() == null) {
			ellipse.setPoint2(ellipse.getPoint1());
		}
		return true;
	}

}
