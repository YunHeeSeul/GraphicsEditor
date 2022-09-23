package shape;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class TRectangle extends TShape {
	private Rectangle2D rectangle;
	private int px, py;
	
	public TRectangle() {
		super(new Rectangle2D.Double(0, 0 , 0, 0));
		this.rectangle = (Rectangle2D.Double)this.getShape();
	}
	
	public TShape newInstance(){
		return new TRectangle();
	}

	public void setLocation(int x, int y) {
		this.rectangle.setFrame(x, y, 
		this.rectangle.getWidth(), this.rectangle.getHeight());
	}

	
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x = this.rectangle.getX() + dx;
		double y = this.rectangle.getY() + dy;
		this.rectangle.setFrame(x, y, 
		this.rectangle.getWidth(), this.rectangle.getHeight());		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize(int newX, int newY) {
		double w = newX - this.rectangle.getX();
		double h = newY - this.rectangle.getY();
		this.rectangle.setFrame(this.rectangle.getX(), this.rectangle.getY(), w, h);
	}
	
	public void resize_S(int newX, int newY) {
		int dy = newY - py;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x, y, 
				this.rectangle.getWidth(), this.rectangle.getHeight()+dy);		
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_N(int newX, int newY) {
		int dy = newY - py;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x, y+dy, 
				this.rectangle.getWidth(), this.rectangle.getHeight()-dy);		
		this.py = newY;
	}
	
	public void resize_E(int newX, int newY) {
		int dx = newX - px;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x, y, 
				this.rectangle.getWidth()+dx, this.rectangle.getHeight());		
		this.px = newX;
	}
	
	public void resize_W(int newX, int newY) {
		int dx = newX - px;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x+dx, y, 
				this.rectangle.getWidth()-dx, this.rectangle.getHeight());		
		this.px = newX;
	}
	
	public void resize_NE(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x, y+dy, 
				this.rectangle.getWidth()+dx, this.rectangle.getHeight()-dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_NW(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x+dx, y+dy, 
				this.rectangle.getWidth()-dx, this.rectangle.getHeight()-dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void resize_SW(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;
		double x = this.rectangle.getX();
		double y = this.rectangle.getY();
		this.rectangle.setFrame(x+dx, y, 
				this.rectangle.getWidth()-dx, this.rectangle.getHeight()+dy);	
		this.px = newX;
		this.py = newY;
	}
	
	public void rotate(int newX, int newY) {
		
	}
}
