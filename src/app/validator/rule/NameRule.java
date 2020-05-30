package app.validator.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import app.validator.rule.parent.Rule;

public class NameRule implements Rule {

	private JTextField jTextField;

	public NameRule(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String validate() {
		String name = this.jTextField.getText();

		if (name.isEmpty())
			return "Name must be filled.";

		else {
			String regex = "^[A-Za-z]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(name);
			if (!matcher.matches())
				return "Name must contain only alphanumeric characters.";
		}

		return "";
	}
	
}
