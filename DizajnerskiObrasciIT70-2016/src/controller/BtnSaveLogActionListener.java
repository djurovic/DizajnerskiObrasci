package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import strategy.LogSerializationStrategy;
import view.MainFrame;

public class BtnSaveLogActionListener implements ActionListener{
	private MainFrame mf;

	public BtnSaveLogActionListener(MainFrame mf) {
		super();
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		mf.setLoadStrategy(new LogSerializationStrategy(mf));
		
		if (mf.getCanvas().getShapes().getShapes().size() > 0) {
			mf.getLoadStrategy().writeShapes();
		} else {
			JOptionPane.showMessageDialog(null, "There are no shapes to serialize", "Warning",
    		        JOptionPane.WARNING_MESSAGE);
		}
		
	}
}
