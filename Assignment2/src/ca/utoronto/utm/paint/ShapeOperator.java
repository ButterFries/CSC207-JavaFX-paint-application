package ca.utoronto.utm.paint;
import java.util.ArrayList;
/** 
 *A ShapeOperator stores and operates (executes) a commandQueue of ShapeCommands.
 */

public class ShapeOperator {
	
	private ArrayList<ShapeCommand> commandQueue;
	public ShapeOperator() {	
		commandQueue = new ArrayList<ShapeCommand>();
	}
	
	public void acceptCommand(ShapeCommand command) {
		this.commandQueue.add(command);
	}
	/**
	 * For every ShapeCommand held in commandQueue, operateAll executes
	 * each individual command.
	 */
	public void operateAll() {
		
		for (ShapeCommand command: this.commandQueue) {
			command.execute();
		}
		commandQueue.clear();
	}
}
