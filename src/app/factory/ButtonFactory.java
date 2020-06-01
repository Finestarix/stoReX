package app.factory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.ColorHandler;

public class ButtonFactory {

	private static final Color DEFAULT_COLOR = Color.WHITE;
	private final static int DEFAULT_WIDTH = 250;
	private final static int DEFAULT_HEIGHT = 30;
	
	private final static int ICON_SIZE = 25;
	private final static int MENU_WIDTH = 160;
	private final static int MENU_HEIGHT = 50;
	
	private final static int LIST_WIDTH = 50;
	private final static int LIST_HEIGHT = 50;
	
	private final static int SMALL_WIDTH = 35;
	private final static int SMALL_HEIGHT = 35;
	
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
	
	private void setMenuStyle(JButton jButton) {
		Dimension dimension = new Dimension(MENU_WIDTH, MENU_HEIGHT);
		jButton.setPreferredSize(dimension);
	}
	
	private void setListStyle(JButton jButton) {
		Dimension dimension = new Dimension(LIST_WIDTH, LIST_HEIGHT);
		jButton.setPreferredSize(dimension);
	}
	
	private void setSmallStyle(JButton jButton) {
		Dimension dimension = new Dimension(SMALL_WIDTH, SMALL_HEIGHT);
		jButton.setPreferredSize(dimension);
	}
	
	private ImageIcon getIcon(ImageIcon icon) {
		Image image = icon.getImage();
		image = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	public JButton create(String text) {
		JButton jButton = new JButton();
		jButton.setText(text);
		setDefaultStyle(jButton);
		return jButton;
	}
	
	public JButton create(String text, String color) {
		JButton jButton = new JButton();
		jButton.setText(text);
		setDefaultStyle(jButton);
		jButton.setBackground(ColorHandler.getColor(color));
		return jButton;
	}
	
	public JButton create(ImageIcon imageIcon, boolean isSmall) {
		JButton jButton = new JButton();
		setDefaultStyle(jButton);
		if (!isSmall)
			setListStyle(jButton);
		else
			setSmallStyle(jButton);
		jButton.setIcon(getIcon(imageIcon));
		return jButton;
	}
	
	public JButton create(String text, ImageIcon imageIcon) {
		JButton jButton = new JButton();
		jButton.setLayout(new BorderLayout());
		
		setDefaultStyle(jButton);
		setMenuStyle(jButton);
		
		JLabel jLabel = new JLabel();
		jLabel.setText(text);
		jLabel.setForeground(DEFAULT_COLOR);
		
		JLabel jLabelIcon = new JLabel();
		jLabelIcon.setIcon(getIcon(imageIcon));
		
		jButton.add(jLabel, BorderLayout.EAST);
		jButton.add(jLabelIcon, BorderLayout.WEST);
		
		return jButton;
	}
}
