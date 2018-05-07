import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
class View extends JFrame {
  private UIContext uiContext;
  private JPanel drawingPanel;
  private JPanel buttonPanel;
  private JPanel filePanel;
  private JButton lineButton;
  private JButton deleteButton;
  private JButton ellipseButton;
  private JButton polygonButton;
  private JButton bsplineButton;
  private JButton labelButton;
  private JButton moveButton;
  private JButton selectButton;
  private JButton saveButton;
  private JButton openButton;
  private JButton undoButton;
  private JButton redoButton;
  private static UndoManager undoManager;
  private boolean controlPoints = false;
  private boolean isMoving = false;
  private Item movingItem;
  private Item proposedItem;
  private String fileName;
  // other buttons to be added as needed;
  private static Model model;

  public UIContext getUI() {
    return uiContext;
  }
  private void setUI(UIContext uiContext) {
    this.uiContext = uiContext;
  }
  public static void setModel(Model model) {
    View.model = model;
  }
  public static void setUndoManager(UndoManager undoManager) {
    View.undoManager = undoManager;
  }
  
  public void enableControlPoints(){
	  controlPoints = true;
  }
  
  public void disableControlPoints(){
	  controlPoints = false;
  }
  
  public void setMovingItem(Point point) {
	  Item item;
	  Enumeration enumeration = model.getItems();
		while(enumeration.hasMoreElements()) {
			item = (Item)(enumeration.nextElement());
			if (item.includes(point)) {
				movingItem = item;
				break;
			}
		}
  }
  
  public void moving() {
	  isMoving = true;
  }
  
  public void notMoving() {
	  isMoving = false;
  }

  private class DrawingPanel extends JPanel {
    private MouseListener currentMouseListener;
    private KeyListener currentKeyListener;
    private FocusListener currentFocusListener;
    public DrawingPanel() {
      setLayout(null);
    }
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      (NewSwingUI.getInstance()).setGraphics(g);
      g.setColor(Color.BLUE);
      Enumeration enumeration = model.getItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
      g.setColor(Color.RED);
      enumeration = model.getSelectedItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();;
      }
      
      if(controlPoints) {
    	Enumeration cEnumeration = model.getItems();
    	while (cEnumeration.hasMoreElements()) {
    		((Item) cEnumeration.nextElement()).renderControlPoints();
    	}  	  
      }
      
      if(isMoving) {
    	g.setColor(Color.gray); 
    	movingItem.render();
      }
      else {
        g.setColor(Color.BLUE);
      }
      
      if(proposedItem != null) {
    	  g.setColor(Color.RED);
    	  proposedItem.render();
      }
      
    }
    public void addMouseListener(MouseListener newListener) {
      removeMouseListener(currentMouseListener);
      currentMouseListener =  newListener;
      super.addMouseListener(newListener);
    }
    public void addKeyListener(KeyListener newListener) {
      removeKeyListener(currentKeyListener);
      currentKeyListener =  newListener;
      super.addKeyListener(newListener);
    }
    public void addFocusListener(FocusListener newListener) {
      removeFocusListener(currentFocusListener);
      currentFocusListener =  newListener;
      super.addFocusListener(newListener);
    }
    
    public void drawProposedPoint(Item item) {
    	System.out.println("Draw called!");
    	item.render();
    }
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
    setTitle("Drawing Program 1.1  " + fileName);
  }
  public String getFileName() {
    return fileName;
  }

  public View() {
    super("Drawing Program 1.1  Untitled");
    fileName = null;
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        System.exit(0);
      }
    });
    model.setUI(NewSwingUI.getInstance());
    drawingPanel = new DrawingPanel();
    buttonPanel = new JPanel();
    Container contentpane = getContentPane();
    contentpane.add(buttonPanel, "North");
    contentpane.add(drawingPanel);
    ellipseButton = new EllipseButton(undoManager, this, drawingPanel);
    polygonButton = new PolygonButton(undoManager, this, drawingPanel);
    bsplineButton = new BsplineButton(undoManager, this, drawingPanel);
    lineButton= new LineButton(undoManager, this, drawingPanel);
    labelButton = new LabelButton(undoManager, this, drawingPanel);
    selectButton= new SelectButton(undoManager, this, drawingPanel);
    moveButton = new MoveButton(undoManager, this, drawingPanel);
    deleteButton= new DeleteButton(undoManager);
    saveButton= new SaveButton(undoManager, this);
    openButton= new OpenButton(undoManager, this);
    undoButton = new UndoButton(undoManager);
    redoButton = new RedoButton(undoManager);
    
    buttonPanel.add(ellipseButton);
    buttonPanel.add(polygonButton);
    buttonPanel.add(bsplineButton);
    buttonPanel.add(lineButton);
    buttonPanel.add(labelButton);
    buttonPanel.add(selectButton);
    buttonPanel.add(moveButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(openButton);
    buttonPanel.add(undoButton);
    buttonPanel.add(redoButton);
    this.setSize(966, 443);
  }
  public void refresh() {
    // code to access the Model update the contents of the drawing panel.
    drawingPanel.repaint();
  }
  public static Point mapPoint(Point point){
    // maps a point on the drawing panel to a point
    // on the figure being created. Perhaps this
    // should be in drawing panel
    return point;
  }
	public void drawProposedNextPoint(Line newLine) {
		proposedItem = newLine;
		
	}
}