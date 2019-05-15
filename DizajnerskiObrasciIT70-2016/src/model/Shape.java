package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Observable;

public abstract class Shape extends Observable implements Movable, Comparable, Serializable  {
	
	private String colorString = "black";
	private boolean selected;
	private boolean deleted;
	private Color color;
	private String name;
	
	public Shape() {
	
	}
	
	public Shape(String name) {
		super();
		this.name = name;
		this.selected = false;
	}

	public static Color getColor(String colorString) {
		if(colorString.equalsIgnoreCase("blue"))
			return Color.BLUE;
		else if(colorString.equalsIgnoreCase("red"))
			return Color.RED;
		else if(colorString.equalsIgnoreCase("green"))
			return Color.GREEN;
		else if(colorString.equalsIgnoreCase("yellow"))
			return Color.YELLOW;
		else if(colorString.equalsIgnoreCase("pink"))
			return Color.PINK;
		else if(colorString.equalsIgnoreCase("white"))
			return Color.WHITE;
		else
			return Color.BLACK;
	}
	
	public abstract void draw(Graphics g);
	public abstract void select(Graphics g);
	public abstract boolean contains(int x, int y);

	public String getColorString() {
		return colorString;
	}

	public void setColorString(String colorString) {
		this.colorString = colorString;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		notifyObservers();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	
	
}
