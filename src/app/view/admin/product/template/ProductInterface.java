package app.view.admin.product.template;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public interface ProductInterface {

	void initializePanel();
	
	void initializeComponent();
	
	JPanel getDataComponent();
	
	JPanel getButtonComponent();
	
	JLabel getNameLabel();
	
	JLabel getPriceLabel();
	
	JLabel getQuantityLabel();
	
	JButton getUpdateButton();
	
	JButton getDeleteButton();
	
}
