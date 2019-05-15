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

import command.EditCommand;
import model.Shape;
import model.Point;
import model.Rectangle;

public class DlgModifyPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtColor;
	public int x;
	public int y;
	public Color color;
	public Color color1;
	
	private MainFrame mf;
	public boolean stop = true;
	public int pomoc4=0;
	public boolean pomoc=false;
	
	public Point p;
	
	/**
	 * Create the dialog.
	 */
	public DlgModifyPoint(MainFrame mf, Point p) {
		this.mf = mf;
		this.p = p;
		setModal(true);
		setTitle("Djurovic Stefan IT 70-2016");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{83, 0, 258, 0};
		gbl_contentPanel.rowHeights = new int[]{28, 28, 28, 28, 28, 28, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
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
				txtX.setText(String.valueOf(p.getX()));
				txtX.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtX = new GridBagConstraints();
				gbc_txtX.anchor = GridBagConstraints.WEST;
				gbc_txtX.fill = GridBagConstraints.VERTICAL;
				gbc_txtX.insets = new Insets(0, 0, 5, 0);
				gbc_txtX.gridx = 2;
				gbc_txtX.gridy = 0;
				contentPanel.add(txtX, gbc_txtX);
				txtX.setColumns(10);
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
				txtY.setText(String.valueOf(p.getY()));
				txtY.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtY = new GridBagConstraints();
				gbc_txtY.anchor = GridBagConstraints.WEST;
				gbc_txtY.fill = GridBagConstraints.VERTICAL;
				gbc_txtY.insets = new Insets(0, 0, 5, 0);
				gbc_txtY.gridx = 2;
				gbc_txtY.gridy = 1;
				contentPanel.add(txtY, gbc_txtY);
				txtY.setColumns(10);
			}
			{
				JLabel lblColor = new JLabel("Color");
				lblColor.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblColor = new GridBagConstraints();
				gbc_lblColor.fill = GridBagConstraints.BOTH;
				gbc_lblColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblColor.gridx = 0;
				gbc_lblColor.gridy = 2;
				contentPanel.add(lblColor, gbc_lblColor);
			}
		}
		txtColor = new JTextField();
		txtColor.setBackground(p.getColor());
		txtColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				color = JColorChooser.showDialog(mf, "Choose point color", Color.black);
				if (color != null)
					txtColor.setBackground(color);
			}
		});
		txtColor.setEditable(false);
		txtColor.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtColor = new GridBagConstraints();
		gbc_txtColor.anchor = GridBagConstraints.WEST;
		gbc_txtColor.insets = new Insets(0, 0, 5, 0);
		gbc_txtColor.fill = GridBagConstraints.VERTICAL;
		gbc_txtColor.gridx = 2;
		gbc_txtColor.gridy = 2;
		contentPanel.add(txtColor, gbc_txtColor);
		txtColor.setColumns(10);
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

					JButton btnAccept = new JButton("Accept");
					btnAccept.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
						
							x=Integer.parseInt(txtX.getText());
							y=Integer.parseInt(txtY.getText());
							color1=txtColor.getBackground();
							
							Point edited = new Point(x, y, color1);
							edited.setSelected(true);
							edited.setName(p.getName());
							
							EditCommand cmd = new EditCommand(p, edited, mf.getCanvas().getShapes());
							mf.getCmdManager().doCommand(cmd);
//							mf.logCommand(cmd.toString());
							mf.getCanvas().repaint();
							
							setVisible(false);
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "You didn't enter number!", "Warning", JOptionPane.INFORMATION_MESSAGE);
						}
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
					
				}
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
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
