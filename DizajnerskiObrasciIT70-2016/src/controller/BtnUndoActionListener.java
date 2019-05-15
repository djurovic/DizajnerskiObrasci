package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class BtnUndoActionListener implements ActionListener {

	MainFrame mf;
	
	
	public BtnUndoActionListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf=mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mf.getCmdManager().undo();
//		mf.logCommand("Undo");
		mf.getCanvas().repaint();
	}

}
