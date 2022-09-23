package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import global.Constants.EAnchors;
import shape.TAnchors;
import shape.TShape;

public class Resizer extends Transfomer {

	public Resizer(TShape selectedShape) {
		super(selectedShape);

	}

	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		this.previous.setLocation(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D g2d, int x, int y) {
		this.getShape().draw(g2d);

		AffineTransform affineTransform = new AffineTransform();
		Point2D resizeOrigin = this.getResizeAnchor();
		affineTransform.translate(resizeOrigin.getX(), resizeOrigin.getY());
		Point2D resizeFactor = this.computeResizeFactor(this.previous, new Point2D.Double(x,y));
		affineTransform.scale(resizeFactor.getX(), resizeFactor.getY());
		affineTransform.translate(-resizeOrigin.getX(), -resizeOrigin.getY());
		
		this.selectedShape.transformShape(affineTransform);

		this.getShape().draw(g2d);
		this.previous.setLocation(x, y);
	}

	private Point2D computeResizeFactor(Point2D previousP, Point2D currentP) {
		double px = previousP.getX();
		double py = previousP.getY();
		double cx = currentP.getX();
		double cy = currentP.getY();
		double deltaW = 0;
		double deltaH = 0;
		switch (this.selectedShape.getSelectedAnchor()) {
		case E:
			deltaW = cx - px;
			deltaH = 0;
			break;
		case W:
			deltaW = -(cx - px);
			deltaH = 0;
			break;
		case S:
			deltaW = 0;
			deltaH = cy - py;
			break;
		case N:
			deltaW = 0;
			deltaH = -(cy - py);
			break;
		case SE:
			deltaW = cx - px;
			deltaH = cy - py;
			break;
		case NE:
			deltaW = cx - px;
			deltaH = -(cy - py);
			break;
		case SW:
			deltaW = -(cx - px);
			deltaH = cy - py;
			break;
		case NW:
			deltaW = -(cx - px);
			deltaH = -(cy - py);
			break;
		}
		double currentW = this.selectedShape.getWidth();
		double currentH = this.selectedShape.getHeight();
		double xFactor = 1.0;
		if (currentW > 0.0)
			xFactor = (1.0 + deltaW / currentW);
		double yFactor = 1.0;
		if (currentH > 0.0)
			yFactor = (1.0 + deltaH / currentH);
		return new Point.Double(xFactor, yFactor);
	}

	private Point getResizeAnchor() {
		Point resizeAnchor = new Point();
		if (getShape().getSelectedAnchor() == EAnchors.NW) {
			resizeAnchor.setLocation(getShape().getAnchorList().getAnchors().get(EAnchors.SE.ordinal()).getX(),
					getShape().getAnchorList().getAnchors().get(EAnchors.SE.ordinal()).getY());
		} else if (getShape().getSelectedAnchor() == EAnchors.N) {
			resizeAnchor.setLocation(0, getShape().getAnchorList().getAnchors().get(EAnchors.S.ordinal()).getY());
		} else if (getShape().getSelectedAnchor() == EAnchors.NE) {
			resizeAnchor.setLocation(getShape().getAnchorList().getAnchors().get(EAnchors.SW.ordinal()).getX(),
					getShape().getAnchorList().getAnchors().get(EAnchors.SW.ordinal()).getY());
		} else if (getShape().getSelectedAnchor() == EAnchors.W) {
			resizeAnchor.setLocation(getShape().getAnchorList().getAnchors().get(EAnchors.E.ordinal()).getX(), 0);
		} else if (getShape().getSelectedAnchor() == EAnchors.E) {
			resizeAnchor.setLocation(getShape().getAnchorList().getAnchors().get(EAnchors.W.ordinal()).getX(), 0);
		} else if (getShape().getSelectedAnchor() == EAnchors.SW) {
			resizeAnchor.setLocation(getShape().getAnchorList().getAnchors().get(EAnchors.NE.ordinal()).getX(),
					getShape().getAnchorList().getAnchors().get(EAnchors.NE.ordinal()).getY());
		} else if (getShape().getSelectedAnchor() == EAnchors.S) {
			resizeAnchor.setLocation(0, getShape().getAnchorList().getAnchors().get(EAnchors.N.ordinal()).getY());
		} else if (getShape().getSelectedAnchor() == EAnchors.SE) {
			resizeAnchor.setLocation(getShape().getAnchorList().getAnchors().get(EAnchors.NW.ordinal()).getX(),
					getShape().getAnchorList().getAnchors().get(EAnchors.NW.ordinal()).getY());
		}
		return resizeAnchor;
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {

	}

}
