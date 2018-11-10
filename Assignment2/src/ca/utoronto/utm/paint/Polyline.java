package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import java.util.*;

public class Polyline {
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

}