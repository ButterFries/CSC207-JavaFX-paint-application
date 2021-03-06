package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
/**
 * A Rectangle is a Shape with an origin Point and a diagonal Point.
 * It also contains aesthetic attributes like its thickness,
 * colour and fill. The origin Point may lie anywhere on the Rectangle.
 */

public class Rectangle extends Shape {
	protected Point origin, diagonal;
	// gives context to where origin Point lies on the Rectangle. For example: "topleft"
	protected String context; 
	protected int height, width;
	protected int thickness = 1;
	protected Color colour;
	protected boolean fill = false;
	
	
	
	public Rectangle(Point origin, Point diagonal) {
		this.origin = origin;
		this.diagonal = diagonal;
		this.updateDim();
		this.assignContext();
	}
	
	public Rectangle(Point origin, Point diagonal, Color colour, boolean fill, int thickness) {
		this.origin = origin;
		this.diagonal = diagonal;
		this.colour = colour;
		this.fill = fill;
		this.thickness = thickness;
		this.updateDim();
		this.assignContext();
	}
	
	/**
	 * @return Based on this Rectangles context field, it computes
	 * and returns the upper left Point of the Rectangle.
	 */
	public Point findTopLeft() {
		int x, y;
		switch (this.getContext()) {
		case "topleft":
			return this.origin;
		case "topright":
			x = diagonal.getX(); y = origin.getY();
			return new Point(x, y);
		case "botleft":
			x = origin.getX(); y = diagonal.getY();
			return new Point(x, y);
		case "botright":
			return diagonal;
		default:
			return origin;}
	}
	/**
	 * Updates the height and width fields. (Usually called after a 
	 * change in origin or diagonal Points).
	 */
	public void updateDim() {
		this.height = Math.abs(this.origin.getY() - this.diagonal.getY());
		this.width = Math.abs(this.origin.getX() - this.diagonal.getX());
	}
	
	/**
	 * Sets the Context field of this Rectangle based on the coordinates of
	 * the Diagonal Point and the Origin Point.
	 */
	public void assignContext() {
		int deltaY = origin.getY() - diagonal.getY(); // change in Y value between origin and diagonal
		int deltaX = origin.getX() - diagonal.getX(); // change in X value between origin and diagonal
		if (deltaX <= 0 && deltaY <= 0) {
			this.context = "topleft";
		} else if (deltaX >= 0 && deltaY <= 0) {
			this.context = "topright";
		} else if (deltaX <= 0 && deltaY >= 0) {
			this.context = "botleft";
		} else if (deltaX >= 0 && deltaY >= 0) {
			this.context = "botright";
		}
	}
	
	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
		this.assignContext();
		this.updateDim();
	}

	public Point getDiagonal() {
		return diagonal;
	}

	public void setDiagonal(Point diagonal) {
		this.diagonal = diagonal;
		this.assignContext();
		this.updateDim();
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public void setMod(GraphicsContext g) {
		g.setStroke(this.colour);
		g.setLineWidth(this.thickness);
		g.setFill(this.colour);
	}
	/**
	 * @param g the GraphicsContext object that this
	 * Rectangle object is to be displayed on.
	 * @return generate a graphical representation of 
	 * this Rectangle object displayed on g.
	 */
	public void draw(GraphicsContext g) {
		Point topLeft = this.findTopLeft();
		int h = this.height; int w = this.width;
		
		
		if(this.fill) {
			g.fillRect(topLeft.getX(), topLeft.getY(), w, h);
		}
		g.strokeRect(topLeft.getX(), topLeft.getY(), w, h);
	}
	
}