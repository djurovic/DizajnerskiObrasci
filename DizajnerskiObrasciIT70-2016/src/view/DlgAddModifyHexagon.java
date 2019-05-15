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
import hexagon.Hexagon;
import model.Circle;
import model.HexagonAdapter;
import model.Shape;

public class DlgAddModifyHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtColorBorder;
	private JTextField txtColorFill;
	public int x;
	public int y;
	public int radius;
	public Color colorBorder;
	public Color colorFill;

	private JFrame mainFrame;
	public boolean stop = true;
	public int pomoc4 = 0;
	public boolean pomoc = false;

	private MainFrame mf;
	private HexagonAdapter hex;

	/**
	 * Create the dialog.
	 */
	public DlgAddModifyHexagon(MainFrame mf, HexagonAdapter h) {
		this.hex = h;
		this.mf = mf;

		setModal(true);
		setTitle("Djurovic Stefan IT 70-2016");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 136, 263, 0 };
		gbl_contentPanel.rowHeights = new int[] { 22, 22, 22, 22, 22, 22, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		if (hex != null) {
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
				if (hex != null) {
					txtX.setText(String.valueOf(hex.getHexagon().getX()));
				}
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
				if (hex != null) {
					txtY.setText(String.valueOf(hex.getHexagon().getY()));
				}
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
			JLabel lblRaduius = new JLabel("Radius");
			lblRaduius.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblRaduius = new GridBagConstraints();
			gbc_lblRaduius.fill = GridBagConstraints.BOTH;
			gbc_lblRaduius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRaduius.gridx = 0;
			gbc_lblRaduius.gridy = 2;
			contentPanel.add(lblRaduius, gbc_lblRaduius);
		}
		{
			{
				txtRadius = new JTextField();
				if (hex != null) {
					txtRadius.setText(String.valueOf(hex.getHexagon().getR()));
				}
				txtRadius.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtRadius = new GridBagConstraints();
				gbc_txtRadius.anchor = GridBagConstraints.WEST;
				gbc_txtRadius.fill = GridBagConstraints.VERTICAL;
				gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
				gbc_txtRadius.gridx = 1;
				gbc_txtRadius.gridy = 2;
				contentPanel.add(txtRadius, gbc_txtRadius);
				txtRadius.setColumns(10);
			}
			if (hex != null) {
				{
					JLabel lblColorBorder = new JLabel("Edge color");
					lblColorBorder.setHorizontalAlignment(SwingConstants.CENTER);
					GridBagConstraints gbc_lblColorBorder = new GridBagConstraints();
					gbc_lblColorBorder.fill = GridBagConstraints.BOTH;
					gbc_lblColorBorder.insets = new Insets(0, 0, 5, 5);
					gbc_lblColorBorder.gridx = 0;
					gbc_lblColorBorder.gridy = 3;
					contentPanel.add(lblColorBorder, gbc_lblColorBorder);
				}
			}

		}

		if (hex != null) {
			{
				txtColorBorder = new JTextField();
				txtColorBorder.setBackground(hex.getHexagon().getBorderColor());
				txtColorBorder.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						colorBorder = JColorChooser.showDialog(mainFrame, "Choose edge color", Color.black);
						if (colorBorder != null)
							txtColorBorder.setBackground(colorBorder);
					}
				});
				txtColorBorder.setEditable(false);
				txtColorBorder.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtColorBorder = new GridBagConstraints();
				gbc_txtColorBorder.anchor = GridBagConstraints.WEST;
				gbc_txtColorBorder.fill = GridBagConstraints.VERTICAL;
				gbc_txtColorBorder.insets = new Insets(0, 0, 5, 0);
				gbc_txtColorBorder.gridx = 1;
				gbc_txtColorBorder.gridy = 3;
				contentPanel.add(txtColorBorder, gbc_txtColorBorder);
				txtColorBorder.setColumns(10);
				{
					JLabel lblColorFill = new JLabel("Fill color");
					lblColorFill.setHorizontalAlignment(SwingConstants.CENTER);
					GridBagConstraints gbc_lblColorFill = new GridBagConstraints();
					gbc_lblColorFill.fill = GridBagConstraints.BOTH;
					gbc_lblColorFill.insets = new Insets(0, 0, 5, 5);
					gbc_lblColorFill.gridx = 0;
					gbc_lblColorFill.gridy = 4;
					contentPanel.add(lblColorFill, gbc_lblColorFill);
				}
			}
		}

		if (hex != null) {
			txtColorFill = new JTextField();
			txtColorFill.setBackground(hex.getHexagon().getAreaColor());
			txtColorFill.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					colorFill = JColorChooser.showDialog(mainFrame, "Choose fill color", Color.white);
					if (colorFill != null)
						txtColorFill.setBackground(colorFill);
				}
			});
			txtColorFill.setEditable(false);
			txtColorFill.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_txtColorFill = new GridBagConstraints();
			gbc_txtColorFill.anchor = GridBagConstraints.WEST;
			gbc_txtColorFill.insets = new Insets(0, 0, 5, 0);
			gbc_txtColorFill.fill = GridBagConstraints.VERTICAL;
			gbc_txtColorFill.gridx = 1;
			gbc_txtColorFill.gridy = 4;
			contentPanel.add(txtColorFill, gbc_txtColorFill);
			txtColorFill.setColumns(10);
		}

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[] { 144, 144, 144, 0 };
				gbl_buttonPane.rowHeights = new int[] { 23, 0 };
				gbl_buttonPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_buttonPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
				buttonPane.setLayout(gbl_buttonPane);
			}
			{
				JButton btnAccept = new JButton("Accept");
				btnAccept.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (hex != null) {
							try {

								x = Integer.parseInt(txtX.getText());
								y = Integer.parseInt(txtY.getText());
								radius = Integer.parseInt(txtRadius.getText());
								colorBorder = txtColorBorder.getBackground();
								colorFill = txtColorFill.getBackground();
								
								Hexagon h = new Hexagon(x, y, radius);
								HexagonAdapter edited = new HexagonAdapter(h, colorFill, colorBorder);
								edited.setSelected(true);
								edited.setName(hex.getName());
								
								EditCommand cmd = new EditCommand(hex, edited, mf.getCanvas().getShapes());
								mf.getCmdManager().doCommand(cmd);
//								mf.logCommand(cmd.toString());
								mf.getCanvas().repaint();
								setVisible(false);
							} catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(null, "You didn't enter number!", "Warning",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							try{
								radius=Integer.parseInt(txtRadius.getText());

								if (radius <= 0)
								{

									JOptionPane.showMessageDialog(null, "Radius must be positive number");
								}
								else
								{

									setVisible(false);
								}
								Hexagon h = new Hexagon(mf.getClickedLocation().getX(), mf.getClickedLocation().getY(), radius);
								Shape s = new HexagonAdapter(h, mf.getBtnFillColor().getBackground(), mf.getBtnBorderColor().getBackground());
								s.setName("Shape" + mf.getShapeNameCounter());
								mf.incrementShapeNameCounter();
								DrawCommand cmd = new DrawCommand(s, mf.getCanvas().getShapes());
								mf.getCmdManager().doCommand(cmd);
//								mf.logCommand(cmd.toString());
								mf.getCanvas().repaint();

							} catch (Exception e1) {


								JOptionPane.showMessageDialog(null, "You didn't enter number!");

							}
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
							pomoc = true;
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
