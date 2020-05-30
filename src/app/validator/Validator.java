package app.validator;

import java.util.ArrayList;

import app.validator.rule.parent.Rule;

public class Validator {

	private static ArrayList<String> errorMessages = new ArrayList<>();
	
	public static ArrayList<String> getErrorMessages() {
		return errorMessages;
	}

	public static boolean validate(Rule... rules) {
		errorMessages.clear();

		for (Rule rule : rules) {
			String errorMessage = rule.validate();

			if (!errorMessage.isEmpty()) {
				errorMessages.add(errorMessage);
				return false;
			}
		}

		return true;
	}
}
