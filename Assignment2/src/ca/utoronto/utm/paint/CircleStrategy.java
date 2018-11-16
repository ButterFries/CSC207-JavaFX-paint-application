package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
/**
 * A CircleStrategy class has a Circle a PaintModel, 
 * and style fields: a current_colour, a fill and a thickness.
 * This class is called to Instantiate a Circle Object based
 * off of MouseEvents.
 *
 */
public class CircleStrategy implements ShapeStrategy{
	private PaintModel model;
	private Circle circle;
	private Color current_colour;
	
	private boolean fill;
	private int thickness; 
	
	public CircleStrategy(PaintModel paintModel, boolean fill, int thickness, Color current_colour) {
		this.model = paintModel;
		this.fill = fill;
		this.thickness = thickness;
		this.current_colour = current_colour;
		
	}
	
	public void setColour(Color current_colour) {
		this.current_colour = current_colour;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	

	@Override
	public void mEvent(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		}
	}
	// We update the radius of this circle in construction as the mouse is dragged.
	// Also by setTempCircle we update the state of the model.
	private void mouseDragged(MouseEvent e) {
			int length = Math.abs((int)this.circle.getCentre().getX() - (int)e.getX());
			int height = Math.abs((int)this.circle.getCentre().getY() - (int)e.getY());
			int radius = (int)Math.sqrt(length*length + height*height);
			this.circle.setRadius(radius);
			this.model.setTempCircle(this.circle);
			
		}
	//Like all other shapes except for Polyline after we instantiate a new 
	// Circle after a press of the mouse.
	private void mousePressed(MouseEvent e) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		int radius = 0;
		this.circle = new Circle(centre, radius, current_colour, fill, thickness);
	}
	// Like all other shapes except for Polyline after a release this Circle's
	// attributes are finalized and it is added to model's shapes ArrayList.
	private void mouseReleased(MouseEvent e) {
		int length = Math.abs((int)this.circle.getCentre().getX() - (int)e.getX());
		int height = Math.abs((int)this.circle.getCentre().getY() - (int)e.getY());
		int radius = (int)Math.sqrt(length*length + height*height);
		this.circle.setRadius(radius);
		this.model.setTempCircle(null);
		this.model.addCircle(this.circle);
		this.circle = null;
	}
	
	


}
	
