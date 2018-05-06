import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PolygonButton extends JButton implements ActionListener{
	
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private PolygonCommand polygonCommand;
	private UndoManager undoManager;

	public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Polygon");
		this.undoManager = undoManager;
		addActionListener(this);
		this.view = jFrame;
		this.drawingPanel = jPanel;
		mouseHandler = new MouseHandler();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		drawingPanel.addMouseListener(mouseHandler);
	}
	
	private class MouseHandler extends MouseAdapter{
		private int pointCount = 0;
		public void mouseClicked(MouseEvent e) {
			if(++pointCount == 1) {
				polygonCommand = new PolygonCommand(View.mapPoint(e.getPoint()));
				undoManager.beginCommand(polygonCommand);
				view.refresh();
			}
			else {
				if(polygonCommand.hitEndPoint(View.mapPoint(e.getPoint()))) {
					polygonCommand.closePolygon();
					drawingPanel.removeMouseListener(this);
					view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					undoManager.endCommand(polygonCommand);
					pointCount = 0;
				}
				else {
					pointCount++;
					polygonCommand.setNewPoint(View.mapPoint(e.getPoint()));
					undoManager.beginCommand(polygonCommand);
				}
			}
			
			
		}
	}

}
