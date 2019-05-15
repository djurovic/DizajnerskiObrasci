package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import command.DrawCommand;
import command.EditCommand;
import model.Square;
import model.Shape;
import model.Circle;
import model.Point;
import model.Rectangle;

public class DlgAddModifySquare extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public int x;
	public int y;
	public int side;
	public Color edgeColor;
	public Color fillColor;

	private MainFrame mf;
	public boolean stop = true;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtSideLenght;
	private JTextField txtEdgeColor;
	private JTextField txtFillColor;
	public int pomoc4=0;
	public boolean pomoc=false;

	private Square square;


	/**
	 * Create the dialog.
	 */
	public DlgAddModifySquare(MainFrame mf, Square s) {
		this.square = s;
		this.mf = mf;
		setModal(true);

		setTitle("Djurovic Stefan IT 70-2016");
		setBounds(100, 100, 467, 294);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{1, 424, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 1, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		if (square != null) {
			{
				JLabel lblX = new JLabel("X:");
				lblX.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.fill = GridBagConstraints.BOTH;
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 0;
				gbc_lblX.gridy = 0;
				contentPanel.add(lblX, gbc_lblX);
			}
			{
				txtX = new JTextField();
				txtX.setText(String.valueOf(square.getUpperLeft().getX()));
				txtX.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtX = new GridBagConstraints();
				gbc_txtX.anchor = GridBagConstraints.WEST;
				gbc_txtX.fill = GridBagConstraints.VERTICAL;
				gbc_txtX.insets = new Insets(0, 0, 5, 0);
				gbc_txtX.gridx = 1;
				gbc_txtX.gridy = 0;
				contentPanel.add(txtX, gbc_txtX);
				txtX.setColumns(10);
			}
			{
			}
			{
				JLabel lblY = new JLabel("Y:");
				lblY.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.fill = GridBagConstraints.BOTH;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 0;
				gbc_lblY.gridy = 1;
				contentPanel.add(lblY, gbc_lblY);
			}
			{
				txtY = new JTextField();
				txtY.setText(String.valueOf(square.getUpperLeft().getY()));
				txtY.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtY = new GridBagConstraints();
				gbc_txtY.anchor = GridBagConstraints.WEST;
				gbc_txtY.fill = GridBagConstraints.VERTICAL;
				gbc_txtY.insets = new Insets(0, 0, 5, 0);
				gbc_txtY.gridx = 1;
				gbc_txtY.gridy = 1;
				contentPanel.add(txtY, gbc_txtY);
				txtY.setColumns(10);
			}
		}
		{
			JLabel lblSideLenght = new JLabel("Side lenght");
			lblSideLenght.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblSideLenght = new GridBagConstraints();
			gbc_lblSideLenght.fill = GridBagConstraints.BOTH;
			gbc_lblSideLenght.insets = new Insets(0, 0, 5, 5);
			gbc_lblSideLenght.gridx = 0;
			gbc_lblSideLenght.gridy = 2;
			contentPanel.add(lblSideLenght, gbc_lblSideLenght);
		}
		{
			txtSideLenght = new JTextField();
			if (square != null) {
				txtSideLenght.setText(String.valueOf(square.getSide()));
			}
			txtSideLenght.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_txtSideLenght = new GridBagConstraints();
			gbc_txtSideLenght.anchor = GridBagConstraints.WEST;
			gbc_txtSideLenght.fill = GridBagConstraints.VERTICAL;
			gbc_txtSideLenght.insets = new Insets(0, 0, 5, 0);
			gbc_txtSideLenght.gridx = 1;
			gbc_txtSideLenght.gridy = 2;
			contentPanel.add(txtSideLenght, gbc_txtSideLenght);
			txtSideLenght.setColumns(10);
		}

		if (square != null) {
			{
				txtEdgeColor = new JTextField();
				txtEdgeColor.setBackground(square.getColor());
				txtEdgeColor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						edgeColor = JColorChooser.showDialog(mf, "Choose edge color", Color.black);
						if (edgeColor != null)
							txtEdgeColor.setBackground(edgeColor);
					}
				});
				{
					JLabel lblEdgeColor = new JLabel("Edge color");
					lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
					GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
					gbc_lblEdgeColor.fill = GridBagConstraints.BOTH;
					gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
					gbc_lblEdgeColor.gridx = 0;
					gbc_lblEdgeColor.gridy = 3;
					contentPanel.add(lblEdgeColor, gbc_lblEdgeColor);
				}
				txtEdgeColor.setEditable(false);
				txtEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtEdgeColor = new GridBagConstraints();
				gbc_txtEdgeColor.anchor = GridBagConstraints.WEST;
				gbc_txtEdgeColor.fill = GridBagConstraints.VERTICAL;
				gbc_txtEdgeColor.insets = new Insets(0, 0, 5, 0);
				gbc_txtEdgeColor.gridx = 1;
				gbc_txtEdgeColor.gridy = 3;
				contentPanel.add(txtEdgeColor, gbc_txtEdgeColor);
				txtEdgeColor.setColumns(10);
			}
		}

		if (square != null) {
			{
				JLabel lblFillColor = new JLabel("Fill color");
				lblFillColor.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblFillColor = new GridBagConstraints();
				gbc_lblFillColor.fill = GridBagConstraints.BOTH;
				gbc_lblFillColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblFillColor.gridx = 0;
				gbc_lblFillColor.gridy = 4;
				contentPanel.add(lblFillColor, gbc_lblFillColor);
			}
			txtFillColor = new JTextField();
			txtFillColor.setBackground(square.getColorFill());
			txtFillColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					fillColor = JColorChooser.showDialog(mf, "Choose fill color", Color.white);
					if (fillColor != null)
						txtFillColor.setBackground(fillColor);
				}
			});
			txtFillColor.setEditable(false);
			GridBagConstraints gbc_txtFillColor = new GridBagConstraints();
			gbc_txtFillColor.insets = new Insets(0, 0, 5, 0);
			gbc_txtFillColor.anchor = GridBagConstraints.NORTHWEST;
			gbc_txtFillColor.gridx = 1;
			gbc_txtFillColor.gridy = 4;
			contentPanel.add(txtFillColor, gbc_txtFillColor);
			txtFillColor.setColumns(10);

		}

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[]{144, 144, 144, 0};
				gbl_buttonPane.rowHeights = new int[]{23, 0};
				gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				buttonPane.setLayout(gbl_buttonPane);
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pomoc=true;
							setVisible(false);
						}
					});
					JButton btnAccept = new JButton("Accept");
					btnAccept.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {

							if (square != null) {
								try{
									//									
									x=Integer.parseInt(txtX.getText());
									y=Integer.parseInt(txtY.getText());
									side=Integer.parseInt(txtSideLenght.getText());
									edgeColor=txtEdgeColor.getBackground();
									fillColor=txtFillColor.getBackground();

									Square edited = new Square(new Point(x, y), side, edgeColor, fillColor);
									edited.setName(square.getName());
									edited.setSelected(true);
									
									EditCommand cmd = new EditCommand(square, edited, mf.getCanvas().getShapes());
									mf.getCmdManager().doCommand(cmd);
//									mf.logCommand(cmd.toString());
									mf.getCanvas().repaint();
									
									setVisible(false);

								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "You didn't eneter number!", "Warning", JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								try {
									side=Integer.parseInt(txtSideLenght.getText());
									if (side <= 0)
									{

										JOptionPane.showMessageDialog(null, "Side lenght must be positive number");
									}
									else
									{
										setVisible(false);
									}
									

								} catch (Exception e2) {


									JOptionPane.showMessageDialog(null, "You must enter a number!");


								}
														
								Shape s = new Square(mf.getClickedLocation(), side, mf.getBtnBorderColor().getBackground(), mf.getBtnFillColor().getBackground());
								s.setName("Shape" + mf.getShapeNameCounter());
								mf.incrementShapeNameCounter();
								DrawCommand cmd = new DrawCommand(s, mf.getCanvas().getShapes());
								mf.getCmdManager().doCommand(cmd);
//								mf.logCommand(cmd.toString());
								mf.getCanvas().repaint();
							}
							mf.clearClickedLocations();

						}
					});
					btnAccept.setActionCommand("OK");
					GridBagConstraints gbc_btnAccept = new GridBagConstraints();
					gbc_btnAccept.fill = GridBagConstraints.VERTICAL;
					gbc_btnAccept.insets = new Insets(0, 0, 0, 5);
					gbc_btnAccept.gridx = 0;
					gbc_btnAccept.gridy = 0;
					buttonPane.add(btnAccept, gbc_btnAccept);
					getRootPane().setDefaultButton(btnAccept);



					btnCancel.setActionCommand("Cancel");
					GridBagConstraints gbc_btnCancel = new GridBagConstraints();
					gbc_btnCancel.fill = GridBagConstraints.VERTICAL;
					gbc_btnCancel.gridx = 2;
					gbc_btnCancel.gridy = 0;
					buttonPane.add(btnCancel, gbc_btnCancel);
				}
			}
		}

	}

}
