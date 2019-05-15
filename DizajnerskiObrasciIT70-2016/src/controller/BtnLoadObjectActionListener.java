package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import strategy.ObjectSerializationStrategy;
import view.MainFrame;

public class BtnLoadObjectActionListener implements ActionListener {
	
	MainFrame mf;
	
	public BtnLoadObjectActionListener(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		mf.setLoadStrategy(new ObjectSerializationStrategy(mf));		
		mf.getLoadStrategy().readShapes();
	}
	
	

}
