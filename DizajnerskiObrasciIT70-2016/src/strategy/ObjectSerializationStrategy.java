package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.CommandManager;
import command.DeleteCommand;
import command.DrawCommand;
import command.EditCommand;
import command.ShapeCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import model.Shape;
import model.Model;
import view.MainFrame;

public class ObjectSerializationStrategy implements Strategy {
	
	private MainFrame mf;
	
	public ObjectSerializationStrategy(MainFrame mf) {
		this.mf = mf;
	}

	@Override
	public void readShapes() {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		File selectedFile;

		int returnValue = jfc.showOpenDialog(mf);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			try {
		         FileInputStream fileIn = new FileInputStream(selectedFile.getAbsolutePath());
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         
		         CommandManager manager = (CommandManager) in.readObject();
		         mf.setCmdManager(new CommandManager());
		         mf.getCanvas().setShapes(new Model());
		         
		         for (ShapeCommand cmd: manager.getCommands()) {		        	 
		        	 if (cmd instanceof DrawCommand) {
		        		 DrawCommand c = new DrawCommand(((DrawCommand) cmd).getShape(), mf.getCanvas().getShapes());
		        		 mf.getCmdManager().doCommand(c);
		        	 } else if (cmd instanceof BringToBackCommand) {
		        		 BringToBackCommand c = new BringToBackCommand(mf.getCanvas().getShapes(), ((BringToBackCommand) cmd).getShape());
		        		 mf.getCmdManager().doCommand(c);
		        	 } else if (cmd instanceof BringToFrontCommand) {
		        		 BringToFrontCommand c = new BringToFrontCommand(mf.getCanvas().getShapes(), ((BringToFrontCommand) cmd).getShape());
		        		 mf.getCmdManager().doCommand(c);
		        	 } else if (cmd instanceof ToFrontCommand) {
		        		 ToFrontCommand c = new ToFrontCommand(mf.getCanvas().getShapes(), ((ToFrontCommand) cmd).getShape());
		        		 mf.getCmdManager().doCommand(c);
		        	 } else if (cmd instanceof ToBackCommand) {
		        		 ToBackCommand c = new ToBackCommand(mf.getCanvas().getShapes(), ((ToBackCommand) cmd).getShape());
		        		 mf.getCmdManager().doCommand(c);
		        	 } else if (cmd instanceof EditCommand) {
		        		 EditCommand c = new EditCommand(((EditCommand) cmd).getShape(), ((EditCommand)cmd).getEdited(), mf.getCanvas().getShapes());
		        		 mf.getCmdManager().doCommand(c);
		        	 } else if (cmd instanceof DeleteCommand) {
//		        		 DeleteCommand c = new DeleteCommand(((DeleteCommand) cmd).getShape(), mf.getCanvas().getShapes());
//		        		 mf.getCmdManager().doCommand(c);		        		 
		        		 DeleteCommand c = new DeleteCommand(((DeleteCommand) cmd).getShapeList(), mf.getCanvas().getShapes());
		        		 mf.getCmdManager().doCommand(c);
		        		 
		        	 }		
		        	 
		        	 mf.getCanvas().repaint();
		        	 
//		        	 int reply = JOptionPane.showConfirmDialog(mf, "Accept command?", "Confirm command", JOptionPane.YES_NO_OPTION);
//		             if (reply == JOptionPane.NO_OPTION) {
//		            	 mf.getCmdManager().undo();
//		             }
		        	 
		         }
		         mf.getCanvas().repaint();
		         
		         in.close();
		         fileIn.close();
		      } catch (IOException i) {
		         i.printStackTrace();
		         return;
		      } catch (ClassNotFoundException c) {
		         c.printStackTrace();
		         return;
		      }
		}
	}

	@Override
	public void writeShapes() {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		File selectedFile;

		int returnValue = jfc.showSaveDialog(mf);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			
			String fileName = selectedFile.getName();
			
			String newFilePath = selectedFile.getAbsolutePath();
			try {
		         FileOutputStream fileOut =
		         new FileOutputStream(newFilePath);
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(mf.getCmdManager());
		         out.close();
		         fileOut.close();
		         System.out.println("Serialized successfully");
		      } catch (IOException i) {
		         i.printStackTrace();
		      } 
		}
		
		
	}

}
