
package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

public class Circle extends Shape {
	
	private Point centre;
	private int radius, thickness = 1;
	private Color colour;
	private boolean fill = false;
	
	
	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
		this.colour = Color.BLACK;
	}
	
	public Circle(Point centre, int radius, Color colour) {
		this.centre = centre;
		this.radius = radius;
		this.colour = colour;
	}
	
	public Circle(Point centre, int radius, Color colour, boolean fill) {
		this.centre = centre;
		this.radius = radius;
		this.colour = colour;
		this.fill = fill;
	}
	
	public Circle(Point centre, int radius, Color colour, boolean fill, int thickness) {
		this.centre = centre;
		this.radius = radius;
		this.colour = colour;
		this.fill = fill;
		this.thickness = thickness;
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
	
	public int getThickness() {
		return thickness;
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
	
	public void setMod(GraphicsContext g) {
		g.setStroke(this.colour);
		g.setLineWidth(this.thickness);
		g.setFill(this.colour);
	}
	public void draw(GraphicsContext g) {
		int x = this.centre.getX() - this.radius;
		int y = this.centre.getY() - this.radius;
		int radius = 2*this.radius;
		
		
		if(this.getFill()) {
			g.fillOval(x, y, radius, radius);
		}
		g.strokeOval(x, y, radius, radius);
	}

}

