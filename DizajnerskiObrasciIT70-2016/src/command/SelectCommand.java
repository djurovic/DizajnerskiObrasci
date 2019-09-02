package command;

import java.io.Serializable;

import model.Model;
import model.Shape;

public class SelectCommand implements ShapeCommand, Serializable {

	private Shape shape;
	private Model shapes;
	private int index;
	
	public SelectCommand() {
		
	}
	
	public SelectCommand(Shape shape, Model shapes, int index) {
		super();
		this.shape = shape;
		this.shapes = shapes;
		this.index = index;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shapes.toggleSelectShape(index);
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shapes.toggleSelectShape(index);
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
		return "SelectCommand-index=" + String.valueOf(index) + "-" + shape.toString();
	}
	
}
