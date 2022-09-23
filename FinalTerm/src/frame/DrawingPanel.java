package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import global.Constants.EAnchors;
import shape.TPolygon;
import shape.TRectangle;
import shape.TShape;
import transformer.Drawer;
import transformer.Mover;
import transformer.Resizer;
import transformer.Rotator;
import transformer.Transfomer;

public class DrawingPanel extends JPanel {
	// declarations

	public enum EDrawingState {
		eIdle, eTPTransforming, eNPTransforming
	};

	Cursor cursor;
	// attributes
	private static final long serialVersionUID = 1L;
	private EDrawingState eDrawingState;
	// components
	private Vector<TShape> shapes;
	// working variables
	private TShape savedShape;
	private TShape selectedShape;
	private boolean updated;
	private TShape selectedTool;
	private Transfomer transformer;
	
	
	
	
	public Object getShapes() {
		this.updated = false;
		return this.shapes;
	}

	public void setLineColor(Color color) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		if (this.selectedShape != null) {
			this.selectedShape.setLineColor(color);
		}
		this.selectedShape.draw(g2D);
	}

	public void setFillColor(Color color) {
		if (this.selectedShape != null) {
			this.selectedShape.setFillColor(color);
		}
	}

	@SuppressWarnings("unchecked")
	public void setShapes(Object shapes) {
		if (shapes == null) {
			this.shapes.clear();
		} else {
			this.shapes = (Vector<TShape>) shapes;
		}
		this.transformer = null;
		this.updated = false;
		this.savedShape = null;
		this.selectedShape = null;
		this.repaint();
	}
	
	public void drawAnchors() {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.selectedShape.drawAchors(g2D);
	}

	public boolean isUpdated() {
		return this.updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	public DrawingPanel() {
		super();
		this.setBackground(Color.WHITE);
		this.eDrawingState = EDrawingState.eIdle;
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.shapes = new Vector<TShape>();
		this.selectedShape = null;
	}

	public void initialize() {
		this.selectedShape = null;
		this.selectedTool = null;
		this.transformer = null;
		this.updated = false;
		this.savedShape = null;
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (TShape shape : this.shapes) {
			shape.draw((Graphics2D) g);
		}
		if (this.selectedShape != null) {
			this.selectedShape.drawAchors((Graphics2D) g);
		}
		if (eDrawingState != EDrawingState.eNPTransforming) {
			this.repaint();
		}
	}

	private TShape onShape(int x, int y) {
		for (TShape shape : this.shapes) {
			if (shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}

	private void setSelected(TShape selectedShape) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(false);
			this.selectedShape.drawAchors(g2D);
		}
		this.selectedShape = selectedShape;
		this.selectedShape.setSelected(true);
	}

	private void selectAction(int x, int y) {
		TShape shape = this.onShape(x, y);
		if (shape == null) {
			shape = this.selectedTool.newInstance();
			this.transformer = new Drawer(shape);
		} else {
			if (shape.getSelectedAnchor() == EAnchors.R) {
				this.transformer = new Rotator(shape);
			} else if (shape.getSelectedAnchor() == null) {
				this.transformer = new Mover(shape);
			} else {
				this.transformer = new Resizer(shape);
			}
		}
		this.setSelected(shape);
		this.updated = true;
	}

	private void initTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.initTransforming(g2D, x, y);
	}

	private void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.keepTransforming(g2D, x, y);
	}

	private void continueTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.continueTransforming(g2D, x, y);
	}

	private void finishTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) this.getGraphics();
		g2D.setXORMode(this.getBackground());
		this.transformer.finishTransforming(g2D, x, y);
		this.selectedShape.drawAchors(g2D);

		if (this.transformer instanceof Drawer) {
			this.shapes.add(this.selectedShape);
		}
		
	}


	class MouseHandler implements MouseInputListener {
		

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				if (e.getClickCount() == 1) {
					
					this.mouseL1Clicked(e);
				} else if (e.getClickCount() == 2) {
					this.mouseL2Clicked(e);
				}
			}
		}

		private void mouseL1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if ((selectedTool instanceof TPolygon) && (transformer instanceof Drawer)) {
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPTransforming;
				}
			} else if (eDrawingState == EDrawingState.eNPTransforming) {
				continueTransforming(e.getX(), e.getY());
			}

		}

		private void mouseL2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNPTransforming) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				selectAction(e.getX(), e.getY());
				if (!((selectedTool instanceof TPolygon) && (transformer instanceof Drawer))) {
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eTPTransforming;
				}
			}
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTPTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTPTransforming) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		public void ChangeCursor(int x, int y) {
			TShape shape = onShape(x, y);
			if (shape == null) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			} else {
				global.Constants.EAnchors eAnchors = shape.getSelectedAnchor();
				if (eAnchors == null) {
					setCursor(new Cursor(Cursor.MOVE_CURSOR));
				} else {
					setCursor(new Cursor(eAnchors.getCursor()));
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				ChangeCursor(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eNPTransforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}
	}

	public void setActionCommand(TShape selectedTool) {
		this.selectedTool = selectedTool;
	}

	public void cut() {
		if (this.selectedShape != null) {
			this.savedShape = this.selectedShape;
			this.delete();
		}
	}

	public void copy() {
		if (this.selectedShape != null) {
			this.savedShape = this.selectedShape;
		}
	}

	public void paste() {
		if (this.savedShape != null) {
			this.setSelected(this.savedShape.clone());
			this.transformer = new Mover(this.savedShape);
			this.transformer.moveLittle();
			this.shapes.add(this.selectedShape);
			this.repaint();
		}
	}

	public void delete() {
		if (this.selectedShape != null) {
			this.shapes.remove(this.selectedShape);
			this.selectedShape = null;
			this.repaint();
		}
	}
}
