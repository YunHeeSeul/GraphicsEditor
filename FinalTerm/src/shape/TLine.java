package shape;


import java.awt.geom.Line2D;


public class TLine extends TShape {
	private Line2D line;
	private int px, py;
	
	public TLine() {
		super(new Line2D.Double(0, 0 , 0, 0));
		this.line = (Line2D.Double)this.getShape();
	}
	public TShape newInstance(){
		return new TLine();
	}

	public void setLocation(int x, int y) {
		this.line.setLine(x, y, 
		this.line.getX2(), this.line.getY2());
	}

	
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x1 = this.line.getX1() + dx;
		double y1 = this.line.getY1() + dy;
		double x2 = this.line.getX2() + dx;
		double y2 = this.line.getY2() + dy;
		this.line.setLine(x1, y1, x2, y2);		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize(int newX, int newY) {
		this.line.setLine(this.line.getX1(), this.line.getY1(), newX , newY);
	}
	
	public void resize_S(int newX, int newY) {
		int dy = newY - py;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x, y, 
				this.line.getX2(), this.line.getY2()+dy);		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_N(int newX, int newY) {
		int dy = newY - py;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x, y+dy, 
				this.line.getX2(), this.line.getY2()-dy);		
		this.py = newY;
	}
	
	public void resize_E(int newX, int newY) {
		int dx = newX - px;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x, y, 
				this.line.getX2()+dx, this.line.getY2());		
		this.px = newX;
	}
	
	public void resize_W(int newX, int newY) {
		int dx = newX - px;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x+dx, y, 
				this.line.getX2()-dx, this.line.getY2());		
		this.px = newX;
	}
	
	public void resize_NE(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x, y+dy, 
				this.line.getX2()+dx, this.line.getY2()-dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_NW(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x+dx, y+dy, 
				this.line.getX2()-dx, this.line.getY2()-dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_SW(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.line.getX1();
		double y = this.line.getY1();
		this.line.setLine(x+dx, y, 
				this.line.getX2()-dx, this.line.getY2()+dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void rotate(int newX, int newY) {
		
	}
}
