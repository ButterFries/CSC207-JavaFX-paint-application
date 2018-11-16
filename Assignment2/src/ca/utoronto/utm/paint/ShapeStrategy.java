package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * A ShapeStrategy interface outlines the requirements of a ShapeStrategy,
 * requiring that an instance of this interface must implement the mEvent method,
 * setColour, setFill and setThickness style methods. The ShapeStrategy defines a
 * strategy for constructing a particular instance of Shape based on certain mouse 
 * events.
 *
 */
public interface ShapeStrategy {
	// given a MouseEvent the method produces or alters the state of some Shape instance.
	public void mEvent(MouseEvent e);
	public void setColour(Color c);
	public void setFill(boolean b);
	public void setThickness(int n);
	
}
