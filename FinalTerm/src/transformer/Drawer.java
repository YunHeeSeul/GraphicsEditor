package transformer;

import java.awt.Graphics2D;
import shape.TShape;

public class Drawer extends Transfomer {
	
	public Drawer(TShape selectedShape) {
		super(selectedShape);
	}

	
	public void initTransforming(Graphics2D g2D,int x, int y) {
		this.getShape().draw(g2D);
		this.getShape().setLocation(x, y);
		
	}
	
	public void keepTransforming(Graphics2D g2D,int x, int y) {
		this.getShape().draw(g2D);
		this.getShape().resize(x, y);
		this.getShape().draw(g2D);
	}
	public void continueTransforming(Graphics2D g2d, int x, int y) {
		this.getShape().addPoint(x,y);
	}
	
	public void finishTransforming(Graphics2D g2D,int x, int y) {
	}

}
