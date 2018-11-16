package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
/**
 * A Polyline Strategy class has a Polyline, a Line, a PaintModel, 
 * and style fields: a current_colour, a fill and a thickness.
 * This class is called to Instantiate a Polyline and/or Line Object based
 * on MouseEvents.
 *
 */
public class PolylineStrategy implements ShapeStrategy{
	private Polyline polyline;
	private PaintModel model;
	private Line line;
	private Color current_colour;
	private boolean fill;
	private int thickness;
	
	public PolylineStrategy(PaintModel model, boolean fill, int thickness, Color current_colour) {
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

	public void stopPolyline() {
		
		if (this.polyline == null) return;
		
		this.model.addPolyline(this.polyline);
		this.model.setTempPolyline(null);
		this.polyline = null; this.line = null;
	}
	
	@Override
	public void mEvent(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		}
		
	}
	
	
	
	private void mouseClicked(MouseEvent e) {
		Point point = new Point((int) e.getX(),(int) e.getY());
		if (this.polyline == null) { // No Polyline exits yet, so we Instantiate one
			Polyline polyl = new Polyline(this.thickness, this.current_colour, fill);
			Line line = new Line(point, this.current_colour, this.thickness);
			this.polyline = polyl; this.line = line;
		}
		else if (e.getClickCount() == 2) { // when doubling clicked this Polyline is finalized.
			stopPolyline();
		}
		else { // we create a new LineSegment from this point onward
			this.line.setEnd(point);
			this.polyline.addLine(this.line);
			this.model.setTempPolyline(this.polyline);
			Line newLine = new Line(point, this.line.getColour(), this.line.getThickness());
			this.line = newLine;
			
		}
		
	}
	
	private void mouseMoved(MouseEvent e) {
		Point point = new Point((int) e.getX(),(int) e.getY());
		if (this.polyline != null) { // after any click we are always constructing a LineSegment
			this.line.setEnd(point);
			this.polyline.popLine();
			this.polyline.addLine(this.line);
			this.model.setTempPolyline(this.polyline);
		}
	}
	
	
	
}
