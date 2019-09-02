package controller;

import model.Line;
import model.Point;
import model.Shape;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;

import command.DrawCommand;
import command.SelectCommand;
import view.CanvasPanel;
import view.DlgAddModifyCircle;
import view.DlgAddModifyHexagon;
import view.DlgAddModifyRectangle;
import view.DlgModifyLine;
import view.DlgAddModifySquare;
import view.MainFrame;

public class CanvasMouseAdapter extends MouseAdapter {
	
	MainFrame mf;
	
	public CanvasMouseAdapter(MainFrame mf) {
		this.mf=mf;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
//		super.mouseClicked(e);
		if (mf.isSelectMode()) {
			selectAction(e);
		} else {
			drawAction(e);
		}
		
	}
	
	public void drawAction(MouseEvent e) {
		if (mf.getClickedLocation() != null) {
			// ako je vec selektovana prva lokacija, na klik cemo zapamtiti drugu lokaciju
			mf.setClickedLocationEnd(new Point(e.getX(), e.getY(), mf.getBtnBorderColor().getBackground()));
		} else {
			// u suprotnom ako prva lokacija nije selektovana, selektuje se prva lokacija
			mf.setClickedLocation(new Point(e.getX(), e.getY(), mf.getBtnBorderColor().getBackground()));
		}
		
		
		switch (mf.getSelected()) {
			case "Point":
				Color c = null;
				if (mf.getBtnFillColor().getBackground().toString().equals("javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]")) {
					c = Color.BLACK;
				} else {
					c = mf.getBtnFillColor().getBackground();
				}
				Shape s = new Point(mf.getClickedLocation().getX(), mf.getClickedLocation().getY(), c);
				s.setName("Shape" + mf.getShapeNameCounter());
				mf.incrementShapeNameCounter();
				DrawCommand cmd = new DrawCommand(s, mf.getCanvas().getShapes());
//				mf.logCommand(cmd.toString());
				mf.getCmdManager().doCommand(cmd);
				mf.getCanvas().repaint();
				mf.clearClickedLocations();
				break;
			case "Line":
				if (mf.getClickedLocationEnd() != null) {
					Color c1 = null;
					if (mf.getBtnFillColor().getBackground().toString().equals("javax.swing.plaf.ColorUIResource[r=238,g=238,b=238]")) {
						c1 = Color.BLACK;
					} else {
						c1 = mf.getBtnFillColor().getBackground();
					}
					
					Shape s1 = new Line(mf.getClickedLocation(), mf.getClickedLocationEnd(), c1);
					s1.setName("Shape" + mf.getShapeNameCounter());
					mf.incrementShapeNameCounter();
					DrawCommand cmd1 = new DrawCommand(s1, mf.getCanvas().getShapes());
					mf.getCmdManager().doCommand(cmd1);
//					mf.logCommand(cmd1.toString());
					mf.getCanvas().repaint();
					mf.clearClickedLocations();
				}
				break;
			case "Square":
				DlgAddModifySquare d3 = new DlgAddModifySquare(mf, null);
				d3.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "Rectangle":
				DlgAddModifyRectangle d4 = new DlgAddModifyRectangle(mf, null);
				d4.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "Circle":
				DlgAddModifyCircle d5 = new DlgAddModifyCircle(mf, null);
				d5.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "Hexagon":
				DlgAddModifyHexagon d6 = new DlgAddModifyHexagon(mf, null);
				d6.setVisible(true);
				mf.clearClickedLocations();
				break;	
		}
	}
	
	public void selectAction(MouseEvent e) {
		// ako ostane -1 na kraju izvrsavanja fora, to znaci da ne postoji element na poziciji klika
		int selectedShapeIndex = -1;
		Shape selected = null;
		
		int index = 0;
		for (Shape s: mf.getCanvas().getShapes().getShapes()) {
			if (s.contains(e.getX(), e.getY())) {
				selected = s;
				selectedShapeIndex = index;
			}
			index++;
		}
		
		if (selectedShapeIndex != -1) {
			
			SelectCommand cmd = new SelectCommand(selected, mf.getCanvas().getShapes(), selectedShapeIndex);
			mf.getCmdManager().doCommand(cmd);
			
//			mf.getCanvas().getShapes().toggleSelectShape(selectedShapeIndex);
		}
		
		
		mf.getCanvas().repaint();
	}
	
	
	
}
