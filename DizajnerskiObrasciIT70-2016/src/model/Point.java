package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class Point extends Shape implements Serializable {
	private int x;
	private int y;
	
	public Point() {
		
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, String color) {
		this(x, y);
		setColorString(color);
	}
	
	public Point(int x, int y, Color color) {
		this(x,y);
		setColor(color);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y+2, x, y-2);
		
		if(isSelected())
			select(g);
	}
	
	public double distance(Point p) {
		int dX = x - p.x;
		int dY = y - p.y;
		double d = Math.sqrt(dX*dX + dY*dY);
		return d;
	}

	@Override
	public void select(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3, 6, 6);
	}
	
	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		this.x = x;
		setY(y);
	}

	@Override
	public void moveFor(int posX, int posY) {
		// TODO Auto-generated method stub
		this.x = posX;
		setY(posY);
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Point) {
			Point pocetak = new Point(0,0);
			return (int) (((Point) o).distance(pocetak) - this.distance(pocetak));
		}
		else
			return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point param = (Point) obj;
			if (this.x == param.getX() && this.y == param.getY()) 
				return true;
			else 
				return false;
		}
		else
			return false;

	}
	
	public boolean contains(int x, int y) {
		if(new Point(x, y).distance(this) <= 2)
			return true;
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point-name=>"+ getName() + ";x=>" + x + ";y=>" + y + ";border=>" + getColor().toString();
	}
	
	public static Point fromString(String str) {
		String[] props = str.split(";");
		
		Point shape = new Point();
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
		
		shape.setX(x);
		shape.setY(y);
		shape.setColor(border);
		
		return shape;	
	}
}
