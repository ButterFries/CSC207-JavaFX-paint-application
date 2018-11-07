package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;


public class Rectangle {
	private Point origin, diagonal;
	private String context; // gives context to origin's place on rectangle
	private int height, width;
	private int thickness = 1;
	private Color colour = Color.WHITE;
	private boolean fill = false;
	
	
	public Rectangle(Point origin, Point diagonal, Color colour, boolean fill, int thickness) {
		this.origin = origin;
		this.diagonal = diagonal;
		this.colour = colour;
		this.fill = fill;
		this.thickness = thickness;
		this.computeDimensions();
	}
	public Rectangle(Point origin, Point diagonal, Color colour, boolean fill) {
		this.origin = origin;
		this.diagonal = diagonal;
		this.colour = colour;
		this.fill = fill;
		this.computeDimensions();
	}
	public Rectangle(Point origin, Point diagonal, Color colour) {
		this.origin = origin;
		this.diagonal = diagonal;
		this.colour = colour;
		this.computeDimensions();
	}
	public Rectangle(Point origin, Point diagonal) {
		this.origin = origin;
		this.diagonal = diagonal;
		this.computeDimensions();
	}
	
	public void computeDimensions() {
		int deltaX = this.origin.getX() - this.diagonal.getX();
		int deltaY = this.origin.getY() - this.diagonal.getY();
		this.height = Math.abs(deltaX);
		this.width = Math.abs(deltaY);
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

	public Point getDiagonal() {
		return this.diagonal;
	}
	
	public boolean getFill() {
		return fill;
	}
	
	public int getThickness() {
		return thickness;
	}

	public void setDiagonal(Point diagonal) {
		this.diagonal = diagonal;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public void setColour(Color colour) {
		this.colour = colour;
	}
	public int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getContext() {
		return this.context;
	}
}

	