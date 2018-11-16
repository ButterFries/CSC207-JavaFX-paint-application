package ca.utoronto.utm.paint;

import java.util.ArrayList;

import java.util.Observable;
/**
 * A PaintModel stores all Shape objects to drawn on a
 * PaintPanel.
 */
public class PaintModel extends Observable {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	//dormant shapes are shapes that have been erased due to an undo 
	private ArrayList<Shape> dormantShapes = new ArrayList<Shape>();
	//tempShapes are shapes currently in construction in the PaintPanel
	private Shape[] tempShapes = new Shape[5];



	public void addCircle(Circle c) {
		this.shapes.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	

	
	public void setTempCircle(Circle c) {
		this.tempShapes[0] = c;
		this.setChanged();
		this.notifyObservers();
	}
	
	
	public void addRectangle(Rectangle r) {
		this.shapes.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	

	public void setTempRect(Rectangle r) {
		this.tempShapes[1] = r;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addSquiggle(Squiggle s) {
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();

	}
	
	public void setTempSquiggle(Squiggle s) {
		this.tempShapes[2] = s;
		this.setChanged();
		this.notifyObservers();
		
	}
	public void setTempPolyline(Polyline p) {
		this.tempShapes[3] = p;
		this.setChanged();
		this.notifyObservers();
	}
	public void addPolyline(Polyline p) {
		this.shapes.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addSquares(Square s) {
		this.shapes.add(s);
		this.setChanged();
		this.notifyObservers();
	}

	public void setTempSquare(Square p) {
		this.tempShapes[4] = p;
		this.setChanged();
		this.notifyObservers();
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public Shape[] getTempShapes() {
		return tempShapes;
	}
	/**
	 * Appends the last Shape in the shapes ArrayList to
	 * the dormantShapes ArrayList and removes that same 
	 * Shape from the shapes ArrayList.
	 */
	public void addDormant() {
		if (shapes.size() > 0) {
			Shape current = shapes.get(shapes.size() - 1);
			dormantShapes.add(current); shapes.remove(current);
			this.resetTemps();
			this.setChanged();
			this.notifyObservers();
		}
	}
	/**
	 * Deletes the most recent shape that was added to dormantShapes 
	 * ArrayList (only if it can - is of size > 0) and appends this Shape
	 * to the shapes ArrayList. 
	 */
	public void delDormant() {
		if (dormantShapes.size() > 0) {
			Shape current = dormantShapes.get(dormantShapes.size() - 1);
			dormantShapes.remove(current); shapes.add(current);
			this.setChanged();
			this.notifyObservers();
		}
	}
	/**
	 * Assigns null to every index of tempShapes array.
	 */
	public void resetTemps() {
		for(int i=0; i < tempShapes.length; i++) {
			tempShapes[i] = null;
		}
	}
	/**
	 * Updates the shapes ArrayList by removing all its elements.
	 */
	public void clearAll() {
		shapes.clear();
		this.resetTemps();
		
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Shape> getDormant(){
		return dormantShapes;
	}
	
}
