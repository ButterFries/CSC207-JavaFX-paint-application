package ca.utoronto.utm.paint;
/**
 * A ShapeCommand that performs some action on the instance of
 * a Shape object through its execute method.
 *
 */

public interface ShapeCommand {
	
	public abstract void execute();

}
