
package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

public class Point {
	
	private int x, y, thickness = 1;
	private Color colour = Color.TRANSPARENT;
	
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Point(int x, int y, Color colour) {
		this.x = x;
		this.y = y;
		this.colour = colour;
	}
	
	Point(int x, int y, Color colour, int thickness) {
		this.x = x;
		this.y = y;
		this.colour = colour;
		this.thickness = thickness;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public int getThickness() {
		return thickness;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
