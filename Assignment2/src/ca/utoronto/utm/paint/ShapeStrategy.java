package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface ShapeStrategy {
	
	public void mEvent(MouseEvent e);
	public void setColour(Color c);
	public void setFill(boolean b);
	public void setThickness(int n);
	
}
