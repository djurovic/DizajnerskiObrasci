package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import command.BringToBackCommand;
import command.ToFrontCommand;
import model.Shape;
import view.MainFrame;

public class BtnToFrontActionListener implements ActionListener {

private MainFrame mf;
	
	public BtnToFrontActionListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Shape selected = mf.getCanvas().getShapes().getSelectedShapes().get(0);
		
		ToFrontCommand cmd = new ToFrontCommand(mf.getCanvas().getShapes(), selected);
		mf.getCmdManager().doCommand(cmd);
//		mf.logCommand(cmd.toString());
		mf.getCanvas().repaint();
	}

}
