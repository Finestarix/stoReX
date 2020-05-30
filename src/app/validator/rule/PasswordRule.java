package app.validator.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import app.validator.rule.parent.Rule;

public class PasswordRule implements Rule{

	private JTextField jTextField;

	public PasswordRule(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String validate() {
		String password = this.jTextField.getText();

		if (password.isEmpty())
			return "Password must be filled.";

		else {
			String regex = "^[a-zA-Z0-9]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(password);
			if (!matcher.matches())
				return "Password must be alphanumeric.";
		}

		return "";
	}
	
}
