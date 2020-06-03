package app.factory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.ColorHandler;
import util.ImageHandler;

public class LabelFactory {

	private static final Color DEFAULT_COLOR = Color.WHITE;
	private final static int ICON_SIZE = 25;

	private static LabelFactory labelFactory;

	public static synchronized LabelFactory getInstance() {
		if (labelFactory == null)
			synchronized (LabelFactory.class) {
				if (labelFactory == null)
					labelFactory = new LabelFactory();
			}

		return labelFactory;
	}

	private void setDefaultStyle(JLabel jLabel) {
		jLabel.setBackground(DEFAULT_COLOR);

		String color = ColorHandler.PRIMARY_BACKGROUND;
		jLabel.setForeground(ColorHandler.getColor(color));

		jLabel.setOpaque(true);
	}

	public JLabel create(ImageIcon imageIcon) {
		JLabel jLabel = new JLabel();
		jLabel.setIcon(ImageHandler.getNewIconSize(imageIcon, ICON_SIZE, ICON_SIZE));
		setDefaultStyle(jLabel);
		return jLabel;
	}

	public JLabel create(String text, boolean isBold) {
		JLabel jLabel = new JLabel();
		jLabel.setText(text);

		setDefaultStyle(jLabel);

		if (isBold) {
			Font font = new Font("Dialog", Font.BOLD, 14);
			jLabel.setFont(font);
		}

		return jLabel;
	}

	public JLabel create(String text, String color, boolean isClickable) {
		JLabel jLabel = new JLabel();
		jLabel.setText(text);

		setDefaultStyle(jLabel);
		jLabel.setForeground(ColorHandler.getColor(color));

		if (isClickable) {
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
			jLabel.setCursor(cursor);
		}

		return jLabel;
	}

}
