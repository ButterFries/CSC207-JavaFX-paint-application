package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view

	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };

		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setMinWidth(100);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		this.view.getPaintPanel().setMode(command);
		
		PaintModel paintModel = this.view.getPaintPanel().getModel();
		boolean fill = this.view.getPaintPanel().getFill();
		int thickness = this.view.getPaintPanel().getThickness();
		
		
		
		if (command == "Circle") {
			this.view.getPaintPanel().setStrat(new CircleStrategy(paintModel,
					fill, thickness));
		}
		else if (command == "Rectangle") {
			this.view.getPaintPanel().setStrat(new RectangleStrategy(paintModel, fill,
					thickness));
		}
		else if (command == "Square") {
			//this.view.getPaintPanel().setStrat(new SquareStrategy());
		}
		else if (command == "Squiggle") {
			//this.view.getPaintPanel().setStrat(new SquiggleStrategy());
		}
		System.out.println(command);
	}
}
