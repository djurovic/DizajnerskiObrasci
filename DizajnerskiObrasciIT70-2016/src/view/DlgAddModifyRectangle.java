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
import model.Shape;
import model.Square;
import model.Rectangle;
import model.Circle;
import model.Point;

public class DlgAddModifyRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtWidth;
	private JTextField txtSide;
	private JTextField txtEdgeColor;
	private JTextField txtFillColor;
	public int x;
	public int y;
	public int width;
	public int side;
	public Color edgeColor;
	public Color fillColor;

	private MainFrame mf;
	public boolean stop = true;
	public int pomoc4 = 0;
	// PnlCrtanje crtez = new PnlCrtanje();
	public boolean pomoc = false;

	private Rectangle rectangle;

	public DlgAddModifyRectangle(MainFrame mf, Rectangle r) {
		this.rectangle = r;
		this.mf = mf;

		setTitle("Djurovic Stefan IT 70-2016");
		setModal(true);
		setBounds(100, 100, 457, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 219, 0 };
		gbl_contentPanel.rowHeights = new int[] { 23, 19, 19, 19, 19, 19, 19, 19, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		if (r != null) {
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
				if (rectangle != null) {
					txtX.setText(String.valueOf(rectangle.getUpperLeft().getX()));
				}
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
				if (rectangle != null) {
					txtY.setText(String.valueOf(rectangle.getUpperLeft().getY()));
				}
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
		}
		{
			JLabel lblWidth = new JLabel("Widht");
			lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.fill = GridBagConstraints.BOTH;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 2;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			if (r != null) {
				txtWidth.setText(String.valueOf(r.getWidth()));
			}
			txtWidth.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.anchor = GridBagConstraints.WEST;
			gbc_txtWidth.fill = GridBagConstraints.VERTICAL;
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.gridx = 2;
			gbc_txtWidth.gridy = 2;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			{
				JLabel lblSide = new JLabel("Side");
				lblSide.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblSide = new GridBagConstraints();
				gbc_lblSide.fill = GridBagConstraints.BOTH;
				gbc_lblSide.insets = new Insets(0, 0, 5, 5);
				gbc_lblSide.gridx = 0;
				gbc_lblSide.gridy = 3;
				contentPanel.add(lblSide, gbc_lblSide);
			}
			{
				txtSide = new JTextField();
				if (r != null) {
					txtSide.setText(String.valueOf(r.getSide()));
				}
				txtSide.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtSide = new GridBagConstraints();
				gbc_txtSide.anchor = GridBagConstraints.WEST;
				gbc_txtSide.fill = GridBagConstraints.VERTICAL;
				gbc_txtSide.insets = new Insets(0, 0, 5, 0);
				gbc_txtSide.gridx = 2;
				gbc_txtSide.gridy = 3;
				contentPanel.add(txtSide, gbc_txtSide);
				txtSide.setColumns(10);
			}
			{
			}

			if (rectangle != null) {
				{
					JLabel lblEdgeColor = new JLabel("Edge color");
					lblEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
					GridBagConstraints gbc_lblEdgeColor = new GridBagConstraints();
					gbc_lblEdgeColor.fill = GridBagConstraints.BOTH;
					gbc_lblEdgeColor.insets = new Insets(0, 0, 5, 5);
					gbc_lblEdgeColor.gridx = 0;
					gbc_lblEdgeColor.gridy = 4;
					contentPanel.add(lblEdgeColor, gbc_lblEdgeColor);
				}
				txtEdgeColor = new JTextField();
				txtEdgeColor.setBackground(r.getColor());
				txtEdgeColor.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						edgeColor = JColorChooser.showDialog(mf, "Choose edge color", Color.black);
						if (edgeColor != null)
							txtEdgeColor.setBackground(edgeColor);
					}
				});
				txtEdgeColor.setEditable(false);
				txtEdgeColor.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_txtEdgeColor = new GridBagConstraints();
				gbc_txtEdgeColor.anchor = GridBagConstraints.WEST;
				gbc_txtEdgeColor.fill = GridBagConstraints.VERTICAL;
				gbc_txtEdgeColor.insets = new Insets(0, 0, 5, 0);
				gbc_txtEdgeColor.gridx = 2;
				gbc_txtEdgeColor.gridy = 4;
				contentPanel.add(txtEdgeColor, gbc_txtEdgeColor);
				txtEdgeColor.setColumns(10);
			
			{
				JLabel lblFillColor = new JLabel("Fill color");
				lblFillColor.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblFillColor = new GridBagConstraints();
				gbc_lblFillColor.fill = GridBagConstraints.BOTH;
				gbc_lblFillColor.insets = new Insets(0, 0, 5, 5);
				gbc_lblFillColor.gridx = 0;
				gbc_lblFillColor.gridy = 5;
				contentPanel.add(lblFillColor, gbc_lblFillColor);
			}
			txtFillColor = new JTextField();
			txtFillColor.setBackground(r.getColorFill());
			txtFillColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					fillColor = JColorChooser.showDialog(mf, "Choose fill color", Color.white);
					if (fillColor != null)
						txtFillColor.setBackground(fillColor);
				}
			});
			txtFillColor.setHorizontalAlignment(SwingConstants.CENTER);
			txtFillColor.setEditable(false);
			txtFillColor.setColumns(10);
			GridBagConstraints gbc_txtFillColor = new GridBagConstraints();
			gbc_txtFillColor.anchor = GridBagConstraints.WEST;
			gbc_txtFillColor.insets = new Insets(0, 0, 5, 0);
			gbc_txtFillColor.fill = GridBagConstraints.VERTICAL;
			gbc_txtFillColor.gridx = 2;
			gbc_txtFillColor.gridy = 5;
			contentPanel.add(txtFillColor, gbc_txtFillColor);
		}
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


					JButton btnAccept = new JButton("Accept");
					btnAccept.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							if (rectangle != null) {
								try {

									x = Integer.parseInt(txtX.getText());
									y = Integer.parseInt(txtY.getText());
									side = Integer.parseInt(txtSide.getText());
									width = Integer.parseInt(txtWidth.getText());
									edgeColor = txtEdgeColor.getBackground();
									fillColor = txtFillColor.getBackground();

									Rectangle edited = new Rectangle(new Point(x, y), width, side, edgeColor, fillColor);
									edited.setSelected(true);
									edited.setName(rectangle.getName());
									
									EditCommand cmd = new EditCommand(rectangle, edited, mf.getCanvas().getShapes());
									mf.getCmdManager().doCommand(cmd);
//									mf.logCommand(cmd.toString());
									mf.getCanvas().repaint();
									
									setVisible(false);
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "You didn't enter color", "Warning",
											JOptionPane.INFORMATION_MESSAGE);
								}
							} else {
								try {

									side = Integer.parseInt(txtSide.getText());
									width = Integer.parseInt(txtWidth.getText());

									if (side <= 0 || width <= 0)

									{

										JOptionPane.showMessageDialog(null, "Number must be positive!");
									} else {

										setVisible(false);
									}
									Shape s = new Rectangle(mf.getClickedLocation(), width, side, mf.getBtnBorderColor().getBackground(), mf.getBtnFillColor().getBackground());
									s.setName("Shape" + mf.getShapeNameCounter());
									mf.incrementShapeNameCounter();
									DrawCommand cmd = new DrawCommand(s, mf.getCanvas().getShapes());
									mf.getCmdManager().doCommand(cmd);
//									mf.logCommand(cmd.toString());
									mf.getCanvas().repaint();

								} catch (Exception e2) {

									JOptionPane.showMessageDialog(null, "You must enter number!");
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
