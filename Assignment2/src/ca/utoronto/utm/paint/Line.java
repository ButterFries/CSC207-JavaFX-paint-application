package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

public class Line {
	private Point origin, end;
	private int thickness = 1;
	private Color colour = Color.WHITE;
	
	
	
	public Line(Point origin, Point end) {
		this.origin = origin;
		this.end = end;
	}
	public Line(Point origin, Color colour, int thickness) {
		this.origin = origin;
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
	public Point getOrigin() {
		return origin;
	}
	public void setOrigin(Point origin) {
		this.origin = origin;
	}
	public Point getEnd() {
		return end;
	}
	public void setEnd(Point end) {
		this.end = end;
	}
	

}
