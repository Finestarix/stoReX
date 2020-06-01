package app.validator.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import app.validator.rule.parent.Rule;

public class ProductPriceRule implements Rule {

	private JTextField jTextField;

	public ProductPriceRule(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String validate() {
		String price = this.jTextField.getText();

		if (price.isEmpty())
			return "Price must be filled.";

		else {
			String regex = "^[0-9]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(price);
			if (!matcher.matches())
				return "Price must be numeric.";
			
			else if (Integer.parseInt(price) < 1) 
				return "Price must be positive.";
			
			else if (Integer.parseInt(price) >= 99999999)
				return "Price must be less than Rp. 99.999.999,00";
		}

		return "";
	}
	
}
