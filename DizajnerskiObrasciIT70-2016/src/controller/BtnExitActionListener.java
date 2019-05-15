package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class BtnExitActionListener implements ActionListener {
	
	private MainFrame mf;

	public BtnExitActionListener(MainFrame mf) {
		this.mf=mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		mf.setVisible(false);
	}

}
