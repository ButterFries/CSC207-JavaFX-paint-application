package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;

import java.util.*;

import javafx.scene.paint.Color;
/**
 * A Squiggle is a type of Shape and is a collection of Point objects, 
 * with style fields: thickness, fill, colour.
 */
public class Squiggle extends Shape {
	ArrayList<Point> points = new ArrayList<>();
	private int thickness = 1;
	private Color colour = Color.WHITE;
	private boolean fill = false;
	
	public Squiggle(Point origin) {
		this.points.add(origin);
	}public Squiggle(Point origin, Color colour) {
		this.points.add(origin);
		this.colour = colour;
	}public Squiggle(Point origin, Color colour, int thickness) {
		this.points.add(origin);
		this.colour = colour;
		this.thickness = thickness;
	}public Squiggle(Point origin, Color colour, int thickness, boolean fill) {
		this.points.add(origin);
		this.colour = colour;
		this.thickness = thickness;
		this.fill = fill;
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
	public ArrayList<Point> getPoints() {
		return points;
	}
	public void extend(Point p) {
		this.points.add(p);
	}
	/**
	 * @return An ArrayList of Arrays of size two holding Point objects. Each Array of size two
	 * holds a pair of adjacent Points from the points field of this Squiggle. Primarily
	 * used in drawing a Solid fill version of Squiggle in the draw() method.
	 */
	public ArrayList<Point[]> adjacentPairs(){
		ArrayList<Point[]> adjPairs = new ArrayList<Point[]>();
		for (int i = 1; i < this.points.size(); i++) {
			Point[] tuple = new Point[2];
			tuple[0] = this.points.get(i-1);tuple[1] = this.points.get(i);
			adjPairs.add(tuple);
			} return adjPairs;
	}
	
	public void setMod(GraphicsContext g) {
		g.setLineWidth(this.thickness);
		g.setStroke(this.colour);
	}
	/**
	 * @param g the GraphicsContext object that this
	 * Squiggle object is to be displayed on.
	 * @return generate a graphical representation of 
	 * this Squiggle object displayed on g.
	 */
	public void draw(GraphicsContext g) {
		
		if (this.fill) {
			for (Point[] tup : this.adjacentPairs()) {
				g.strokeLine(tup[0].getX(), tup[0].getY(),
						tup[1].getX(), tup[1].getY());
		}
		}
		else {
			for (Point p : this.getPoints()) {
				g.strokeLine(p.getX(), p.getY(), p.getX(), p.getY());
			}
			}
	}
	

}
