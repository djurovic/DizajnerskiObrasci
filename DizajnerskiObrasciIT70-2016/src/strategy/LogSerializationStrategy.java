package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import command.BringToBackCommand;
import command.BringToFrontCommand;
import command.DeleteCommand;
import command.DrawCommand;
import command.EditCommand;
import command.ShapeCommand;
import command.ToBackCommand;
import command.ToFrontCommand;
import model.Circle;
import model.HexagonAdapter;
import model.Line;
import model.Point;
import model.Rectangle;
import model.Shape;
import model.Square;
import view.MainFrame;

public class LogSerializationStrategy implements Strategy {

	private MainFrame mf;

	public LogSerializationStrategy(MainFrame mf) {
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
				BufferedReader br = new BufferedReader(new FileReader(selectedFile)); 
				  
				String line; 
				while ((line = br.readLine()) != null) {
					
					String command = line.split("-")[0];					
					
					switch (command) {
						case "DrawCommand":
							String shape = line.split("-")[1];
							
							Shape s = null;
							DrawCommand cmd = null;
							
							switch (shape) {
								case "Circle":
									s = Circle.fromString(line.split("-")[2]);
									break;
								case "HexagonAdapter":
									s = HexagonAdapter.fromString(line.split("-")[2]);
									break;
								case "Line":
									s = Line.fromString(line.split("-")[2]);
									break;
								case "Point":
									s = Point.fromString(line.split("-")[2]);
									break;
								case "Rectangle":
									s = Rectangle.fromString(line.split("-")[2]);
									break;
								case "Square":
									s = Square.fromString(line.split("-")[2]);
									break;
							}
							
							int reply = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply == JOptionPane.YES_OPTION) {
				            	cmd = new DrawCommand(s, mf.getCanvas().getShapes());							
								mf.getCmdManager().doCommand(cmd);
								mf.incrementShapeNameCounter();
								mf.getCanvas().repaint();
				            }
				            break;
						case "EditCommand":
							String shape1 = line.split("-")[1];
							
							Shape s1 = null;
							EditCommand cmd1 = null;
							
							switch (shape1) {
								case "Circle":
									s1 = Circle.fromString(line.split("-")[2]);
									break;
								case "HexagonAdapter":
									s1 = HexagonAdapter.fromString(line.split("-")[2]);
									break;
								case "Line":
									s1 = Line.fromString(line.split("-")[2]);
									break;
								case "Point":
									s1 = Point.fromString(line.split("-")[2]);
									break;
								case "Rectangle":
									s1 = Rectangle.fromString(line.split("-")[2]);
									break;
								case "Square":
									s1 = Square.fromString(line.split("-")[2]);
									break;
							}
							
							int reply1 = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply1 == JOptionPane.YES_OPTION) {
				            	cmd1 = new EditCommand(getShapeByName(s1.getName()), s1, mf.getCanvas().getShapes());					
								mf.getCmdManager().doCommand(cmd1);
								mf.getCanvas().repaint();
				            }
				            break;
						case "BringToBackCommand":
							String shapeName = line.split("-")[1].split("=")[1];
							
							Shape s2 = getShapeByName(shapeName);
							BringToBackCommand cmd2 = null;
							
							int reply2 = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply2 == JOptionPane.YES_OPTION) {
				            	cmd2 = new BringToBackCommand(mf.getCanvas().getShapes(), s2);;					
								mf.getCmdManager().doCommand(cmd2);
								mf.getCanvas().repaint();
				            }
				            break;
						case "BringToFrontCommand":
							String shapeName1 = line.split("-")[1].split("=")[1];
							
							Shape s3 = getShapeByName(shapeName1);
							BringToFrontCommand cmd3 = null;
							
							int reply3 = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply3 == JOptionPane.YES_OPTION) {
				            	cmd3 = new BringToFrontCommand(mf.getCanvas().getShapes(), s3);;					
								mf.getCmdManager().doCommand(cmd3);
								mf.getCanvas().repaint();
				            }
				            break;
						case "ToFrontCommand":
							String shapeName2 = line.split("-")[1].split("=")[1];
							
							Shape s4 = getShapeByName(shapeName2);
							ToFrontCommand cmd4 = null;
							
							int reply4 = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply4 == JOptionPane.YES_OPTION) {
				            	cmd4 = new ToFrontCommand(mf.getCanvas().getShapes(), s4);;					
								mf.getCmdManager().doCommand(cmd4);
								mf.getCanvas().repaint();
				            }
				            break;
						case "ToBackCommand":
							String shapeName3 = line.split("-")[1].split("=")[1];
							
							Shape s5 = getShapeByName(shapeName3);
							ToBackCommand cmd5 = null;
							
							int reply5 = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply5 == JOptionPane.YES_OPTION) {
				            	cmd5 = new ToBackCommand(mf.getCanvas().getShapes(), s5);;					
								mf.getCmdManager().doCommand(cmd5);
								mf.getCanvas().repaint();
				            }
				            break;
						case "DeleteCommand":
							String shapeNames = line.split("-")[1].split("=")[1];
							
							String[] shapeNamesList = shapeNames.substring(1, shapeNames.length()-1).split(","); 
							
							ArrayList<Shape> shapes = new ArrayList<Shape>();
							
							for (String name : shapeNamesList) {
								shapes.add(getShapeByName(name));
							}
							DeleteCommand cmd6 = null;
							
							int reply6 = JOptionPane.showConfirmDialog(mf, line, "Confirm command", JOptionPane.YES_NO_OPTION);
				            if (reply6 == JOptionPane.YES_OPTION) {
				            	cmd6 = new DeleteCommand(shapes, mf.getCanvas().getShapes());;					
								mf.getCmdManager().doCommand(cmd6);
								mf.getCanvas().repaint();
				            }
				            break;
						case "Undo":
							mf.getCmdManager().undo();
							mf.getCanvas().repaint();
							break;
						case "Redo":
							mf.getCmdManager().redo();
							mf.getCanvas().repaint();
							break;
					}	
					
					
				}
			  	
			} catch (IOException i) {
		         i.printStackTrace();
		         return;
		    }
		}
		
		
	}

	@Override
	public void writeShapes() {

		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		File selectedFile;

		int returnValue = jfc.showSaveDialog(mf);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();

			String fileName = selectedFile.getName();

			String newFilePath = selectedFile.getAbsolutePath();

			try {
			FileWriter writer = new FileWriter(newFilePath); 
			
			for (String cmd: mf.getCommandLog()) {
				writer.write(cmd+System.getProperty( "line.separator" ));
			}
			
			writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	private Shape getShapeByName(String name) {
		// TODO Auto-generated method stub
		for (Shape s : mf.getCanvas().getShapes().getShapes()) {
			if (s.getName().equals(name)) {
				return s; 
			}
		}
		return null;
	}

}
