package ca.utoronto.utm.paint;

import java.util.*;

import javafx.scene.paint.Color;

public class Squiggle extends Observable {
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
	}public void extend(Point p) {
		this.points.add(p);
	}
	public ArrayList<Point[]> adjacentPairs(){
		ArrayList<Point[]> adjPairs = new ArrayList<Point[]>();
		for (int i = 1; i < this.points.size(); i++) {
			Point[] tuple = new Point[2];
			tuple[0] = this.points.get(i-1);tuple[1] = this.points.get(i);
			adjPairs.add(tuple);
			} return adjPairs;
	}
	public String toString() {
		return "" + this.thickness;
	}
	

}
