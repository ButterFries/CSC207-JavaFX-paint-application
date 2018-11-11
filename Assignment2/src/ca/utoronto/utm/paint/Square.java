package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

public class Square extends Rectangle {
	
	
	public Square(Point origin, Point diagonal, Color colour, boolean fill, int thickness) {
		super(origin, diagonal, colour, fill, thickness);
	}
	public Square(Point origin, Point diagonal) {
		super(origin, diagonal);
	}
	
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
			x = origin.getX() - width; y = diagonal.getY() - height;
			return new Point(x, y);
		default:
			return origin;}
	}
	
	public void updateDim() {
		int chngX = Math.abs(this.origin.getX() - this.diagonal.getX());
		int chngY = Math.abs(this.origin.getY() - this.diagonal.getY());
		if (chngX >= chngY) {
			this.height = chngX; this.width = chngX;
		} else {
			this.height = chngY; this.width = chngY;
		}
		 
	}
	// determines where the origin Point lies on the square
	public void assignContext() {
		int deltaY = origin.getY() - diagonal.getY();
		int deltaX = origin.getX() - diagonal.getX();
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
