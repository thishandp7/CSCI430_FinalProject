import javax.swing.*;

import java.awt.Point;
import java.awt.event.*;
public class MoveButton extends JButton implements ActionListener {
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private MoveCommand moveCommand;
	private UndoManager undoManager;
	private boolean isPicked;
	private boolean isPlaced;
	private Point selectedPosition;
	private Point mousePosition;
	public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Move");
		addActionListener(this);
		this.view = jFrame;
		this.drawingPanel = jPanel;
		this.undoManager = undoManager;
		mouseHandler = new MouseHandler();
	}
	
	public void actionPerformed(ActionEvent e) {
		view.enableControlPoints();
		view.refresh();
		moveCommand = new MoveCommand();;
		drawingPanel.addMouseListener(mouseHandler);
		view.addMouseListener(mouseHandler);
	}
	
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			view.disableControlPoints();
			view.refresh();
			if(!isPicked) {
				isPicked = moveCommand.pickItem(View.mapPoint(e.getPoint()));
				if(isPicked) {
					drawingPanel.addMouseMotionListener(this);
					undoManager.beginCommand(moveCommand);
					selectedPosition = e.getPoint();
				}
			}
			else if(isPicked) {
				drawingPanel.removeMouseMotionListener(this);
				drawingPanel.removeMouseListener(this);
				view.removeMouseListener(this);
				isPicked = false;
				undoManager.endCommand(moveCommand);
			}
		}
		public void mouseMoved(MouseEvent e) {
			mousePosition = new Point(e.getX() - selectedPosition.x, e.getY() - selectedPosition.y);
			moveCommand.moveItem(mousePosition);
			view.refresh();
		}
	}

}