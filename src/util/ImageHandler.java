package util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageHandler {

	public static ImageIcon getNewIconSize(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
}
