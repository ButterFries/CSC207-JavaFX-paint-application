package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
/**
 * A Square is a type of Rectangle object, with the key distinction being
 * that its width field is always equal to its height field. Also its diagonal
 * Point can occur on any Point (even a Point which is not its vertex).
 *
 */

public class Square extends Rectangle {
	
	
	public Square(Point origin, Point diagonal, Color colour, boolean fill, int thickness) {
		super(origin, diagonal, colour, fill, thickness);
	}
	public Square(Point origin, Point diagonal) {
		super(origin, diagonal);
	}
	/**
	 * @return Based on this Squares context field, it computes
	 * and returns the upper left Point of the Square.
	 */
	public Point findTopLeft() {
		int x, y;
		switch (this.getContext()) {
		case "topleft":
			return this.origin;
		case "topright":
			x = origin.getX() - width; y = origin.getY();
			return new Point(x, y);
		case "botleft":
			x = origin.getX(); y = origin.getY() - height;
			return new Point(x, y);
		case "botright":
			x = origin.getX() - width; y = origin.getY() - height;
			return new Point(x, y);
		default:
			return origin;}
	}
	/**
	 * Updates the height and width fields, with height = width always
	 *  (Usually called after a change in origin or diagonal Points).
	 */
	public void updateDim() {
		int chngX = Math.abs(this.origin.getX() - this.diagonal.getX());
		int chngY = Math.abs(this.origin.getY() - this.diagonal.getY());
		if (chngX >= chngY) {
			this.height = chngX; this.width = chngX;
		} else {
			this.height = chngY; this.width = chngY;
		}
		 
	}
	/**
	 * Sets the Context field of this Square based on the coordinates of
	 * the Diagonal Point and the Origin Point.
	 */
	public void assignContext() {
		int deltaY = origin.getY() - diagonal.getY();// change in Y value between origin and diagonal
		int deltaX = origin.getX() - diagonal.getX();// change in X value between origin and diagonal
		if (deltaX <= 0 && deltaY <= 0) {
			this.context = "topleft";
		} else if (deltaX >= 0 && deltaY <= 0) {
			this.context = "topright";
		} else if (deltaX <= 0 && deltaY >= 0) {
			this.context = "botleft";
		} else if (deltaX >= 0 && deltaY >= 0) {
			this.context = "botright";
		}
	}
	
}
