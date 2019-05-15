package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends AreaShape implements Serializable {
	
	private Point center;
	private int r;
	
	public Circle() {
		
	}	

	public Circle(Point center, int r) {
		super();
		this.center = center;
		this.r = r;
	}
	
	public Circle(Point center, int r, String color) {
		this(center, r);
		setColorString(color);
	}
	
	public Circle(Point center, int r, Color colorFill, Color colorBorder) {
		this(center, r);
		setColor(colorBorder);
		setColorFill(colorFill);
	}



	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		center.moveTo(x, y);
	}

	@Override
	public void moveFor(int posX, int posY) {
		// TODO Auto-generated method stub
		center.moveFor(posX, posY);
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Circle){
			return (int) (this.r-((Circle) o).r);
		}
		else 
			return 0;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return r*r*Math.PI;
	}

	@Override
	public double circumference() {
		// TODO Auto-generated method stub
		double circumference;
		circumference = 2*r*Math.PI;
		return circumference;
	}

	@Override
	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor(getColorFillString()));
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, 2*r-2);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor((getColor()));
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, 2*r);
		g.setColor(getColorFill());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, 2*r-2);
		if(isSelected())
			select(g);
	}

	@Override
	public void select(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-r), 
				new Point(center.getX(), center.getY() + r)).select(g);
		new Line(new Point(center.getX()-r, center.getY()), 
				new Point(center.getX()+r, center.getY())).select(g);
	}

	public boolean contains(int x, int y) {
		if(new Point(x, y).distance(getCenter()) <= r)
			return true;
		else
			return false;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	@Override
	public String toString() {
		return "Circle-name=>"+ getName() + ";x=>" + center.getX() + ";y=>" + center.getY() + ";r=>" + r + 
				";fill=>" + super.getColorFill().toString() + ";border=>" + super.getColor().toString();
	}
	
	public static Circle fromString(String str) {
		String[] props = str.split(";");
		
		Circle shape = new Circle();
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
		shape.setCenter(p);
		shape.setR(rad);
		shape.setColor(border);
		shape.setColorFill(fill);
		
		return shape;		
	}

	
}
