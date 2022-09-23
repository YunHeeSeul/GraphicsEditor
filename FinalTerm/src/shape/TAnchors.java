package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Vector;

import global.Constants.EAnchors;

public class TAnchors implements Serializable{
	
	private static final long serialVersionUID = 1L;
	static final int ANCHOR_W = 10;
	static final int ANCHOR_H = 10;
	
	private Vector<Ellipse2D.Double> anchors;
	private EAnchors eSelectedAnchor;
	
	@SuppressWarnings("unused")
	public TAnchors() {
		this.anchors = new Vector<Ellipse2D.Double>();
		for (EAnchors eAnchor: EAnchors.values()) {
			this.anchors.add(new Ellipse2D.Double(0, 0, ANCHOR_W, ANCHOR_H));
		}
		this.eSelectedAnchor = null;
	}
	public EAnchors getSelectedAnchor(){
		return this.eSelectedAnchor;
	}
	
	
	private void computeCoordinate(Rectangle2D r) {
		double ax = r.getX() - ANCHOR_W/2;
		double ay = r.getY() - ANCHOR_H/2;
		double h = r.getHeight();
		double w = r.getWidth();
		this.anchors.get(EAnchors.E.ordinal()).setFrame(ax+w, ay+h/2, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.W.ordinal()).setFrame(ax, ay+h/2, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.S.ordinal()).setFrame(ax+w/2, ay+h, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.N.ordinal()).setFrame(ax+w/2, ay, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.NE.ordinal()).setFrame(ax+w, ay, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.NW.ordinal()).setFrame(ax, ay, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.SE.ordinal()).setFrame(ax+w, ay+h, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.SW.ordinal()).setFrame(ax, ay+h, ANCHOR_W, ANCHOR_H);
		this.anchors.get(EAnchors.R.ordinal()).setFrame(ax+w/2, ay-50, ANCHOR_W, ANCHOR_H);
	}

	public void draw(Graphics2D g2D, Rectangle2D rectangle) {
		computeCoordinate(rectangle);
 		for (Ellipse2D.Double anchor: this.anchors) {			
			Color color = g2D.getColor();
			g2D.setColor(g2D.getBackground());
			g2D.fill(anchor);
			g2D.setColor(color);
			g2D.draw(anchor);
		}		
	}
	public double getCenterX(EAnchors eAnchors){
		return this.anchors.get(eAnchors.ordinal()).getCenterX();
	}
	
	public double getCenterY(EAnchors eAnchors){
		return this.anchors.get(eAnchors.ordinal()).getCenterY();
	}
	

	public boolean contains(int x, int y) {
		for (int i=0; i<this.anchors.size(); i++) {
			if(this.anchors.get(i).contains(x,y)){
				this.eSelectedAnchor = EAnchors.values()[i];
				return true;
			}
		}
		this.eSelectedAnchor = null;
		return false;
	}
	public Vector<Ellipse2D.Double> getAnchors() {
		return anchors;
	}
	
	
	
}
