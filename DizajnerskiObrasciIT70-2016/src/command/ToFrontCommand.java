package command;

import java.io.Serializable;

import model.Shape;
import model.Model;

public class ToFrontCommand implements ShapeCommand, Serializable {

	private Model shapes;
	private Shape shape;
	private int oldIndex;
	
	public ToFrontCommand(Model shapes, Shape shape) {
		this.shapes = shapes;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		oldIndex = shapes.getShapes().indexOf(shape);
		
		if (oldIndex+1 < shapes.getShapes().size()) {
			Shape temp = shapes.getShapes().get(oldIndex + 1);
			shapes.getShapes().set(oldIndex, temp);
			shapes.getShapes().set(oldIndex + 1, shape);
		}
				
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		Shape temp = shapes.getShapes().get(oldIndex);
		
		if (oldIndex+1 < shapes.getShapes().size()) {
			shapes.getShapes().set(oldIndex, shape);
			shapes.getShapes().set(oldIndex+1, temp);
		}
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
		return "ToFrontCommand-shape=" + shape.getName();
	}
	
	

}
