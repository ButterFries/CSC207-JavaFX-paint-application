package ca.utoronto.utm.paint;
import javafx.scene.canvas.*;
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
