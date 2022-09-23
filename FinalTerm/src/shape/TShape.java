package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import global.Constants.EAnchors;

public abstract class TShape implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	private Shape shape;
	private boolean selected;
	private Color lineColor, fillColor;

	private TAnchors anchors;

	public TShape(Shape shape) {
		this.lineColor = Color.BLACK;
		this.fillColor = null;
		this.shape = shape;
		this.selected = false;
		this.anchors = new TAnchors();
	}

	public abstract TShape newInstance();
	public TShape clone(){
		try {
			return (TShape)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void setLineColor(Color color) {
		this.lineColor = color;
	}
	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	public boolean isSelected() {
		return selected;
	}

	public Shape getShape() {
		return this.shape;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;

	}

	public void transformShape(AffineTransform affineTransform) {
		this.shape = affineTransform.createTransformedShape(this.shape);
	}

	public double getCenterX() {
		return this.shape.getBounds2D().getCenterX();
	}

	public double getCenterY() {
		return this.shape.getBounds2D().getCenterY();
	}

	public void draw(Graphics2D g2D) {
		if(this.fillColor != null){
			g2D.setColor(fillColor);
			g2D.fill(this.shape);
		}
		g2D.setColor(this.lineColor);
		g2D.draw(this.shape);
	}

	public void drawAchors(Graphics2D g2D) {
		this.anchors.draw(g2D, this.shape.getBounds2D());
	}

	public boolean contains(int x, int y) {
		if (this.anchors.contains(x, y)) {
			return true;
		}
		return this.shape.getBounds().contains(x, y);
	}

	public EAnchors getSelectedAnchor() {
		return this.anchors.getSelectedAnchor();
	}

	public abstract void saveCurrentPosition(int x, int y);

	public abstract void setLocation(int x, int y);

	public abstract void move(int newX, int newY);

	public abstract void resize(int newX, int newY);

	public abstract void rotate(int newX, int newY);

	public Rectangle getBounds() {
		return shape.getBounds();
	}

	public double getWidth() {
		return this.shape.getBounds2D().getWidth();
	}

	public double getHeight() {
		return this.shape.getBounds2D().getHeight();
	}

	public TAnchors getAnchorList() {
		return anchors;
	}

	public void addPoint(int x, int y) {
	}

	public void moveLittle() {
	}

	public void resizeCoordinate(Point2D resizeFactor) {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
		shape = (affineTransform.createTransformedShape(shape));
	}
	
	public void move(Point2D resizeAnchor){
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.setToTranslation(resizeAnchor.getX(), resizeAnchor.getY());
		shape = (affineTransform.createTransformedShape(shape));
		}


	

}
