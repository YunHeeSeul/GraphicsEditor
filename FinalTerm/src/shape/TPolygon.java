package shape;


import java.awt.Polygon;


public class TPolygon extends TShape {
	private static final long serialVersionUID = 1L;
	private Polygon polygon;
	private int px, py;
	
	


	public TPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}

	public TShape newInstance() {
		return new TPolygon();
	}

	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	public void setLocation(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}

	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	

	public void resize(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}

	@Override
	public void rotate(int newX, int newY) {
	}

	@Override
	public void move(int newX, int newY) {
	}
}
