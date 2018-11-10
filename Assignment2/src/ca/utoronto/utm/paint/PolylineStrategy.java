package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineStrategy implements ShapeStrategy{
	

	public PaintModel model;
	public PaintPanel paintPanel;
	public Rectangle rectangle;
	
	private Color current_colour = Color.WHITE;
	private boolean fill;
	private int thickness;
	
	public PolylineStrategy(PaintModel model, boolean fill, int thickness) {
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
		
	}
	
	private void mouseDragged(MouseEvent e) {
		
	}
	
	private void mouseClicked(MouseEvent e) {
		
	}
	
	private void mouseReleased(MouseEvent e) {
	
	}
	
	private void mouseEntered(MouseEvent e) {
		
	}
	
	private void mouseExited(MouseEvent e) {
		
	}
	
	private void mouseMoved(MouseEvent e) {
		
	}
	
}
