package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private ArrayList<Squiggle> squiggles = new ArrayList<Squiggle>();
	private Circle tempCircle = null;
	private Rectangle tempRect = null;
	private Squiggle tempSquiggle = null;

	public void addPoint(Point p) {
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void addCircle(Circle c) {
		this.circles.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Circle> getCircles() {
		return circles;
	}
	
	public void setTempCircle(Circle c) {
		this.tempCircle = c;
		this.setChanged();
		this.notifyObservers();
	}
	public Circle getTempCircle() {
		return this.tempCircle;
	}
	
	public void addRectangle(Rectangle r) {
		this.rectangles.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	public void setTempRect(Rectangle r) {
		this.tempRect = r;
		this.setChanged();
		this.notifyObservers();
	}
	public Rectangle getTempRect() {
		return this.tempRect;
	}
	public ArrayList<Squiggle> getSquiggles() {
		return this.squiggles;
	}public void addSquiggle(Squiggle s) {
		this.squiggles.add(s);
		this.setChanged();
		this.notifyObservers();
	}public Squiggle getTempSquiggle() {
		return tempSquiggle;
	}public void setTempSquiggle(Squiggle s) {
		this.tempSquiggle = s;
		this.setChanged();
		this.notifyObservers();
		
	}
	
	
}
