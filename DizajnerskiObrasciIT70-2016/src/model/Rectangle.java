package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class Rectangle extends Square implements Serializable {
	private int width;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeft, int width, int height) {
		this.upperLeft = upperLeft;
		this.width = width;
		side = height;
	}
	
	public Rectangle(Point upperLeft, int width, int height, Color color,Color colorFill) {
		this(upperLeft, width, height);
		setColor(color);
		setColorFill(colorFill);
	}
	
	public double area() {
		return width * side;
	}
	public double circumference() {
		return 2*width + 2*side;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Rectangle) {
			Rectangle params = (Rectangle) obj;
			if(upperLeft.equals(params.upperLeft) && 
					side == params.side && 
							width == params.width)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor(getColorFillString()));
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, side-1, width-1);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), side, width);
		g.setColor(getColorFill());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, side-1, width-1);
		if (isSelected())
			select(g);
	}

	@Override
	public void select(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+side, getUpperLeft().getY())).select(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+width)).select(g);
		new Line(new Point(getUpperLeft().getX()+side, getUpperLeft().getY()), diagonal().getEnd()).select(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+width), diagonal().getEnd()).select(g);
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + width)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + side))
			return true;
		else 
			return false;
	}

	@Override
	public Line diagonal() {
		// TODO Auto-generated method stub
		Point lowerRight = new Point(getUpperLeft().getX()+side, getUpperLeft().getY()+width);
		return new Line(upperLeft, lowerRight);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "Rectangle-name=>"+ getName() + ";x=>" + upperLeft.getX() + ";y=>" + upperLeft.getY() + ";width=>" + width + ";height=>" + String.valueOf(super.getSide()) + 
				";border=>" + getColor() + ";fill=>" + getColorFill();
	}
	
	public static Rectangle fromString(String str) {
		String[] props = str.split(";");
		
		Rectangle shape = new Rectangle();
		int x = 0;
		int y = 0;
		int rad = 0;
		int width = 0;
		int side = 0;
		Color fill = Color.LIGHT_GRAY;
		Color border = Color.BLACK;
		
		for (String prop : props) {
			String key = prop.split("=>")[0];
			String value = prop.split("=>")[1];
			
			switch (key) {
				case "name":
					shape.setName(value);
					break;
				case "x":
					x = Integer.parseInt(value);
					break;
				case "y":
					y = Integer.parseInt(value);
					break;
				case "r":
					rad = Integer.parseInt(value);
					break;
				case "width":
					width = Integer.parseInt(value);
					break;
				case "height":
					side = Integer.parseInt(value);
					break;
				case "fill":
					String[] rgb = value.substring(14, value.length()-1).split(",");
					int r = Integer.parseInt(rgb[0].split("=")[1]);
					int g = Integer.parseInt(rgb[1].split("=")[1]);
					int b = Integer.parseInt(rgb[2].split("=")[1]);
					fill = new Color(r, g, b);
					break;					
				case "border":
					String[] rgb1 = value.substring(14, value.length()-1).split(",");
					int r1 = Integer.parseInt(rgb1[0].split("=")[1]);
					int g1 = Integer.parseInt(rgb1[1].split("=")[1]);
					int b1 = Integer.parseInt(rgb1[2].split("=")[1]);
					border = new Color(r1, g1, b1);
					break;				
			}
		}
		
		Point p = new Point(x, y, border);
		shape.setUpperLeft(p);
		shape.setWidth(width);
		shape.setSide(side);
		shape.setColor(border);
		shape.setColorFill(fill);
		
		return shape;	
	}
	
	
}
