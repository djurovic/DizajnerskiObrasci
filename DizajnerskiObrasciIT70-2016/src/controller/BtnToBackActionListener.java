package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import command.ToBackCommand;
import command.ToFrontCommand;
import model.Shape;
import view.MainFrame;

public class BtnToBackActionListener implements ActionListener {

private MainFrame mf;
	
	public BtnToBackActionListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Shape selected = mf.getCanvas().getShapes().getSelectedShapes().get(0);
		
		ToBackCommand cmd = new ToBackCommand(mf.getCanvas().getShapes(), selected);
		mf.getCmdManager().doCommand(cmd);
//		mf.logCommand(cmd.toString());
		mf.getCanvas().repaint();
	}

}
