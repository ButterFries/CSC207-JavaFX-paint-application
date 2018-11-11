package ca.utoronto.utm.paint;

public class ShapeFactory {
	
	private View view;
	
	public ShapeFactory(View view){
		this.view = view;
	}
	
	
	public ShapeStrategy getShapeStrategy (String command) {
		PaintModel paintModel = this.view.getPaintPanel().getModel();
		boolean fill = this.view.getPaintPanel().getFill();
		int thickness = this.view.getPaintPanel().getThickness();
		
		if (command.equals("Circle")) {
			return new CircleStrategy(paintModel, fill, thickness);
			}
		else if (command.equals("Rectangle")) {
			return new RectangleStrategy(paintModel, fill, thickness);
			}
		else if (command.equals("Square")) {
			return new SquareStrategy(paintModel, fill, thickness);
			}
		else if (command.equals("Squiggle")) {
			//return new SqiggleStrategy(paintModel, fill, thickness);
			return null;
			}
		else if (command.equals("Polyline")) {
			return new PolylineStrategy(paintModel, fill, thickness);
			}
		return null;
		
	}
	

}
