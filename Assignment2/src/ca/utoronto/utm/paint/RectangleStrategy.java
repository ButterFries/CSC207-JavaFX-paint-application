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

public class RectangleStrategy implements ShapeStrategy{

	public PaintModel model;
	public PaintPanel paintPanel;
	public Rectangle rectangle;
	
	private Color current_colour = Color.WHITE;
	private boolean fill;
	private int thickness;
	
	public RectangleStrategy(PaintModel model, boolean fill, int thickness) {
		this.model = model;
		this.fill = fill;
		this.thickness = thickness;
	}
	
	@Override
	public void mEvent(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			mouseExited(event);
		}
		
	}
	
	private void mousePressed(MouseEvent e) {
		Point origin = new Point((int) e.getX(), (int) e.getY());
		Point diagonal = new Point((int) e.getX(), (int) e.getY());
		this.rectangle = new Rectangle(origin, diagonal, current_colour, fill, thickness);
	}
	
	private void mouseDragged(MouseEvent e) {
		Point diagonal = new Point((int) e.getX(), (int) e.getY());
		this.rectangle.setDiagonal(diagonal);
		this.model.setTempRect(this.rectangle);
	}
	
	private void mouseClicked(MouseEvent e) {
		
	}
	
	private void mouseReleased(MouseEvent e) {
		Point diagonal = new Point((int) e.getX(), (int) e.getY());
		this.rectangle.setDiagonal(diagonal);
		this.model.addRectangle(this.rectangle);
		this.rectangle = null;
	}
	
	private void mouseEntered(MouseEvent e) {
		
	}
	
	private void mouseExited(MouseEvent e) {
		
	}
	
	private void mouseMoved(MouseEvent e) {
		
	}
	

}
