package command;

import java.io.Serializable;

import model.Shape;
import model.Model;

public class BringToFrontCommand implements ShapeCommand, Serializable {

	private Model shapes;
	private Shape shape;
	private int oldIndex;
	
	public BringToFrontCommand(Model shapes, Shape shape) {
		this.shapes = shapes;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		
		oldIndex = shapes.getShapes().indexOf(shape);
		shapes.removeShape(shape);
		shapes.addShape(shape);
		
	}

	@Override
	public void unexecute() {
		
		shapes.removeShape(shape);
		shapes.addShape(oldIndex, shape);
	}

	public Model getShapes() {
		return shapes;
	}

	public void setShapes(Model shapes) {
		this.shapes = shapes;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	@Override
	public String toString() {
		return "BringToFrontCommand-shape=" + shape.getName();
	}

	

}
