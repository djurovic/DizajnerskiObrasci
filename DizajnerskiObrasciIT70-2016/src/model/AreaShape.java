package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class AreaShape extends Shape implements Serializable {
	private String colorFillString = "white";
	private Color colorFill;
	
	public abstract double area();
	public abstract double circumference();
	
	public abstract void fill(Graphics g);
	public String getColorFillString() {
		return colorFillString;
	}
	public void setColorFillString(String colorFillString) {
		this.colorFillString = colorFillString;
	}
	public Color getColorFill() {
		return colorFill;
	}
	public void setColorFill(Color colorFill) {
		this.colorFill = colorFill;
	}
	
	@Override
	public String toString() {
		return "AreaShape [colorFillString=" + colorFillString + ", colorFill=" + colorFill + "]";
	}
	
	
	
	
}
