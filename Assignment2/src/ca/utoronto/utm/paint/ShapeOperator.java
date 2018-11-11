package ca.utoronto.utm.paint;
import java.util.ArrayList;

public class ShapeOperator {
	
	private ArrayList<ShapeCommand> commandQueue;
	public ShapeOperator() {	
		commandQueue = new ArrayList<ShapeCommand>();
	}
	
	public void acceptCommand(ShapeCommand command) {
		this.commandQueue.add(command);
	}

	public void operateAll() {
		
		for (ShapeCommand command: this.commandQueue) {
			command.execute();
		}
		commandQueue.clear();
	}
}
