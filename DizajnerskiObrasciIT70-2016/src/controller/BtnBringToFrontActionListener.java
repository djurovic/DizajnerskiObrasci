package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import command.BringToBackCommand;
import command.BringToFrontCommand;
import model.Shape;
import view.MainFrame;

public class BtnBringToFrontActionListener implements ActionListener {

private MainFrame mf;
	
	public BtnBringToFrontActionListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Shape selected = mf.getCanvas().getShapes().getSelectedShapes().get(0);
		
		BringToFrontCommand cmd = new BringToFrontCommand(mf.getCanvas().getShapes(), selected);
		mf.getCmdManager().doCommand(cmd);
//		mf.logCommand(cmd.toString());
		mf.getCanvas().repaint();
	}

}
