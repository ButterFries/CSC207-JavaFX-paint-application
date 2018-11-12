package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
public abstract class Shape {
	private int thickness;
	private boolean fill;
	private Color colour;
	
	public abstract void setMod(GraphicsContext g);
	public abstract void draw(GraphicsContext g);
	
}
