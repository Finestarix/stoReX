package app.validator.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import app.validator.rule.parent.Rule;

public class ProductQuantityRule implements Rule {

	private JTextField jTextField;

	public ProductQuantityRule(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String validate() {
		String quantity = this.jTextField.getText();

		if (quantity.isEmpty())
			return "Quantity must be filled.";

		else {
			String regex = "^[0-9]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(quantity);
			if (!matcher.matches())
				return "Quantity must be numeric.";
			
			else if (Integer.parseInt(quantity) < 1) 
				return "Quantity must be positive.";
			
			else if (Integer.parseInt(quantity) >= 9999)
				return "Quantity must be less than 9999";
		}

		return "";
	}
	
}
