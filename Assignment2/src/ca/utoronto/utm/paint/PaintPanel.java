
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
	
	private Color current_colour;
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
		//this.setStyle("-fx-background-color: Blue");
		this.setStyle("-fx-background-color: White");

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
		//g.setStroke(Color.WHITE);
		//g.setStroke(Color.BLUE);
		//g.strokeText("i=" + i, 50, 75);
		
		//i = i + 1;
		
		ArrayList<Shape> shapes = this.model.getShapes();
		ShapeOperator operator = new ShapeOperator();
		for (Shape s: shapes) {
			operator.acceptCommand(new ModifierCommand(s, g));
			operator.acceptCommand(new DrawCommand(s, g));
		}
		
		
		Shape[] tempShapes = this.model.getTempShapes();
		ShapeOperator tempOperator = new ShapeOperator();
		for (Shape ts: tempShapes) {
			if (ts != null) {
				tempOperator.acceptCommand(new ModifierCommand(ts, g));
				tempOperator.acceptCommand(new DrawCommand(ts, g));
			}
			
		}
		operator.operateAll(); tempOperator.operateAll();
		
		
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
	public String getMode() {
		return mode;
	}
	
	public void setColour(Color colour) {
		this.current_colour = colour;
		this.strategy.setColour(colour);
	}
	
	public void setFill(boolean fill) {
		this.fill = fill;
		this.strategy.setFill(fill);
	}
	
	public boolean getFill() {
		return this.fill;
		
	}
	
	public void setThickness(int thickness) {
		this.thickness = thickness;
		this.strategy.setThickness(thickness);
	}

	public int getThickness() {
		return this.thickness;
	}

	public void setStrat(ShapeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void finishStrat() {
		if (this.strategy.getClass() == PolylineStrategy.class) {
			((PolylineStrategy) this.strategy).stopPolyline();
		}
	}

	public PaintModel getModel() {
		return this.model;	
	}

	@Override
	public void handle(MouseEvent event) {
	
		this.strategy.mEvent(event);
	}
}