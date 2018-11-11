package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;
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
