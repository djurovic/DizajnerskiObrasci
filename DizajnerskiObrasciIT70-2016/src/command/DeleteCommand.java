package command;

import java.io.Serializable;
import java.util.ArrayList;

import model.Shape;
import model.Model;

public class DeleteCommand implements ShapeCommand, Serializable {
	
//	private Shape shape;
	private ArrayList<Shape> shapeList;
	private Model shapes;
//	private DeleteCommand prevCmd;
//	private DeleteCommand nextCmd;
	
//	public DeleteCommand(Shape shape, Model shapes) {
//		this.shape = shape;
//		this.shapes = shapes;
//	}

	public DeleteCommand(ArrayList<Shape> shapeList, Model shapes) {
		this.shapeList = shapeList;
		this.shapes = shapes;
	}
	
//	public DeleteCommand(Shape shape, Model shapes, DeleteCommand prevCmd) {
//		this.shape = shape;
//		this.shapes = shapes;
//		this.prevCmd = prevCmd;
//	}
//	
//	public DeleteCommand(Shape shape, Model shapes, DeleteCommand prevCmd, DeleteCommand nextCommand) {
//		this.shape = shape;
//		this.shapes = shapes;
//		this.prevCmd = prevCmd;
//		this.nextCmd = nextCommand;
//	}
	

//	@Override
//	public void execute() {
//		// TODO Auto-generated method stub
//		shapes.removeShape(shape);
//	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for (Shape s: shapeList) {
			shapes.removeShape(s);
		}
		
	}

//	@Override
//	public void unexecute() {
//		// TODO Auto-generated method stub
//		shapes.addShape(shape);		
//	}
	
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		for (Shape s: shapeList) {
			shapes.addShape(s);
		}
	}


	public Model getShapes() {
		return shapes;
	}


	public void setShapes(Model shapes) {
		this.shapes = shapes;
	}


//	public Shape getShape() {
//		return shape;
//	}
//
//
//	public void setShape(Shape shape) {
//		this.shape = shape;
//	}
//
//	public DeleteCommand getPrevCmd() {
//		return prevCmd;
//	}
//
//	public void setPrevCmd(DeleteCommand prevCmd) {
//		this.prevCmd = prevCmd;
//	}
//
//	public DeleteCommand getNextCmd() {
//		return nextCmd;
//	}
//
//	public void setNextCmd(DeleteCommand nextCmd) {
//		this.nextCmd = nextCmd;
//	}

//	@Override
//	public String toString() {
//		return "DeleteCommand [shape=" + shape.toString() + "]";
//	}
	
	
	
	@Override
	public String toString() {
		String list = "";
		for (Shape s : shapeList) {
			list += s.getName() + ",";
		}
		return "DeleteCommand-shapes=[" + list.substring(0, list.length()-1) + "]";
	}

	public ArrayList<Shape> getShapeList() {
		return shapeList;
	}

	public void setShapeList(ArrayList<Shape> shapeList) {
		this.shapeList = shapeList;
	}
	
}
