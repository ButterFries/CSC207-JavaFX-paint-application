package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

public class Circle {
	
	private Point centre;
	private int radius;
	private Color colour;
	private boolean fill = false;
	
	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
		this.colour = Color.WHITE;
	}
	
	public Circle(Point centre, int radius, Color colour) {
		this.centre = centre;
		this.radius = radius;
		this.colour = colour;
		this.fill = false;
	}
	
	public Circle(Point centre, int radius, Color colour, boolean fill) {
		this.centre = centre;
		this.radius = radius;
		this.colour = colour;
		this.fill = fill;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}
	
	public boolean getFill() {
		return fill;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public void setColour(Color colour) {
		this.colour = colour;
	}

}
