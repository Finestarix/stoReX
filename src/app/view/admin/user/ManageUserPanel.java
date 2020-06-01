package app.view.admin.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ManageUserPanel extends JPanel {

	private static final int PANEL_HEIGHT = 550;
	private static final int PANEL_WIDTH = 900;
	
	public ManageUserPanel() {
		initializeComponent();
	}

	private void initializeComponent() {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(Color.BLACK);
		this.add(new Label("User"));
	}
}
