package app.view.admin.user.template;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface UserInterface {

void initializePanel();
	
	void initializeComponent();
	
	JPanel getDataComponent();
	
	JPanel getButtonComponent();
	
	JLabel getNameLabel();
	
	JLabel getEmailLabel();
	
	JButton getBannedButton();
	
}
