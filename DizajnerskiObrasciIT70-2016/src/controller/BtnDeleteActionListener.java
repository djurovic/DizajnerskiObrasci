package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import command.DeleteCommand;
import command.EditCommand;
import model.Shape;
import model.Model;
import view.MainFrame;

public class BtnDeleteActionListener implements ActionListener {
	
	private MainFrame mf;
	
	public BtnDeleteActionListener(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		int reply = JOptionPane.showConfirmDialog(mf, "Are you sure you want to delete element(s)?", "Delete confirmation", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
          
//        	DeleteCommand prevCmd = null;
//        	
//        	for (Shape s: mf.getCanvas().getShapes().getSelectedShapes()) {
//        		DeleteCommand cmd = new DeleteCommand(s, mf.getCanvas().getShapes(), prevCmd);
//        		prevCmd = cmd;
//				mf.getCmdManager().doCommand(cmd);
//				mf.logCommand(cmd.toString());
//				mf.getCanvas().repaint();
//  			}
        	
        	ArrayList<Shape> shapeList = new ArrayList<Shape>();
        	
        	for (Shape s: mf.getCanvas().getShapes().getSelectedShapes()) {
        		shapeList.add(s);
  			}
        	
        	DeleteCommand cmd = new DeleteCommand(shapeList, mf.getCanvas().getShapes());
			mf.getCmdManager().doCommand(cmd);
//			mf.logCommand(cmd.toString());
			mf.getCanvas().repaint();
        }
		
		
	}
	
	
}
