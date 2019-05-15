package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.MainFrame;

public class CmbChoosenShapeItemListener implements ItemListener {

	private MainFrame mf;
	
	public CmbChoosenShapeItemListener(MainFrame mf) {
		// TODO Auto-generated constructor stub
		this.mf = mf;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		String selectedItem = mf.getComboBox().getSelectedItem().toString();
		if (selectedItem == "Select") {
			mf.setSelectMode(true);
		} else {
			mf.setSelectMode(false);
			mf.getCanvas().getShapes().clearSelection();
			mf.getBtnEdit().setEnabled(false);
			mf.getBtnDelete().setEnabled(false);
			mf.getCanvas().repaint();
		}
		mf.setSelected(selectedItem);
	}

}
