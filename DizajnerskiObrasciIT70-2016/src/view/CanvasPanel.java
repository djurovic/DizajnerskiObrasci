package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import command.ShapeCommand;
import model.Shape;
import model.Model;

public class CanvasPanel extends JPanel {
	
	Model shapes;
	Shape selected;
	
	public CanvasPanel() {
		shapes = new Model();
	}
	
	

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		draw(g);
	}



	public void draw(Graphics g) {
		for(Shape s: shapes.getShapes()) {
			s.draw(g);
		}
	}



	public Shape getSelected() {
		return selected;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
		
	}


	   public void addShape(Shape s) {
		   
	   }
	   

	public Model getShapes() {
		return shapes;
	}

	public void setShapes(Model shapes) {
		this.shapes = shapes;
	}
	   
	 
	
	

}
