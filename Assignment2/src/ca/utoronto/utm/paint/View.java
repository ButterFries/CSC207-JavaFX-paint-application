
package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;

	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this);
		this.shapeChooserPanel = new ShapeChooserPanel(this);

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setLeft(this.shapeChooserPanel);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		menuItem.setId("edit undo");

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		menuItem.setId("edit redo");
		
		menuItem = new MenuItem("Clear All");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		menuItem.setId("edit clear");

		menuBar.getMenus().add(menu);
		
		// Another menu for Colour

		menu = new Menu("Colour");
		
		menuItem = new MenuItem("White");
		menuItem.setOnAction(this);
		menuItem.setId("colour RGBA 1.0 1.0 1.0 1.0");
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Black");
		menuItem.setOnAction(this);
		menuItem.setId("colour RGBA 0.0 0.0 0.0 1.0");
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Red");
		menuItem.setOnAction(this);
		menuItem.setId("colour RGBA 1.0 0.0 0.0 1.0");
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Green");
		menuItem.setOnAction(this);
		menuItem.setId("colour RGBA 0.0 1.0 0.0 1.0");
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Blue");
		menuItem.setOnAction(this);
		menuItem.setId("colour RGBA 0.0 0.0 0.8 1.0");
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);
		
		// Another menu for Fill style

		menu = new Menu("Fill Style");
				
		menuItem = new MenuItem("solid");
		menuItem.setOnAction(this);
		menuItem.setId("fill_style solid");
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("outline");
		menuItem.setOnAction(this);
		menuItem.setId("fill_style outline");
		menu.getItems().add(menuItem);
		
		menuBar.getMenus().add(menu);
		
		// Another menu for Fill style
		
		menu = new Menu("Thickness");
		
		TextField text = new TextField("1");
		text.setOnAction(this);
		text.setId("thickness");
		CustomMenuItem custom = new CustomMenuItem(text);
		custom.setDisable(true);
		
		menu.getItems().add(custom);
		
		menuBar.getMenus().add(menu);
		
		return menuBar;
	}

	@Override
	public void handle(ActionEvent event) {
		
		String[] id;
		try {
			id = ((MenuItem)event.getSource()).getId().split(" ");
		} catch (ClassCastException e) {
			id = ((TextField)event.getSource()).getId().split(" ");
		}
			
		if (id[0].equals("colour")){
			Color colour = new Color(Double.parseDouble(id[2]), Double.parseDouble(id[3]), Double.parseDouble(id[4]), Double.parseDouble(id[5]));
			paintPanel.setColour(colour);
		}
		
		if (id[0].equals("fill_style")) {
			if(id[1].equals("solid")) paintPanel.setFill(true);
			else if(id[1].equals("outline")) paintPanel.setFill(false);
		}
		
		if (id[0].equals("thickness")) {
			int thickness;
			try {
				thickness = Integer.parseInt(((TextField)event.getSource()).getText());
				paintPanel.setThickness(thickness);
			}
			catch(NumberFormatException e) { }
		}
		if (id[0].equals("edit") && id[1].equals("undo")) {
			model.addDormant();
		}
		if (id[0].equals("edit") && id[1].equals("redo")) {
			model.delDormant();
		}
		if (id[0].equals("edit") && id[1].equals("clear")) {
			model.clearAll();
		}
	}
}

