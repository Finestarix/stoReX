package app.validator.rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import app.validator.rule.parent.Rule;

public class EmailRule implements Rule {

	private JTextField jTextField;

	public EmailRule(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	@Override
	public String validate() {
		String email = this.jTextField.getText();

		if (email.isEmpty())
			return "Email must be filled.";

		else {
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches())
				return "Email must be in email format.";
		}

		return "";
	}

}
