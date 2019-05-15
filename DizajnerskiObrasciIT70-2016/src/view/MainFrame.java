package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import command.CommandManager;
import command.ShapeCommand;
import controller.BtnBringToBackActionListener;
import controller.BtnBringToFrontActionListener;
import controller.BtnColorBorderActionListener;
import controller.BtnColorFillActionListener;
import controller.BtnDeleteActionListener;
import controller.BtnEditActionListener;
import controller.BtnExitActionListener;
import controller.BtnLoadLogActionListener;
import controller.BtnLoadObjectActionListener;
import controller.BtnRedoActionListener;
import controller.BtnSaveLogActionListener;
import controller.BtnSaveObjectActionListener;
import controller.BtnToBackActionListener;
import controller.BtnToFrontActionListener;
import controller.BtnUndoActionListener;
import controller.CanvasMouseAdapter;
import controller.CmbChoosenShapeItemListener;
import hexagon.Hexagon;
import model.Circle;
import model.HexagonAdapter;
import model.Line;
import model.Point;
import model.Rectangle;
import model.Shape;
import model.Model;
import model.Square;
import strategy.ObjectSerializationStrategy;
import strategy.Strategy;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.awt.SystemColor;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;

public class MainFrame extends JFrame implements Observer {

	private Point point;
	private Line line;
	private Square square;
	private Rectangle rectangle;
	private Circle circle;
	private HexagonAdapter hexagon;

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	private Point clickedLocation;
	private Point clickedLocationEnd;

	private Color fillColor = Color.DARK_GRAY;
	private Color borderColor = Color.BLACK;	
	
	private int shapeNameCounter = 1;

	private String selected = "Select";
	private int selectedCount = 0;
	private boolean selectMode = true;

	private CanvasPanel canvas;
	private CommandManager cmdManager;
	private ArrayList<String> commandLog;
	
	private Strategy saveStrategy;
	private Strategy loadStrategy;

	private JPanel contentPane;
	
	private JButton btnLoadShapes;
	
	private JComboBox comboBox;
	
	private JMenuItem btnBringToBack;
	private JMenuItem btnToBack;
	private JMenuItem btnToFront;
	private JMenuItem btnBringToFront;
	
