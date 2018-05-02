import java.awt.*;
import java.util.*;
public class SelectCommand extends Command {
  private Item item;
  public SelectCommand() {
  }
  public void setPoint(Point point) {
    Enumeration enumeration = model.getItems();
    while (enumeration.hasMoreElements()) {
      item = (Item)(enumeration.nextElement());
      if (item.includes(point)) {
        model.markSelected(item);
        break;
      }
    }
  }
  public boolean undo() {
    model.unSelect(item);
    return true;
  }
  public boolean  redo() {
    execute();
    return true;
  }
  public void execute() {
    model.markSelected(item);
  }
}
