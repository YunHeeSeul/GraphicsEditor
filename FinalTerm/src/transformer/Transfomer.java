package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import shape.TShape;

public abstract class Transfomer {
	
	protected TShape selectedShape;
	protected Point2D previous , center;

	
	public Transfomer(TShape selectedShape){
		this.selectedShape = selectedShape;
		this.previous = new Point2D.Double();
		this.center = new Point2D.Double();
	}
	
	public TShape getShape() { return this.selectedShape; }
	
	abstract public void initTransforming(Graphics2D g2D,int x, int y);
	abstract public void keepTransforming(Graphics2D g2D,int x, int y);
	abstract public void finishTransforming(Graphics2D g2D,int x, int y);
	public void continueTransforming(Graphics2D g2d, int x, int y) {
	}

	public void moveLittle() {
	}


}
