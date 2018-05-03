import javax.swing.*;
import java.awt.event.*;
public class MoveButton extends JButton implements ActionListener {
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private MoveCommand moveCommand;
	private UndoManager undoManager;
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
		undoManager.beginCommand(moveCommand);
	}
	
	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			view.disableControlPoints();
			view.refresh();
			drawingPanel.removeMouseListener(this);
			undoManager.endCommand(moveCommand);
		}
	}

}