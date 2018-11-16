package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquiggleStrategy implements ShapeStrategy {
	private Squiggle squiggle;
	private PaintModel model;
	private Color current_colour;
	private boolean fill;
	private int thickness;
	
	public SquiggleStrategy(PaintModel model, boolean fill, int thickness) {
		this.model = model;
		this.fill = fill;
		this.thickness = thickness;
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
	
	
	
	private void mouseDragged(MouseEvent e) {
		this.squiggle.extend(new Point((int) e.getX(),(int) e.getY()));
		this.model.setTempSquiggle(this.squiggle);this.squiggle.extend(new Point((int) e.getX(),(int) e.getY()));
		this.model.setTempSquiggle(this.squiggle);
		
	}

	private void mousePressed(MouseEvent e) {
		this.squiggle = new Squiggle(new Point((int) e.getX(), (int) e.getY()), 
				current_colour, thickness, fill);
	}

	private void mouseReleased(MouseEvent e) {
		this.squiggle.extend(new Point((int) e.getX(), (int) e.getY()));
		this.model.setTempSquiggle(null);
		this.model.addSquiggle(this.squiggle);
		this.squiggle = null;
		
	}

}