	private JButton btnFillColor;
	private JButton btnBorderColor;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnLoadObject;
	private JButton btnSaveObject;
	private JButton btnSaveLog;
	private JScrollPane scrollPane;
	private JPanel logPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @return
	 */
	public MainFrame() {
		setTitle("Djurovic Stefan IT 70-2016");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		cmdManager = new CommandManager(this);
		commandLog = new ArrayList<String>();

		JPanel toolbar = new JPanel();
		contentPane.add(toolbar, BorderLayout.NORTH);
		GridBagLayout gbl_toolbar = new GridBagLayout();
		gbl_toolbar.columnWidths = new int[] { 198, 0, 0, 0, 0, 57, 28, 0, 0, 0, 0, 0, 0, 0 };
		gbl_toolbar.rowHeights = new int[] { 22, 0 };
		gbl_toolbar.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_toolbar.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		toolbar.setLayout(gbl_toolbar);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Select", "Point", "Line", "Square", "Rectangle", "Circle", "Hexagon" }));
		comboBox.addItemListener(new CmbChoosenShapeItemListener(this));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		toolbar.add(comboBox, gbc_comboBox);

		btnFillColor = new JButton("Fill color");
		btnFillColor.setBackground(fillColor);
		btnFillColor.addActionListener(new BtnColorFillActionListener(this));
				
				btnLoadShapes = new JButton("Load log");
				btnLoadShapes.addActionListener(new BtnLoadLogActionListener(this));
				btnSaveObject = new JButton("Save object");
				btnSaveObject.addActionListener(new BtnSaveObjectActionListener(this));
				GridBagConstraints gbc_btnSaveObject = new GridBagConstraints();
				gbc_btnSaveObject.insets = new Insets(0, 0, 0, 5);
				gbc_btnSaveObject.gridx = 1;
				gbc_btnSaveObject.gridy = 0;
				toolbar.add(btnSaveObject, gbc_btnSaveObject);
				
				btnSaveLog = new JButton("Save log");
				btnSaveLog.addActionListener(new BtnSaveLogActionListener(this));
				GridBagConstraints gbc_btnSaveLog = new GridBagConstraints();
				gbc_btnSaveLog.insets = new Insets(0, 0, 0, 5);
				gbc_btnSaveLog.gridx = 2;
				gbc_btnSaveLog.gridy = 0;
				toolbar.add(btnSaveLog, gbc_btnSaveLog);
				
				btnLoadObject = new JButton("Load object");
				btnLoadObject.addActionListener(new BtnLoadObjectActionListener(this));
				GridBagConstraints gbc_btnLoadObject = new GridBagConstraints();
				gbc_btnLoadObject.insets = new Insets(0, 0, 0, 5);
				gbc_btnLoadObject.gridx = 3;
				gbc_btnLoadObject.gridy = 0;
				toolbar.add(btnLoadObject, gbc_btnLoadObject);
				
				GridBagConstraints gbc_btnLoadShapes = new GridBagConstraints();
				gbc_btnLoadShapes.insets = new Insets(0, 0, 0, 5);
				gbc_btnLoadShapes.gridx = 4;
				gbc_btnLoadShapes.gridy = 0;
				toolbar.add(btnLoadShapes, gbc_btnLoadShapes);
				
				btnEdit = new JButton("Edit");
				btnEdit.setEnabled(false);
				btnEdit.addActionListener(new BtnEditActionListener(this));
				GridBagConstraints gbc_btnEdit = new GridBagConstraints();
				gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
				gbc_btnEdit.gridx = 5;
				gbc_btnEdit.gridy = 0;
				toolbar.add(btnEdit, gbc_btnEdit);
				
				btnDelete = new JButton("Delete");
				btnDelete.setEnabled(false);
				btnDelete.addActionListener(new BtnDeleteActionListener(this));
				GridBagConstraints gbc_btnDelete = new GridBagConstraints();
				gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
				gbc_btnDelete.gridx = 6;
				gbc_btnDelete.gridy = 0;
				toolbar.add(btnDelete, gbc_btnDelete);
				{
					JMenuBar positionBar = new JMenuBar();		
					JMenu btnPosition = new JMenu("Position");
					
					btnBringToBack = new JMenuItem("Bring to Back");
					btnBringToBack.addActionListener(new BtnBringToBackActionListener(this));
					btnBringToBack.setEnabled(false);
					btnPosition.add(btnBringToBack);
					
					btnToBack = new JMenuItem("To Back");
					btnToBack.addActionListener(new BtnToBackActionListener(this));
					btnToBack.setEnabled(false);
					btnPosition.add(btnToBack);
					
					btnToFront = new JMenuItem("To Front");
					btnToFront.addActionListener(new BtnToFrontActionListener(this));
					btnToFront.setEnabled(false);
					btnPosition.add(btnToFront);
					
					btnBringToFront = new JMenuItem("Bring to Front");
					btnBringToFront.addActionListener(new BtnBringToFrontActionListener(this));
					btnBringToFront.setEnabled(false);
					btnPosition.add(btnBringToFront);
					
					
					positionBar.add(btnPosition);				
					
					GridBagConstraints gbc_position = new GridBagConstraints();
					gbc_position.insets = new Insets(0, 0, 0, 5);
					gbc_position.gridx = 7;
					gbc_position.gridy = 0;
					toolbar.add(positionBar, gbc_position);
				}

		GridBagConstraints gbc_btnFillColor = new GridBagConstraints();
		gbc_btnFillColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnFillColor.gridx = 8;
		gbc_btnFillColor.gridy = 0;
		toolbar.add(btnFillColor, gbc_btnFillColor);

		btnBorderColor = new JButton("Border Color");
		btnBorderColor.setBackground(borderColor);
		btnBorderColor.addActionListener(new BtnColorBorderActionListener(this));


		GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
		gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnBorderColor.gridx = 9;
		gbc_btnBorderColor.gridy = 0;
		toolbar.add(btnBorderColor, gbc_btnBorderColor);
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new BtnUndoActionListener(this));
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.insets = new Insets(0, 0, 0, 5);
		gbc_btnUndo.gridx = 11;
		gbc_btnUndo.gridy = 0;
		toolbar.add(btnUndo, gbc_btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new BtnRedoActionListener(this));
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.gridx = 12;
		gbc_btnRedo.gridy = 0;
		toolbar.add(btnRedo, gbc_btnRedo);

