package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class BtnRedoActionListener implements ActionListener {

	MainFrame mf;
	
	
	public BtnRedoActionListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf=mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mf.getCmdManager().redo();
//		mf.logCommand("Redo");
		mf.getCanvas().repaint();
	}

}
