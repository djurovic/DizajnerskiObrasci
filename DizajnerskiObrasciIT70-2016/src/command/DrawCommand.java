package command;

import java.io.Serializable;

import model.Shape;
import model.Model;

public class DrawCommand implements ShapeCommand, Serializable {

	private Shape shape;
	private Model shapes;
	
	public DrawCommand(Shape shape, Model shapes) {
		this.shape = shape;
		this.shapes = shapes;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shapes.addShape(shape);		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shapes.removeShape(shape);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Model getShapes() {
		return shapes;
	}

	public void setShapes(Model shapes) {
		this.shapes = shapes;
	}

	@Override
	public String toString() {
		return "DrawCommand-" + shape.toString();
	}
	
	
	
	
}
