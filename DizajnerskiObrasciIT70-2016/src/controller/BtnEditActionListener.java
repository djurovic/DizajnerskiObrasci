package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import command.DrawCommand;
import model.Circle;
import model.HexagonAdapter;
import model.Line;
import model.Point;
import model.Rectangle;
import model.Shape;
import model.Square;
import view.DlgAddModifyCircle;
import view.DlgAddModifyHexagon;
import view.DlgAddModifyRectangle;
import view.DlgAddModifySquare;
import view.DlgModifyLine;
import view.DlgModifyPoint;
import view.MainFrame;

public class BtnEditActionListener implements ActionListener {

	private MainFrame mf;
	
	public BtnEditActionListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Shape selected = mf.getCanvas().getShapes().getSelectedShapes().get(0);
		
		switch (selected.getClass().getName()) {
			case "model.Point":
				DlgModifyPoint dp = new DlgModifyPoint(mf, (Point)selected);
				dp.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "model.Line":
				DlgModifyLine dl = new DlgModifyLine(mf, (Line)selected);
				dl.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "model.Square":
				DlgAddModifySquare ds = new DlgAddModifySquare(mf, (Square)selected);
				ds.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "model.Rectangle":
				DlgAddModifyRectangle dr = new DlgAddModifyRectangle(mf, (Rectangle)selected);
				dr.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "model.Circle":
				DlgAddModifyCircle dc = new DlgAddModifyCircle(mf, (Circle)selected);
				dc.setVisible(true);
				mf.clearClickedLocations();
				break;
			case "model.HexagonAdapter":
				DlgAddModifyHexagon dh = new DlgAddModifyHexagon(mf, (HexagonAdapter)selected);
				dh.setVisible(true);
				mf.clearClickedLocations();
				break;	
		}
	}

}
