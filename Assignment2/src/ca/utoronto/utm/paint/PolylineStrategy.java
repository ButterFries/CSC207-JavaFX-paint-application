package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineStrategy implements ShapeStrategy{
	private Polyline polyline;
	private PaintModel model;
	private Line line;
	private Color current_colour = Color.WHITE;
	private boolean fill;
	private int thickness;
	
	public PolylineStrategy(PaintModel model, boolean fill, int thickness) {
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
		if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		}
		
	}
	
	
	
	private void mouseClicked(MouseEvent e) {
		Point point = new Point((int) e.getX(),(int) e.getY());
		if (this.polyline == null) {
			Polyline polyl = new Polyline(this.thickness, this.current_colour, fill);
			Line line = new Line(point, this.current_colour, this.thickness);
			this.polyline = polyl; this.line = line;
		}
		else if (e.getClickCount() == 2) { 
			this.model.addPolyline(this.polyline);
			this.polyline = null; this.line = null;
		}
		else {
			this.line.setEnd(point);
			this.polyline.addLine(this.line);
			this.model.setTempPolyline(this.polyline);
			Line newLine = new Line(point, this.line.getColour(), this.line.getThickness());
			this.line = newLine;
		}

		
	}
	
	private void mouseMoved(MouseEvent e) {
		Point point = new Point((int) e.getX(),(int) e.getY());
		if (this.polyline != null) {
			this.line.setEnd(point);
			this.polyline.addLine(this.line);
			this.model.setTempPolyline(this.polyline);
		}
	}
	
	
	
}
