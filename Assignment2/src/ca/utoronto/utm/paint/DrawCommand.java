package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;
/**
 * 
 * A DrawCommand object has a Shape and GraphicsContext
 * and when executed - using its Shape field - it initiates
 * the generation of its current Shape graphical representation.
 *
 */
public class DrawCommand implements ShapeCommand {
	
	private Shape shape;
	private GraphicsContext g;
	
	public DrawCommand(Shape shape, GraphicsContext g) {
		this.shape = shape;
		this.g = g;
	}
	
	@Override
	public void execute() {
		this.shape.draw(g);
	}

}
