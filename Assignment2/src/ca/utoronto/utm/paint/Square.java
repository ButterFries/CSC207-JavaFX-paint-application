package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

public class Square {
	private Point origin, length;
	private String context;
	private int thickness = 1;
	private int displacement;
	private Color colour;
	private boolean fill = false;
	
	
	
	public Square(Point origin, Point length, Color colour, boolean fill, int thickness) {
		this.origin = origin;
		this.length = length;
		this.colour = colour;
		this.fill = fill;
		this.thickness = thickness;
	}
	public Square(Point origin, Point length, Color colour, boolean fill) {
		this.origin = origin;
		this.length = length;
		this.colour = colour;
		this.fill = fill;
	}
	public Square(Point origin, Point length, Color colour) {
		this.origin = origin;
		this.length = length;
		this.colour = colour;
	}
	public Square(Point origin, Point length) {
		this.origin = origin;
		this.length = length;
		this.colour = Color.WHITE;
	}
	public void computeDimensions() {
		int deltaX = this.origin.getX() - this.length.getX();
		int deltaY = this.origin.getY() - this.length.getY();
		this.displacement = Math.abs(deltaY);
		if ((deltaX >= 0) && (deltaY <= 0)) {
			this.context = "topleft";
		} if ((deltaX >= 0) && (deltaY >= 0)) {
			this.context = "topright";
		} if ((deltaX <= 0) && (deltaY <= 0)) {
			this.context = "botleft";
		} if ((deltaX <= 0) && (deltaY >= 0)) {
			this.context = "botright";
		}
		
	}

	public Point getOrigin() {
		return origin;
	}

	public void setOrigin(Point origin) {
		this.origin = origin;
	}

	public Point getLength() {
		return this.length;
	}
	
	public boolean getFill() {
		return fill;
	}
	
	public int getThickness() {
		return thickness;
	}

	public void setLength(Point length) {
		this.length = length;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public void setColour(Color colour) {
		this.colour = colour;
	}
	public String getContext() {
		return this.context;
	}
	public int getDisplacement() {
		return this.displacement;
	}

}




