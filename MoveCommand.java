import java.awt.*;
import java.util.*;
public class MoveCommand extends Command{
	private Item item;
	private Point currentPosition;
	private Point movePosition;
	private boolean moveComplete;
	public MoveCommand() {
		
	}
	
	public boolean pickItem(Point point) {
		Enumeration enumeration = model.getItems();
		while(enumeration.hasMoreElements()) {
			item = (Item)(enumeration.nextElement());
			if (item.includes(point)) {
				//model.removeItem(item);
				return true;
			}
		}
		return false;
	}
	public void setCurrentPosition(Point point) {
		currentPosition = point;
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
		System.out.println(item);
		item.moveObject(movePosition);
		return true;
	}

	public boolean redo() {
		item.moveObject(movePosition);
		return true;
	}

	public void execute() {
//		if(moveComplete) {
//			item.moveObject(movePosition);
//		}
	}
}