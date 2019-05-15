package command;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlTransient;

import view.MainFrame;

public class CommandManager implements Serializable {

	private List<ShapeCommand> commands;
	private MainFrame mf;
    private int nextPointer = 0;
	
	public CommandManager() {
		commands = new ArrayList<ShapeCommand>();
	}
	
	public CommandManager(MainFrame mf) {
		commands = new ArrayList<ShapeCommand>();
		this.mf = mf;
	}

    public void doCommand(ShapeCommand command) {
        List<ShapeCommand> newList = new ArrayList<>(nextPointer + 1);

        for(int k = 0; k < nextPointer; k++) {
            newList.add(commands.get(k));
        }

        newList.add(command);

        commands = newList;
        nextPointer++;

        command.execute();
        mf.logCommand(command.toString());
    }

    public boolean canUndo() {
        return nextPointer > 0;
    }

    public void undo() {
        if(canUndo()) {
            nextPointer--;
            ShapeCommand commandToUndo = commands.get(nextPointer);
            // Undo the command, or return it to whatever called this to be undone, or something
            commandToUndo.unexecute();
            
            mf.logCommand("Undo");
            
//            if (commandToUndo instanceof DeleteCommand) {
//	            DeleteCommand prevCmd = ((DeleteCommand) commandToUndo).getPrevCmd();
//	    		while (prevCmd != null) {
//	    			prevCmd.setNextCmd((DeleteCommand)commandToUndo);
//	    			nextPointer--;
//	    			prevCmd.unexecute();
//	    			prevCmd = prevCmd.getPrevCmd();
//	    			commandToUndo = prevCmd;
//	    		}	
//            }            
            
         } else {
        	 JOptionPane.showMessageDialog(null, "Can't undo", "Warning",
        		        JOptionPane.WARNING_MESSAGE);
         }
    }

    public boolean canRedo() {
        return nextPointer < commands.size();
    }

    public void redo() {
        if(canRedo()) {
            ShapeCommand commandToDo = commands.get(nextPointer);
            nextPointer++;
            // Do the command, or return it to whatever called this to be re-done, or something
            commandToDo.execute();
            
            
            mf.logCommand("Redo");
//            if (commandToDo instanceof DeleteCommand) {
//	            DeleteCommand prevCmd = ((DeleteCommand) commandToDo).getPrevCmd();
//	    		while (prevCmd != null) {
//	    			System.out.println("prev " + prevCmd.toString());
//	    			System.out.println("pointer " + String.valueOf(nextPointer));
//	    			nextPointer++;
//	    			prevCmd.execute();
//	    			prevCmd = prevCmd.getPrevCmd();
//	    		}	
//            } 
            
//            if (commandToDo instanceof DeleteCommand) {
//	            DeleteCommand nextCmd = ((DeleteCommand) commandToDo).getNextCmd();
//	    		while (nextCmd != null) {
//	    			nextPointer++;
//	    			nextCmd.execute();
//	    			nextCmd = nextCmd.getNextCmd();
//	    		}	
//            } 

        } else {
        	JOptionPane.showMessageDialog(null, "Can't redo", "Warning",
    		        JOptionPane.WARNING_MESSAGE);
        }
    }

	public List<ShapeCommand> getCommands() {
		return commands;
	}

	public void setCommands(List<ShapeCommand> commands) {
		this.commands = commands;
	}
	
	
}
