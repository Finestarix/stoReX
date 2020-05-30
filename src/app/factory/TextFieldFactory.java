package app.factory;

import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import util.ColorHandler;

public class TextFieldFactory {

	private final static int DEFAULT_WIDTH = 250;
	private final static int DEFAULT_HEIGHT = 25;

	private final static int BORDER_NULL = 0;
	private final static int BORDER_BOTTOM = 2;

	private static TextFieldFactory textFieldFactory;

	public static synchronized TextFieldFactory getInstance() {
		if (textFieldFactory == null)
			synchronized (TextFieldFactory.class) {
				if (textFieldFactory == null)
					textFieldFactory = new TextFieldFactory();
			}

		return textFieldFactory;
	}

	private void setDefaultStyle(JTextField jTextField) {
		String color = ColorHandler.PRIMARY_BACKGROUND;
		MatteBorder matteBorder = new MatteBorder(BORDER_NULL, BORDER_NULL, BORDER_BOTTOM, BORDER_NULL,
				ColorHandler.getColor(color));
		jTextField.setBorder(matteBorder);

		Dimension dimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jTextField.setPreferredSize(dimension);
	}

	public JTextField create(boolean isPasswordField, Dimension dimension) {
		JTextField jTextField = (isPasswordField) ? 
				new JPasswordField() : new JTextField();
				
		setDefaultStyle(jTextField);
		
		if (dimension != null)
			jTextField.setPreferredSize(dimension);
			
		return jTextField;
	}

}
