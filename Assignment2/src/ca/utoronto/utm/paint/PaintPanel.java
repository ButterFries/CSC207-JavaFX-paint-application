
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
 * A PaintPanel has a model (PaintModel), a View, a mode and style fields:
 * current_colour, fill, thickness. The View interacts with the PaintPanel to
 * set its style fields based on user input. The PaintPanel is responsible
 * for updating a canvas of a GraphicsContext of a Paint application. Since it
 * implements the Observer interface, it updates itself with every change to the
 * state of its model field (the class it observes).
 *
 */
class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Color current_colour = Color.BLACK;
	private String mode; // modifies how we interpret input 
	private boolean fill = false; // determines whether to draw filled in or outlined shapes
	private int thickness = 1; 
	
	private ShapeStrategy strategy;
	
	private Canvas canvas;
	
	

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(600, 700);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		//this.setStyle("-fx-background-color: Blue");
		this.setStyle("-fx-background-color: White");

		this.addEventHandler(MouseEvent.ANY, this);

		this.mode = "Circle"; // bad code here?
		this.strategy = new CircleStrategy(model, fill, thickness, current_colour);

		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}
	/**
	 * Updates the canvas with every modification to the PaintModel's state, by
	 * generates and executes a new set of ShapeCommand's based off the updated
	 *  PaintModel's state.
	 */
	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setLineWidth(1);
	
		
		ArrayList<Shape> shapes = this.model.getShapes();
		ShapeOperator operator = new ShapeOperator();
		//add to the ShapeOperator commandQueue.
		for (Shape s: shapes) {
			operator.acceptCommand(new ModifierCommand(s, g));
			operator.acceptCommand(new DrawCommand(s, g));
		}
		
		
		Shape[] tempShapes = this.model.getTempShapes();
		ShapeOperator tempOperator = new ShapeOperator();
		//Instantiate and add all commands on shapes in construction to commandQueue
		for (Shape ts: tempShapes) {
			if (ts != null) {
				tempOperator.acceptCommand(new ModifierCommand(ts, g));
				tempOperator.acceptCommand(new DrawCommand(ts, g));
			}
			
		}
		//execute all these commands; in turn refreshing the canvas
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

	public Color getColour() {
		return this.current_colour;
	}
}