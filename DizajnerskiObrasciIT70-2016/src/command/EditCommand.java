package command;

import java.io.Serializable;

import model.Shape;
import model.Model;

public class EditCommand implements ShapeCommand, Serializable {

	private Shape shape;
	private Shape edited;
	private Model shapes;
	
	public EditCommand(Shape shape, Shape edited, Model shapes) {
		this.shape = shape;
		this.shapes = shapes;
		this.edited = edited;
	}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		shapes.editShape(shape, edited);
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		shapes.editShape(edited, shape);
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


	public Shape getEdited() {
		return edited;
	}


	public void setEdited(Shape edited) {
		this.edited = edited;
	}


	@Override
	public String toString() {
		return "EditCommand-" + edited.toString();
	}
	
	

}
