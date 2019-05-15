package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Square extends AreaShape implements Comparable, Serializable {
	
	protected Point upperLeft;
	protected int side;
	
	public Square() {
		
	}
	
	public Square (Point upperLeft, int side) {
		this.upperLeft = upperLeft;
		this.side = side;
	}
	
	public Square (Point upperLeft, int side, Color colorBorder, Color colorFill) {
		this(upperLeft, side);
		setColor(colorBorder);
		setColorFill(colorFill);
	}
	
	public Square(Point upperLeft, int side, String colorFillString, String colorBorderString) {
		this(upperLeft, side);
		setColorString(colorBorderString);
		setColorFillString(colorFillString);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		upperLeft.moveTo(x, y);
	}

	@Override
	public void moveFor(int posX, int posY) {
		// TODO Auto-generated method stub
		upperLeft.moveFor(posX, posY);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Square){
			return (int) (this.area()-((Square) o).area());
		}
		else 
			return 0;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return side*side;
	}

	@Override
	public double circumference() {
		// TODO Auto-generated method stub
		return 4*side;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor(getColorFillString()));
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, side-1, side-1);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), side, side);
		g.setColor(getColorFill());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, side-1, side-1);
		if (isSelected())
			select(g);
	}

	@Override
	public void select(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+side, getUpperLeft().getY())).select(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+side)).select(g);
		new Line(new Point(getUpperLeft().getX()+side, getUpperLeft().getY()), diagonal().getEnd()).select(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+side), diagonal().getEnd()).select(g);
	}
	
	public boolean contains(int x, int y) {
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + side)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + side))
			return true;
		else 
			return false;
	}
	
	public Line diagonal() {
		Point lowerRight = new Point(upperLeft.getX()+side, upperLeft.getY()+side);
		return new Line(upperLeft, lowerRight);
	}
	
	public Point center() {
		return diagonal().lineCenter();
	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "Square-name=>"+ getName() + ";x=>" + upperLeft.getX() + ";y=>" + upperLeft.getY() + ";side=>" + String.valueOf(getSide()) + 
				";border=>" + getColor() + ";fill=>" + getColorFill();
	}
	
	public static Square fromString(String str) {
		String[] props = str.split(";");
		
		Square shape = new Square();
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
				case "side":
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
		shape.setSide(side);
		shape.setColor(border);
		shape.setColorFill(fill);
		
		return shape;	
	}
	
}
