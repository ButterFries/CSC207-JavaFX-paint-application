package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;

import javafx.scene.paint.Color;
import java.util.*;
/**
 * A Polyline is a type of Shape, which is essentially a collection
 * of conjoined LineSegment objects. It has style attributes
 * thickness, colour and fill.
 *
 */

public class Polyline extends Shape {
	
	//the collection of LineSegment objects making up this Polyline
	private ArrayList<Line> lines = new ArrayList<Line>();
	private int thickness = 1;
	private Color colour = Color.WHITE;
	private boolean fill = false;
	
	
	public Polyline(int thickness, Color colour, boolean fill) {
		this.thickness = thickness;
		this.colour = colour;
		this.fill = fill;
	}
	

	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public Line popLine() {
		if (lines.size() < 1) return null;
		return lines.remove(lines.size()-1);
	}


	public void addLine(Line line) {
		this.lines.add(line);
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
	
	public void setMod(GraphicsContext g) {
		g.setStroke(this.colour);
		g.setLineWidth(this.thickness);
	}
	/**
	 * @param g the GraphicsContext object that this
	 * Polyline object is to be displayed on.
	 * @return generate a graphical representation of 
	 * this Polyline object displayed on g.
	 */
	public void draw(GraphicsContext g) {
		
		ArrayList<Line> lines = this.lines;
			for (Line l: lines) {
				g.strokeLine(l.getOrigin().getX(), l.getOrigin().getY(), 
						l.getEnd().getX(), l.getEnd().getY());
			}
	}

}