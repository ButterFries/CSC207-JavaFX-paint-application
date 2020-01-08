package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.image.*; 

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view

	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };

		int row = 0;
		for (String label : buttonLabels) {
			//Retrieves the icon image files from this package
			Image icon = new Image(getClass().getResourceAsStream(label +"Icon.png"));
			Button button = new Button(label);
			ImageView iIcon = new ImageView(icon);
			iIcon.setFitHeight(27); iIcon.setFitWidth(27);
			button.setGraphic(iIcon);
			button.setPrefWidth(120); button.setPrefHeight(20);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
	}
	

	@Override
	public void handle(ActionEvent event) {
		ShapeFactory factory = new ShapeFactory(view);
		String command = ((Button) event.getSource()).getText();
		
		if (this.view.getPaintPanel().getMode().equals("Polyline")) {
			this.view.getPaintPanel().finishStrat();
		}
		
		this.view.getPaintPanel().setMode(command);
		
		this.view.getPaintPanel().setStrat(factory.getShapeStrategy(command));
		
	}
}
