package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import frame.DrawingPanel.EDrawingState;
import shape.TShape;

public class Mover extends Transfomer {

	public Mover(TShape selectedShape) {
		super(selectedShape);
	}

	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		this.getShape().saveCurrentPosition(x, y);
		this.previous.setLocation(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D g2d, int x, int y) {
		this.getShape().draw(g2d);
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(x-this.previous.getX(), y-this.previous.getY());
		this.selectedShape.transformShape(affineTransform);
		
		this.getShape().draw(g2d);
		this.previous.setLocation(x, y);
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
		
	}
	
	public void moveLittle() {
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(10, 10);
		this.selectedShape.transformShape(affineTransform);
		
	}
}

