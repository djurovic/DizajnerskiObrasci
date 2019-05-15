package command;

import java.io.Serializable;

import model.Shape;
import model.Model;

public class BringToBackCommand implements ShapeCommand, Serializable {
	
	private Model shapes;
	private Shape shape;
	private int oldIndex;

	public BringToBackCommand(Model shapes, Shape shape) {
		// TODO Auto-generated constructor stub
		this.shape = shape;
		this.shapes = shapes;
	}

	@Override
	public void execute() {
		
		oldIndex = shapes.getShapes().indexOf(shape);
		shapes.removeShape(shape);
		shapes.addShape(0, shape);
		
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
		return "BringToBackCommand-shape=" + shape.getName();
	}
	
	

}
