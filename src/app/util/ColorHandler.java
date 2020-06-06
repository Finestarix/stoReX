package app.util;

import java.awt.Color;

public class ColorHandler {
	public final static String PRIMARY_BACKGROUND = "#404040";
	public final static String PRIMARY_SECONDARY = "#808080";
	public final static String PRIMARY_DANGER = "#ff6961";
	
	public static Color getColor(String color) {
		return Color.decode(color);
	}

}
