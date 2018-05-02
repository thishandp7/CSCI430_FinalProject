import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class EllipseButton  extends JButton implements ActionListener {
	protected JPanel drawingPanel;
	protected View view;
	private MouseHandler mouseHandler;
	private EllipseCommand ellipseCommand;
	private UndoManager undoManager;
	public EllipseButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
		super("Ellipse");
		this.undoManager = undoManager;
		addActionListener(this);
		this.view = jFrame;
		drawingPanel = jPanel;
		mouseHandler = new MouseHandler();
	}


	public void actionPerformed(ActionEvent e) {
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		drawingPanel.addMouseListener(mouseHandler);
	}
	
	private class MouseHandler extends MouseAdapter {
		private int pointCount = 0;
		public void mouseClicked(MouseEvent e) {
			if(++pointCount == 1) {
				System.out.println("1st Point " + e.getPoint());
			}
			else if(pointCount == 2) {
				System.out.println("2nd Point " + e.getPoint());
			}
		}
	}

}
