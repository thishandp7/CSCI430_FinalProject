import java.awt.*;
import java.util.*;
public class MoveCommand extends Command{
	private Item item;
	private Point previousPosition;
	private Point movePosition;
	private boolean moveComplete;
	public MoveCommand() {
		
	}
	
	public boolean pickItem(Point point) {
		Enumeration enumeration = model.getItems();
		while(enumeration.hasMoreElements()) {
			item = (Item)(enumeration.nextElement());
			if (item.includes(point)) {
				item.setOrginPoints();
				return true;
			}
		}
		return false;
	}
	public void setCurrentPosition(Point point) {
		previousPosition = point;
	}
	public void moveItem(Point point) {
		if(item != null) {
			item.moveObject(point);
			movePosition = point;
		}
	}
	
	public void placeItem() {
		moveComplete = true;
	}

	public boolean undo() {
		item.setOrginPoints();
		Point p =  new Point(-movePosition.x, -movePosition.y);
		item.moveObject(p);
		execute();
		return true;
	}

	public boolean redo() {
		item.setOrginPoints();
		item.moveObject(movePosition);
		execute();
		return true;
	}

	public void execute() {
		model.removeItem(item);
		model.addItem(item);
	}
}