package app.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CurrencyHandler {

	public static String getRupiahFormat(double price) {
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setCurrencySymbol("Rp. ");
		decimalFormatSymbols.setMonetaryDecimalSeparator(',');
		decimalFormatSymbols.setGroupingSeparator('.');

		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		
		String formatedPrice = decimalFormat.format(price);
		
		return formatedPrice;
	}
	
}
