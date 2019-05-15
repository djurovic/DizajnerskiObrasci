package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable implements Serializable{

	private List<Shape> shapes;
	
	public Model() {
		shapes = new ArrayList<Shape>();
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public void addShape(Shape s) {
		shapes.add(s);
		setChanged();
		notifyObservers();
	}
	
	public void addShape(int index, Shape s) {
		shapes.add(index, s);
		setChanged();
		notifyObservers();
	}
	
	public void removeShape(Shape s) {
		shapes.remove(s);
		setChanged();
		notifyObservers();
	}
	
	public void setShape(int index, Shape s) {
		shapes.set(index, s);
		setChanged();
		notifyObservers();
	}
	
	public void setRemovedFalse(int index) {
		shapes.get(index).setDeleted(false);
	}
	
	public void editShape(Shape s, Shape edited) {
		shapes.set(shapes.indexOf(s), edited);
		setChanged();
		notifyObservers();
	}
	
	public void toggleSelectShape(int index) {
		boolean currentState = shapes.get(index).isSelected();
		shapes.get(index).setSelected(!currentState);
		setChanged();
		notifyObservers();
	}
	
	public int selectedCount() {
		int count = 0;
		for (Shape s: shapes) {
			if (s.isSelected()) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<Shape> getSelectedShapes() {
		
		ArrayList<Shape> result = new ArrayList<Shape>();
		
		for (Shape s: shapes) {
			if (s.isSelected()) {
				result.add(s);
			}
		}
		return result;
	}
	
	public void clearSelection() {
		for (Shape s : shapes) {
			s.setSelected(false);
		}
	}
	
}