		canvas = new CanvasPanel();
		canvas.getShapes().addObserver(this);
		
		canvas.addMouseListener(new CanvasMouseAdapter(this));		
		
		canvas.setBackground(Color.WHITE);
		contentPane.add(canvas, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(0, 150));
		contentPane.add(scrollPane, BorderLayout.SOUTH);
		
		logPanel = new JPanel();
		scrollPane.setViewportView(logPanel);
		logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));

		
	}

	public JButton getBtnFillColor() {
		return btnFillColor;
	}

	public void setBtnFillColor(JButton btnFillColor) {
		this.btnFillColor = btnFillColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		switch(canvas.getShapes().selectedCount()) {
			case 0:
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);

				btnBringToBack.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
				btnBringToFront.setEnabled(false);
				break;
			case 1:
				btnEdit.setEnabled(true);
				btnDelete.setEnabled(true);

				btnBringToBack.setEnabled(true);
				btnToBack.setEnabled(true);
				btnToFront.setEnabled(true);
				btnBringToFront.setEnabled(true);
				break;
			case 2:
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(true);

				btnBringToBack.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
				btnBringToFront.setEnabled(false);
				break;
		}
		
		if (cmdManager.canUndo()) {
			btnUndo.setEnabled(true);
		} else {
			btnUndo.setEnabled(false);
		}
		
		if (cmdManager.canRedo()) {
			btnRedo.setEnabled(true);
		} else {
			btnRedo.setEnabled(false);
		}
		
	}
	
	public void logCommand(String command) {
		JLabel cmd = new JLabel(command);
		logPanel.add(cmd);
		commandLog.add(command);
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setValue( sb.getMaximum() );		
		validate();
	    repaint();
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public CanvasPanel getCanvas() {
		return canvas;
	}

	public void setCanvas(CanvasPanel canvas) {
		this.canvas = canvas;
	}

	public Point getClickedLocation() {
		return clickedLocation;
	}

	public void setClickedLocation(Point clickedLocation) {
		this.clickedLocation = clickedLocation;
	}

	public Point getClickedLocationEnd() {
		return clickedLocationEnd;
	}

	public void setClickedLocationEnd(Point clickedLocationEnd) {
		this.clickedLocationEnd = clickedLocationEnd;
	}
	
	public void clearClickedLocations() {
		setClickedLocation(null);
		setClickedLocationEnd(null);
	}

	public CommandManager getCmdManager() {
		return cmdManager;
	}

	public void setCmdManager(CommandManager cmdManager) {
		this.cmdManager = cmdManager;
	}

	public boolean isSelectMode() {
		return selectMode;
	}

	public void setSelectMode(boolean selectMode) {
		this.selectMode = selectMode;
	}

	public Strategy getSaveStrategy() {
		return saveStrategy;
	}

	public void setSaveStrategy(Strategy saveStrategy) {
		this.saveStrategy = saveStrategy;
	}

	public Strategy getLoadStrategy() {
		return loadStrategy;
	}

	public void setLoadStrategy(Strategy loadStrategy) {
		this.loadStrategy = loadStrategy;
	}

	public JPanel getLogPanel() {
		return logPanel;
	}

	public void setLogPanel(JPanel logPanel) {
		this.logPanel = logPanel;
	}

	public ArrayList<String> getCommandLog() {
		return commandLog;
	}

	public void setCommandLog(ArrayList<String> commandLog) {
		this.commandLog = commandLog;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public int getShapeNameCounter() {
		return shapeNameCounter;
	}

	public void setShapeNameCounter(int shapeNameCounter) {
		this.shapeNameCounter = shapeNameCounter;
	}
	
	public void incrementShapeNameCounter() {
		this.shapeNameCounter += 1;
	}

}
