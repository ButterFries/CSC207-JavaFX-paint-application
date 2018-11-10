
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

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	
	private Color current_colour = Color.WHITE;
	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle; // the rectangle we are building 
	private Squiggle Squiggle; // the squiggle in construction
	private Polyline polyline = null;
	private Line line = null;
	private boolean fill = false; // determines whether to draw filled in or outlined shapes
	private int thickness = 1; 
	
	private ShapeStrategy strategy;
	
	private Canvas canvas;
	
	

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: blue");

		this.addEventHandler(MouseEvent.ANY, this);

		this.mode = "Circle"; // bad code here?
		this.strategy = new CircleStrategy(model, fill, thickness);

		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setLineWidth(1);
		g.setStroke(Color.WHITE);
		g.strokeText("i=" + i, 50, 75);
		
		i = i + 1;
		
		
		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for (Circle c : circles) {
			int x = c.getCentre().getX() - c.getRadius();
			int y = c.getCentre().getY() - c.getRadius();
			int radius = 2*c.getRadius();
			g.setStroke(c.getColour());
			g.setLineWidth(c.getThickness());
			
			if(c.getFill()) {
				g.setFill(c.getColour());
				g.fillOval(x, y, radius, radius);
			}
			g.strokeOval(x, y, radius, radius);
		}
		
		// Draw temporary circles while the mouse is dragged
		Circle c = this.model.getTempCircle();
		if (c != null) {
			int x = c.getCentre().getX() - c.getRadius();
			int y = c.getCentre().getY() - c.getRadius();
			int radius = 2*c.getRadius();
			g.setStroke(c.getColour());
			g.setLineWidth(c.getThickness());
			
			if(c.getFill()) {
				g.setFill(c.getColour());
				g.fillOval(x, y, radius, radius);
			}
			g.strokeOval(x, y, radius, radius);
		}
		
		// Draw Rectangles
		ArrayList<Rectangle> rectangles = this.model.getRectangles();
		for (Rectangle r: rectangles) {
			Point topLeft = r.findTopLeft();
			int h = r.getHeight(); int w = r.getWidth();
			g.setStroke(r.getColour());
			g.setLineWidth(r.getThickness());
			
			if(r.isFill()) {
				g.setFill(r.getColour());
				g.fillRect(topLeft.getX(), topLeft.getY(), w, h);
			}
			g.strokeRect(topLeft.getX(), topLeft.getY(), w, h);
		}
		
		// Draw Temporary Rectangles during a drag of the mouse
		Rectangle r = this.model.getTempRect();
		if (r != null) {
			Point topLeft = r.findTopLeft();
			int h = r.getHeight(); int w = r.getWidth();
			g.setStroke(r.getColour());
			g.setLineWidth(r.getThickness());
			
			if(r.isFill()) {
				g.setFill(r.getColour());
				g.fillRect(topLeft.getX(), topLeft.getY(), w, h);
			}
			g.strokeRect(topLeft.getX(), topLeft.getY(), w, h);
			}
		
		//Draw Polylines
		this.drawPolylines(g);
		// Draw Squiggles
		this.drawSquiggles(g);
		

		
	}
	
	public void drawPolylines(GraphicsContext g) {
		ArrayList<Polyline> polylines = this.model.getPolylines();
		for (Polyline pline : polylines) {
			g.setStroke(pline.getColour());
			g.setLineWidth(pline.getThickness());
			ArrayList<Line> lines = pline.getLines();
				for (Line l: lines) {
					g.strokeLine(l.getOrigin().getX(), l.getOrigin().getY(), 
							l.getEnd().getX(), l.getEnd().getY());
				}
			}
		
		Polyline temPoly = this.model.getTempPolyline();
		if (temPoly != null) {
			g.setStroke(temPoly.getColour()); // 
			g.setLineWidth(temPoly.getThickness()); 
			ArrayList<Line> tlines = temPoly.getLines();
			for (Line tl: tlines) {
				g.strokeLine(tl.getOrigin().getX(), tl.getOrigin().getY(), 
						tl.getEnd().getX(), tl.getEnd().getY());
			}
		}
		
	}
	
	public void drawSquiggles(GraphicsContext g) {
		ArrayList<Squiggle> squiggles = this.model.getSquiggles();
		for (Squiggle s: squiggles) {
			g.setLineWidth(s.getThickness());
			g.setStroke(s.getColour());
			if (s.isFill()) {
				for (Point[] tup : s.adjacentPairs()) {
					g.strokeLine(tup[0].getX(), tup[0].getY(),
							tup[1].getX(), tup[1].getY());
			}
			}
			else {
				for (Point p : s.getPoints()) {
					g.strokeLine(p.getX(), p.getY(), p.getX(), p.getY());
				}
				}
				
			}
		
		Squiggle ts = this.model.getTempSquiggle();
		if (ts != null) {
			g.setLineWidth(ts.getThickness());
			g.setStroke(ts.getColour());
			if (ts.isFill()) {
				for (Point[] ctup : ts.adjacentPairs()) {
					g.strokeLine(ctup[0].getX(), ctup[0].getY(),
							ctup[1].getX(), ctup[1].getY());
			}
				
			}
			else {
			for (Point cp : ts.getPoints()) {
				g.strokeLine(cp.getX(), cp.getY(), cp.getX(), cp.getY());
			}}
		}
		
	}
	

	
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setColour(Color colour) {
		this.current_colour = colour;
	}
	
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public boolean getFill() {
		return this.fill;
	}
	
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
<<<<<<< HEAD
<<<<<<< HEAD
	
=======
>>>>>>> UserStory8
	public int getThickness() {
		return this.thickness;
	}
	
	public void setStrat(ShapeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public PaintModel getModel() {
		return this.model;
	}
<<<<<<< HEAD

	@Override
	public void handle(MouseEvent event) {
		
		this.strategy.mEvent(event);
	}

=======
=======
>>>>>>> UserStory8

	@Override
	public void handle(MouseEvent event) {
		
		this.strategy.mEvent(event);
	}
<<<<<<< HEAD
>>>>>>> UserStory8
=======

>>>>>>> UserStory8
	public void clickedActPoly(MouseEvent e) {
		Point point = new Point((int) e.getX(),(int) e.getY());
		if (this.polyline == null) {
			Polyline polyl = new Polyline(this.thickness, this.current_colour, this.fill);
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
	public void movedActPoly(MouseEvent e) {
		Point point = new Point((int) e.getX(),(int) e.getY());
		if (this.polyline != null) {
			this.line.setEnd(point);
			this.polyline.addLine(this.line);
			this.model.setTempPolyline(this.polyline);
		}
	}
	

}
