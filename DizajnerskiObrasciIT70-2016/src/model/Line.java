package model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import hexagon.Hexagon;

public class Line extends Shape implements Serializable {
	private Point start;
	private Point end;
	
	public Line() {
		
	}

	public Line(Point start, Point end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public Line(Point start, Point end, String color) {
		this(start, end);
		setColorString(color);
	}
	
	public Line(Point start, Point end, Color color) {
		this(start, end);
		setColor(color);
		this.start.setColor(color);
		this.end.setColor(color);
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		start.moveTo(x, y);
		end.moveTo(x, y);
	}

	@Override
	public void moveFor(int posX, int posY) {
		// TODO Auto-generated method stub
		start.moveFor(posX+start.getX(), posY+start.getY());
		end.moveFor(posX+end.getX(), posY+end.getY());
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Line){
			return (int) (this.length()-((Line) o).length());
		}
		else 
			return 0;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getColor());
		g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
		if(isSelected())
			select(g);
	}

	@Override
	public void select(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		start.select(g);
		end.select(g);
		lineCenter().select(g);
	}
	
	public double length() {		
		return start.distance(end);
	}
	
	public Point lineCenter(){
		int xCenter = (start.getX() + end.getX())/2;
		int yCenter = (start.getY() + end.getY())/2;
		Point lineCenter = new Point(xCenter, yCenter);
		return lineCenter;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Line) {
			Line param = (Line) obj;
			if(start.equals(param.start) && end.equals(param.end))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean contains(int x, int y){
		Point temp = new Point(x, y);
		if((start.distance(temp)+end.distance(temp))-length()<=1)
			return true;
		else
			return false;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Line-name=>"+ getName() + ";x1=>" + String.valueOf(start.getX()) + ";y1=>" + String.valueOf(start.getY()) + 
				";x2=>" + String.valueOf(end.getX()) + ";y2=>" + String.valueOf(end.getY()) + ";border=>" + start.getColor();
	}
	
	public static Line fromString(String str) {
		String[] props = str.split(";");
		
		Line shape = new Line();
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
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
				case "x1":
					x1 = Integer.parseInt(value);
					break;
				case "y1":
					y1 = Integer.parseInt(value);
					break;
				case "x2":
					x2 = Integer.parseInt(value);
					break;
				case "y2":
					y2 = Integer.parseInt(value);
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
		
		Point start = new Point(x1, y1, border);
		Point end = new Point(x2, y2, border);
		shape.setStart(start);
		shape.setEnd(end);
		shape.setColor(border);
		
		return shape;	
	}
	
}
