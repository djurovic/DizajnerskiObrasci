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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import model.Circle;
import model.Line;
import model.Shape;
import model.Point;
import model.Rectangle;

public class DlgModifyLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX1;
	private JTextField txtY1;
	private JTextField txtX2;
	private JTextField txtY2;
	private JTextField txtColor;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public Color color;
	public boolean stop = true;

	private MainFrame mf;
	public int pomoc4=0;
	public boolean pomoc=false;

	private Line line;

	/**
	 * Create the dialog.
	 */
	public DlgModifyLine(MainFrame mf, Line l) {
		this.line = l;
		this.mf = mf;
		setTitle("Djurovic Stefan IT 70-2016");
		setModal(true);
		setBounds(100, 100, 452, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 308, 0};
		gbl_contentPanel.rowHeights = new int[]{22, 22, 22, 22, 22, 22, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);

		if (line != null) {
			{
				JLabel lblX = new JLabel("X1:");
				lblX.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblX = new GridBagConstraints();
				gbc_lblX.gridwidth = 2;
				gbc_lblX.fill = GridBagConstraints.BOTH;
				gbc_lblX.insets = new Insets(0, 0, 5, 5);
				gbc_lblX.gridx = 0;
				gbc_lblX.gridy = 0;
				contentPanel.add(lblX, gbc_lblX);
			}
			{
				txtX1 = new JTextField();
				txtX1.setText(String.valueOf(line.getStart().getX()));
				txtX1.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtX1 = new GridBagConstraints();
				gbc_txtX1.anchor = GridBagConstraints.WEST;
				gbc_txtX1.fill = GridBagConstraints.VERTICAL;
				gbc_txtX1.insets = new Insets(0, 0, 5, 0);
				gbc_txtX1.gridx = 2;
				gbc_txtX1.gridy = 0;
				contentPanel.add(txtX1, gbc_txtX1);
				txtX1.setColumns(10);
			}
			{
				JLabel lblY = new JLabel("Y1:");
				lblY.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblY = new GridBagConstraints();
				gbc_lblY.gridwidth = 2;
				gbc_lblY.fill = GridBagConstraints.BOTH;
				gbc_lblY.insets = new Insets(0, 0, 5, 5);
				gbc_lblY.gridx = 0;
				gbc_lblY.gridy = 1;
				contentPanel.add(lblY, gbc_lblY);
			}
			{
				txtY1 = new JTextField();
				txtY1.setText(String.valueOf(line.getStart().getY()));
				txtY1.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtY1 = new GridBagConstraints();
				gbc_txtY1.anchor = GridBagConstraints.WEST;
				gbc_txtY1.fill = GridBagConstraints.VERTICAL;
				gbc_txtY1.insets = new Insets(0, 0, 5, 0);
				gbc_txtY1.gridx = 2;
				gbc_txtY1.gridy = 1;
				contentPanel.add(txtY1, gbc_txtY1);
				txtY1.setColumns(10);
			}
		{
			JLabel lblX2 = new JLabel("X2:");
			lblX2.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblX2 = new GridBagConstraints();
			gbc_lblX2.gridwidth = 2;
			gbc_lblX2.fill = GridBagConstraints.BOTH;
			gbc_lblX2.insets = new Insets(0, 0, 5, 5);
			gbc_lblX2.gridx = 0;
			gbc_lblX2.gridy = 2;
			contentPanel.add(lblX2, gbc_lblX2);
		}
		{
			txtX2 = new JTextField();
			txtX2.setText(String.valueOf(line.getEnd().getX()));			
			txtX2.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_txtX2 = new GridBagConstraints();
			gbc_txtX2.anchor = GridBagConstraints.WEST;
			gbc_txtX2.fill = GridBagConstraints.VERTICAL;
			gbc_txtX2.insets = new Insets(0, 0, 5, 0);
			gbc_txtX2.gridx = 2;
			gbc_txtX2.gridy = 2;
			contentPanel.add(txtX2, gbc_txtX2);
			txtX2.setColumns(10);
		}
		{
			JLabel lblY1 = new JLabel("Y2:");
			lblY1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblY1 = new GridBagConstraints();
			gbc_lblY1.gridwidth = 2;
			gbc_lblY1.fill = GridBagConstraints.BOTH;
			gbc_lblY1.insets = new Insets(0, 0, 5, 5);
			gbc_lblY1.gridx = 0;
			gbc_lblY1.gridy = 3;
			contentPanel.add(lblY1, gbc_lblY1);
		}
		{
			{
				txtY2 = new JTextField();
				txtY2.setText(String.valueOf(line.getEnd().getY()));				
				txtY2.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtY2 = new GridBagConstraints();
				gbc_txtY2.anchor = GridBagConstraints.NORTHWEST;
				gbc_txtY2.insets = new Insets(0, 0, 5, 0);
				gbc_txtY2.gridx = 2;
				gbc_txtY2.gridy = 3;
				contentPanel.add(txtY2, gbc_txtY2);
				txtY2.setColumns(10);
			}
			{
				JLabel lblColor = new JLabel("Color");
				lblColor.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblColor = new GridBagConstraints();
				gbc_lblColor.gridwidth = 2;
				gbc_lblColor.fill = GridBagConstraints.BOTH;
				gbc_lblColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblColor.gridx = 0;
				gbc_lblColor.gridy = 4;
				contentPanel.add(lblColor, gbc_lblColor);
			}
		}
			txtColor = new JTextField();
			txtColor.setBackground(line.getColor());
			txtColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					color = JColorChooser.showDialog(mf, "Choose line color", Color.black);
					if (color != null)
						txtColor.setBackground(color);
				}
			});
			txtColor.setEditable(false);
			GridBagConstraints gbc_txtColor = new GridBagConstraints();
			gbc_txtColor.anchor = GridBagConstraints.WEST;
			gbc_txtColor.insets = new Insets(0, 0, 5, 0);
			gbc_txtColor.fill = GridBagConstraints.VERTICAL;
			gbc_txtColor.gridx = 2;
			gbc_txtColor.gridy = 4;
			contentPanel.add(txtColor, gbc_txtColor);
			txtColor.setColumns(10);
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
			}
			{
				JButton btnAccept = new JButton("Accept");
				btnAccept.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						if (line !=null) {
							try{

								x1=Integer.parseInt(txtX1.getText());
								y1=Integer.parseInt(txtY1.getText());
								x2=Integer.parseInt(txtX2.getText());
								y2=Integer.parseInt(txtY2.getText());
								color=txtColor.getBackground();
								
								Line edited = new Line(new Point(x1, y1), new Point(x2, y2), color);
								edited.setSelected(true);
								edited.setName(line.getName());
								
								EditCommand cmd = new EditCommand(line, edited, mf.getCanvas().getShapes());
								mf.getCmdManager().doCommand(cmd);
//								mf.logCommand(cmd.toString());
								mf.getCanvas().repaint();
								
								setVisible(false);
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "You didn't enter number", "Warning", JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
//							Shape s = new Line(mf.getClickedLocation(), mf.getClickedLocationEnd(), mf.getBtnFillColor().getBackground());
//							DrawCommand cmd = new DrawCommand(s, mf.getCanvas().getShapes(), 0);
//							cmd.execute();
//							mf.getCanvas().repaint();
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
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							pomoc=true;
							setVisible(false);
						}
					});



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
