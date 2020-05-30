package app.factory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

import util.ColorHandler;

public class ButtonFactory {

	private static final Color DEFAULT_COLOR = Color.WHITE;
	private final static int DEFAULT_WIDTH = 250;
	private final static int DEFAULT_HEIGHT = 30;
	
	private static ButtonFactory buttonFactory;

	public static synchronized ButtonFactory getInstance() {
		if (buttonFactory == null)
			synchronized (ButtonFactory.class) {
				if (buttonFactory == null)
					buttonFactory = new ButtonFactory();
			}

		return buttonFactory;
	}
	
	private void setDefaultStyle(JButton jButton)
	{
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		jButton.setCursor(cursor);
		
		String color = ColorHandler.PRIMARY_BACKGROUND;
		jButton.setBackground(ColorHandler.getColor(color));
		jButton.setForeground(DEFAULT_COLOR);
		
		jButton.setFocusPainted(false);
		jButton.setBorderPainted(false);
		
		Dimension dimension = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		jButton.setPreferredSize(dimension);
	}
	
	public JButton create(String text) {
		JButton jButton = new JButton();
		jButton.setText(text);
		setDefaultStyle(jButton);
		return jButton;
	}
	
}
