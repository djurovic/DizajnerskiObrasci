package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import strategy.LogSerializationStrategy;
import strategy.ObjectSerializationStrategy;
import view.MainFrame;

public class BtnLoadLogActionListener implements ActionListener{

MainFrame mf;
	
	public BtnLoadLogActionListener(MainFrame mf) {
		this.mf = mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		mf.setLoadStrategy(new LogSerializationStrategy(mf));		
		mf.getLoadStrategy().readShapes();
		
	}
	
}
