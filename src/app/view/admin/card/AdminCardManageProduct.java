package app.view.admin.card;

import java.awt.Label;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AdminCardManageProduct extends JPanel {

	public AdminCardManageProduct() {
		initializeComponent();
	}

	private void initializeComponent() {
		this.add(new Label("Product"));
	}
	
}
