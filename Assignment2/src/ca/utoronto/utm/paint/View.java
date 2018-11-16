
package ca.utoronto.utm.paint;

import java.util.ArrayList;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A View has a model where the Paint applications Shape objects are stored,
 * a paintPanel where ShapeCommands are executed, a shapeChooserPanel where
 * ShapeStrategys are set, and a scene. This object defines the graphical
 * user interface of the Menu's and Button objects on the main window of
 * the Paint application. It also receives and processes commands from user
 * input.
 *
 */
public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private Scene scene;
	
	private ArrayList<TextField> colours = new ArrayList<TextField>();
	
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

		scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("ThemeDefault.css").toExternalForm()
		);
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
		menuItem.setId("file exit");

		menuBar.getMenus().add(menu);
		
		// Another menu for Themes

		menu = new Menu("Themes");

		menuItem = new MenuItem("Default");
		menuItem.setOnAction(this);
		menuItem.setId("theme default");
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Night");
		menuItem.setOnAction(this);
		menuItem.setId("theme night");
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Warm");
		menuItem.setOnAction(this);
		menuItem.setId("theme warm");
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
		
		String colours[] = {"Red", "Green", "Blue", "Alpha"};

		menu = new Menu("Colour");
		
		for (String colour : colours) {
			
			Text label = new Text(colour+" (0.0 - 1.0)");
			label.setId("label colour "+colour);
			CustomMenuItem custom = new CustomMenuItem(label);
			custom.setDisable(true);
			custom.getStyleClass().add(".custom-menu-item");
			menu.getItems().add(custom);
			
			TextField text = new TextField("0.0");
			text.setOnAction(this);
			text.setId("colour "+colour);
			custom = new CustomMenuItem(text);
			custom.setDisable(true);
			custom.getStyleClass().add(".custom-menu-item");
			menu.getItems().add(custom);
			
			text.textProperty().addListener((observer, oldText, newText) -> { 
				updateColour(); 
			});
			
			this.colours.add(text);
			
		}
		
		this.colours.get(3).setText("1.0");

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
		custom.getStyleClass().add(".custom-menu-item");
		
		menu.getItems().add(custom);
		
		menuBar.getMenus().add(menu);
		
		return menuBar;
	}
	
	public void updateColour() {
		try {
			
			Color colour = new Color(Math.max(0, Math.min(Double.parseDouble(colours.get(0).getText()), 1)), 
									 Math.max(0, Math.min(Double.parseDouble(colours.get(1).getText()), 1)), 
									 Math.max(0, Math.min(Double.parseDouble(colours.get(2).getText()), 1)), 
									 Math.max(0, Math.min(Double.parseDouble(colours.get(3).getText()), 1)));
			
			paintPanel.setColour(colour);
			System.out.println(Double.parseDouble(colours.get(1).getText()));
		
		} catch(NumberFormatException e) { }
	}
	

	@Override
	public void handle(ActionEvent event) {
		
		// Splits information stored in the event id
		
		String[] id = null;
		if (event.getSource() instanceof MenuItem) {
			id = ((MenuItem)event.getSource()).getId().split(" ");
		}
		else if (event.getSource() instanceof TextField) {
			id = ((TextField)event.getSource()).getId().split(" ");
		}
		
		if (id[0].equals("theme")) {
			
			scene.getStylesheets().clear();
			
			if(id[1].equals("default")) {
				scene.getStylesheets().add(
					getClass().getResource("ThemeDefault.css").toExternalForm()
				);
			}
			
			else if(id[1].equals("warm")) {
				scene.getStylesheets().add(
					getClass().getResource("ThemeWarm.css").toExternalForm()
				);
			}
			
			else if(id[1].equals("night")) {
				scene.getStylesheets().add(
					getClass().getResource("ThemeNight.css").toExternalForm()
				);
			}
			
			
		}
		
		if (id[0].equals("colour")) {
			
			updateColour();
		
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
			model.addDormant(); // the last shape generated is removed from PaintModel's shapes array
		}
		if (id[0].equals("edit") && id[1].equals("redo")) {
			model.delDormant(); // the last shape added to PaintModel's dormantShapes ArrayList is removed
								// and added to the shapes ArrayList
		}
		if (id[0].equals("edit") && id[1].equals("clear")) {
			model.clearAll();
		}
		if(id[0].equals("file") && id[1].equals("exit")) {
			System.exit(0);
		}
	}
}

