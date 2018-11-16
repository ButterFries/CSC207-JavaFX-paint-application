package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;
/**
 * 
 * A ModifierCommand object has a Shape and GraphicsContext
 * and when executed - using its Shape field - it sets
 * the style attributes for the GraphicsContext on which
 * the current Shape will be drawn on.
 *
 */
public class ModifierCommand implements ShapeCommand {
	
	private Shape shape;
	private GraphicsContext g;
	
	public ModifierCommand(Shape shape, GraphicsContext g) {
		this.shape = shape;
		this.g = g;
	}
	
	@Override
	public void execute() {
		this.shape.setMod(g);
	}

}
