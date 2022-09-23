package shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


public class TOval extends TShape {
	private Ellipse2D ellipse;
	private int px, py;
	
	public TOval() {
		super(new Ellipse2D.Double(0, 0 , 0, 0));
		this.ellipse = (Ellipse2D.Double)this.getShape();
	}
	
	public TShape newInstance(){
		return new TOval();
	}

	public void setLocation(int x, int y) {
		this.ellipse.setFrame(x, y, 
		this.ellipse.getWidth(), this.ellipse.getHeight());
	}

	
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x = this.ellipse.getX() + dx;
		double y = this.ellipse.getY() + dy;
		this.ellipse.setFrame(x, y, 
		this.ellipse.getWidth(), this.ellipse.getHeight());		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize(int newX, int newY) {
		double w = newX - this.ellipse.getX();
		double h = newY - this.ellipse.getY();
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), w, h);
	}
	
	public void resize_S(int newX, int newY) {
		int dy = newY - py;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x, y, 
				this.ellipse.getWidth(), this.ellipse.getHeight()+dy);		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_N(int newX, int newY) {
		int dy = newY - py;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x, y+dy, 
				this.ellipse.getWidth(), this.ellipse.getHeight()-dy);		
		this.py = newY;
	}
	
	public void resize_E(int newX, int newY) {
		int dx = newX - px;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x, y, 
				this.ellipse.getWidth()+dx, this.ellipse.getHeight());		
		this.px = newX;
	}
	
	public void resize_W(int newX, int newY) {
		int dx = newX - px;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x+dx, y, 
				this.ellipse.getWidth()-dx, this.ellipse.getHeight());		
		this.px = newX;
	}
	
	public void resize_NE(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x, y+dy, 
				this.ellipse.getWidth()+dx, this.ellipse.getHeight()-dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_NW(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x+dx, y+dy, 
				this.ellipse.getWidth()-dx, this.ellipse.getHeight()-dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_SW(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.ellipse.getX();
		double y = this.ellipse.getY();
		this.ellipse.setFrame(x+dx, y, 
				this.ellipse.getWidth()-dx, this.ellipse.getHeight()+dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void rotate(int newX, int newY) {
		
	}
}