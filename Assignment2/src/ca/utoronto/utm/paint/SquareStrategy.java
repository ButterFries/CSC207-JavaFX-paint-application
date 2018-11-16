package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareStrategy implements ShapeStrategy {
	public PaintPanel paintPanel;
	public Square square;
	private PaintModel model;
	private Color current_colour;
	private boolean fill;
	private int thickness;
	
	public SquareStrategy(PaintModel model, boolean fill, int thickness, Color current_colour) {
		this.model = model;
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
		this.square = new Square(origin, diagonal, current_colour, fill, thickness);
	}
	
	private void mouseDragged(MouseEvent e) {
		Point diagonal = new Point((int) e.getX(), (int) e.getY());
		this.square.setDiagonal(diagonal);
		this.model.setTempSquare(this.square);
	}
	
	private void mouseClicked(MouseEvent e) {
		
	}
	
	private void mouseReleased(MouseEvent e) {
		Point diagonal = new Point((int) e.getX(), (int) e.getY());
		this.square.setDiagonal(diagonal);
		this.model.addSquares(this.square);
		this.model.setTempSquare(null);
		this.square = null;
	}
	
	private void mouseEntered(MouseEvent e) {
		
	}
	
	private void mouseExited(MouseEvent e) {
		
	}
	
	private void mouseMoved(MouseEvent e) {
		
	}
	

}