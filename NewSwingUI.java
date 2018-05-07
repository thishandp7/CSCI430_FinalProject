import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Enumeration;
import java.util.Vector;
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
			  width = 4;
			  height = 4;
			  graphics.fillOval(x1, y1, width, height);
		  }
	  }
  }
  
  public void draw(Polygon polygon) {
	 int i1 = 0;
	 int i2 = 0;
	 int i3 = 0;
	 int i4 = 0;
	 boolean complete = false;
	 Point point1, point2, initalPoint;
	 if(polygon.getPointCount() > 1) {
		 if(polygon.pointAt(0).equals(polygon.pointAt(polygon.getPointCount() - 1))) {
			 complete = true; 
		 } 
	 }
	 if(!complete && polygon.getPointCount() > 0) {
		 initalPoint = polygon.pointAt(0);
		 graphics.fillRect(initalPoint.x - 3, initalPoint.y - 3, 6, 6);
	 }
	 if(polygon.getPointCount() > 1) {
		 for(int i = 1; i < polygon.getPointCount(); i++) {
			 point1 = polygon.pointAt(i - 1);
			 point2 = polygon.pointAt(i);
			 i1 = Math.round((float) (point1.getX()));
		     i2 = Math.round((float) (point1.getY()));
		     i3 = Math.round((float) (point2.getX()));
		     i4 = Math.round((float) (point2.getY()));
		     graphics.drawLine(i1, i2, i3, i4);
		 }
	 }
  }
  
  public void draw(Bspline bspline) {
	  System.out.println( "draw bspline");
	  int m = 50, n = bspline.getPointCount();
      float xA, yA, xB, yB, xC, yC, xD, yD,
         a0, a1, a2, a3, b0, b1, b2, b3, x=0, y=0, x0, y0;
      boolean first = true;
      for (int i=1; i<n-2; i++)
      {  xA=bspline.pointAt(i-1).x; xB=bspline.pointAt(i).x; xC=bspline.pointAt(i+1).x; xD=bspline.pointAt(i+2).x;
         yA=bspline.pointAt(i-1).y; yB=bspline.pointAt(i).y; yC=bspline.pointAt(i+1).y; yD=bspline.pointAt(i+2).y;
         a3=(-xA+3*(xB-xC)+xD)/6; b3=(-yA+3*(yB-yC)+yD)/6;
         a2=(xA-2*xB+xC)/2;       b2=(yA-2*yB+yC)/2;
         a1=(xC-xA)/2;            b1=(yC-yA)/2;
         a0=(xA+4*xB+xC)/6;       b0=(yA+4*yB+yC)/6;
         for (int j=0; j<=m; j++)
         {  x0 = x; y0 = y;
            float t = (float)j/(float)m;
            x = ((a3*t+a2)*t+a1)*t+a0;
            y = ((b3*t+b2)*t+b1)*t+b0;
            if (first) first = false; 
            else 
            	graphics.drawLine((int)x0, (int)y0, (int)x, (int)y);
         }
      }
  }
  
  
  public void draw(Item item) {
    System.out.println( "Cant draw unknown Item \n");
  }

  public void drawControlPoints(Enumeration points) {
	  graphics.setColor(Color.BLACK);
	  while(points.hasMoreElements()) {
		  Point p = (Point)(points.nextElement());
		  if(p != null) {
			  graphics.fillRect(p.x-3, p.y-3, 6, 6);
		  }
	  }	
  }
}