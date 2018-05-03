import java.awt.Color;
import java.awt.Graphics;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void draw(Label label) {
    if (label.getStartingPoint() != null) {
      if (label.getText() != null) {
        graphics.drawString(label.getText(), (int) label.getStartingPoint().getX(), (int) label.getStartingPoint().getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(label.getText());
    graphics.drawString("_", (int) label.getStartingPoint().getX() + length, (int) label.getStartingPoint().getY());
  }
  public void draw(Line line) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (line.getPoint1() != null) {
      i1 = Math.round((float) (line.getPoint1().getX()));
      i2 = Math.round((float) (line.getPoint1().getY()));
      if (line.getPoint2() != null) {
        i3 = Math.round((float) (line.getPoint2().getX()));
        i4 = Math.round((float) (line.getPoint2().getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }
  
  public void draw(Ellipse ellipse) {
	  int x1 = 0;
	  int y1 = 0;
	  int x2 = 0;
	  int y2 = 0;
	  int width = 0;
	  int height = 0;
	  
	  if(ellipse.getPoint1() != null) {
		  x1 = Math.round((float) (ellipse.getPoint1().getX()));
		  y1 = Math.round((float) (ellipse.getPoint1().getY()));
		  if(ellipse.getPoint2() != null) {
			  x2 = Math.round((float) (ellipse.getPoint2().getX()));
			  y2 = Math.round((float) (ellipse.getPoint2().getY()));
			  width = x2 - x1;
			  height = y2 - y1;
			  graphics.setColor(Color.BLUE);
			  
			  if(width < 0 && height < 0) {
				  graphics.drawOval(x2, y2, Math.abs(width), Math.abs(height));
			  }
			  else if (width < 0 && height >= 0) {
				  graphics.drawOval(x1 -  Math.abs(width), y1, Math.abs(width), height);
			  }
			  else if (width >= 0 && height < 0) {
				  graphics.drawOval(x1, y1 - Math.abs(height), width, Math.abs(height));
			  }
			  else {
				  graphics.drawOval(x1, y1, width, height);
			  }
		  }
		  else {
			  width = 2;
			  height = 2;
			  graphics.setColor(Color.RED);
			  graphics.drawOval(x1, y1, width, height);
		  }
	  }
  }
  
  public void draw(Item item) {
    System.out.println( "Cant draw unknown Item \n");
  }
}