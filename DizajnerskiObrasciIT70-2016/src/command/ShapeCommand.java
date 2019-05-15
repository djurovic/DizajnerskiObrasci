package command;

import java.io.Serializable;

import model.Shape;
import model.Model;

public interface ShapeCommand extends Serializable {
	public void execute();
	public void unexecute();
}
