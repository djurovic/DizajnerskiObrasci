package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import view.MainFrame;

public class BtnColorBorderActionListener implements ActionListener{
	
	private MainFrame mf;

	
		public BtnColorBorderActionListener(MainFrame mf) {
			this.mf=mf;
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JColorChooser jcc = new JColorChooser();
		Color colorChoise = jcc.showDialog(null, "Choose color", Color.black);
		if (colorChoise != null) {
			mf.getBtnBorderColor().setBackground(colorChoise);
		}
		
	}

}
