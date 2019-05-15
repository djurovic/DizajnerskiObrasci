package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.Serializable;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Serializable {
	protected Hexagon hexagon;
	protected Polygon p;
	
	public HexagonAdapter(Hexagon h, Color fillColor, Color borderColor) {
		this.hexagon = h;
		this.hexagon.setAreaColor(fillColor);
		this.hexagon.setBorderColor(borderColor);
	}

	public HexagonAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		hexagon.setX(x);
		hexagon.setY(y);
	}

	@Override
	public void moveFor(int posX, int posY) {
		// TODO Auto-generated method stub
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		hexagon.paint(g);
	}

	@Override
	public void select(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		
		new Line(new Point(hexagon.getX(), hexagon.getY()-hexagon.getR()), 
				new Point(hexagon.getX(), hexagon.getY() + hexagon.getR())).select(g);
		new Line(new Point(hexagon.getX()-hexagon.getR(), hexagon.getY()), 
				new Point(hexagon.getX()+hexagon.getR(), hexagon.getY())).select(g);
		
	}
	
	
	
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	@Override
	public String toString() {
		return "HexagonAdapter-name=>"+ getName() + ";x=>" + String.valueOf(hexagon.getX()) + ";y=>" + String.valueOf(hexagon.getY()) + 
				";r=>" + String.valueOf(hexagon.getR()) + ";border=>" + hexagon.getBorderColor() + ";fill=>" + hexagon.getAreaColor();
	}

	@Override
	public void setSelected(boolean selected) {
		// TODO Auto-generated method stub
		super.setSelected(true);
		hexagon.setSelected(selected);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return hexagon.isSelected();
	}
	
	public static HexagonAdapter fromString(String str) {
		String[] props = str.split(";");
		
		HexagonAdapter shape = new HexagonAdapter();
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
		
		Hexagon hex = new Hexagon(x, y, rad);
		shape.setHexagon(hex);
		hex.setBorderColor(border);
		hex.setAreaColor(fill);
		shape.setColor(border);
		
		return shape;	
	}
	
	
}
