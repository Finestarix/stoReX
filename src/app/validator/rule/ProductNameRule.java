package app.validator.rule;

import javax.swing.JTextField;

import app.validator.rule.parent.Rule;

public class ProductNameRule implements Rule {

	private JTextField jTextField;

	public ProductNameRule(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String validate() {
		String name = this.jTextField.getText();

		if (name.isEmpty())
			return "Name must be filled.";

		return "";
	}
	
}
