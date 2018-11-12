package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {

	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Circle> circles = new ArrayList<Circle>();
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private ArrayList<Squiggle> squiggles = new ArrayList<Squiggle>();
	private ArrayList<Polyline> polylines = new ArrayList<Polyline>();
	private ArrayList<Square> squares = new ArrayList<Square>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> dormantShapes = new ArrayList<Shape>();
	private Shape[] tempShapes = new Shape[5];
	private Circle tempCircle = null;
	private Rectangle tempRect = null;
	private Squiggle tempSquiggle = null;
	private Polyline tempPolyline = null;
	private Square tempSquare = null;

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
		this.shapes.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Circle> getCircles() {
		return circles;
	}
	
	public void setTempCircle(Circle c) {
		this.tempCircle = c;
		this.tempShapes[0] = c;
		this.setChanged();
		this.notifyObservers();
	}
	public Circle getTempCircle() {
		return this.tempCircle;
	}
	
	public void addRectangle(Rectangle r) {
		this.rectangles.add(r);
		this.shapes.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}
	public void setTempRect(Rectangle r) {
		this.tempRect = r;
		this.tempShapes[1] = r;
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
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}public Squiggle getTempSquiggle() {
		return tempSquiggle;
	}public void setTempSquiggle(Squiggle s) {
		this.tempSquiggle = s;
		this.tempShapes[2] = s;
		this.setChanged();
		this.notifyObservers();
		
	}
	public void setTempPolyline(Polyline p) {
		this.tempPolyline = p;
		this.tempShapes[3] = p;
		this.setChanged();
		this.notifyObservers();
	}
	public void addPolyline(Polyline p) {
		this.polylines.add(p);
		this.shapes.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	public Polyline getTempPolyline() {
		return this.tempPolyline;
	}
	
	public ArrayList<Polyline> getPolylines(){
		return this.polylines;
	}
	public void addSquares(Square s) {
		this.squares.add(s);
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Square> getSquares(){
		return this.squares;
	}
	public void setTempSquare(Square p) {
		this.tempSquare = p;
		this.tempShapes[4] = p;
		this.setChanged();
		this.notifyObservers();
	}
	public Square getTempSquare() {
		return this.tempSquare;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Shape[] getTempShapes() {
		return tempShapes;
	}
	public void addDormant() {
		if (shapes.size() > 0) {
			Shape current = shapes.get(shapes.size() - 1);
			dormantShapes.add(current); shapes.remove(current);
			this.resetTemps();
			this.setChanged();
			this.notifyObservers();
		}
	}
	public void delDormant() {
		if (dormantShapes.size() > 0) {
			Shape current = dormantShapes.get(dormantShapes.size() - 1);
			dormantShapes.remove(current); shapes.add(current);
			this.setChanged();
			this.notifyObservers();
		}
	}
	public void resetTemps() {
		for(int i=0; i < tempShapes.length; i++) {
			tempShapes[i] = null;
		}
	}
	public ArrayList<Shape> getDormant(){
		return dormantShapes;
	}
	
}
