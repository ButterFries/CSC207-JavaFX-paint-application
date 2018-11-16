package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;
/**
 * A Abstract Shape has a thickness, colour and fill and has the ability
 * to draw itself on a GraphicsContext.
 */
import javafx.scene.canvas.*;
public abstract class Shape {
	private int thickness;
	private boolean fill;
	private Color colour;
	/**
	 * Each Shape instance implements this abstract method
	 * which sets the modifications to the graphical representation 
	 * of this instance on the given g (GraphicsContext).
	 */
	public abstract void setMod(GraphicsContext g);
	/**
	 * Each Shape instance implements this abstract method
	 * which generates the graphical representation of that instance
	 * on the given g (GraphicsContext).
	 */
	public abstract void draw(GraphicsContext g);
	
}
