import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;


public class BsplineButton extends JButton implements ActionListener{
	
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private BsplineCommand bsplineCommand;
	private UndoManager undoManager;

	public BsplineButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Bspline");
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
			if(pointCount == 0) {
				bsplineCommand = new BsplineCommand(View.mapPoint(e.getPoint()));
				undoManager.beginCommand(bsplineCommand);
				drawingPanel.addMouseMotionListener(this);
				pointCount++;
			}
			else {
				if(bsplineCommand.hitEndPoint(View.mapPoint(e.getPoint()))) {
					bsplineCommand.closeCurve();
					undoManager.beginCommand(bsplineCommand);
					drawingPanel.removeMouseListener(this);
					view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					view.drawProposedNextPoint(null);
					drawingPanel.removeMouseMotionListener(this);
					undoManager.endCommand(bsplineCommand);
					pointCount = 0;
				}
				else {
					pointCount++;
					undoManager.beginCommand(bsplineCommand);
				}
			}
		}
		public void mouseMoved(MouseEvent e) {
			bsplineCommand.setNewPoint(pointCount, View.mapPoint(e.getPoint()));
			view.repaint();
		}
		
	}
	
}