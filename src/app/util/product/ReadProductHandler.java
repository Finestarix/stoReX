package app.util.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import app.controller.ProductController;
import app.model.Product;
import app.util.FileHandler;
import app.util.MessageHandler;

public class ReadProductHandler {

	private static final String FILE = FileHandler.getDatabasePath();

	public static void readProductHandler(String fileName) {

		fileName = FILE + "//" + fileName;

		try {
			File file = new File(fileName);
			file.createNewFile();
		} catch (IOException exception) {
			MessageHandler.error("Error Read Product File !");
			System.exit(0);
		}

		ArrayList<String> productList = new ArrayList<String>();

		try {
			PdfReader pdfReader = new PdfReader(fileName);

			int totalPages = pdfReader.getNumberOfPages();
			for (int i = 1; i <= totalPages; i++) {
				String[] pageContentSplit = PdfTextExtractor.getTextFromPage(pdfReader, i).split("\n");
				for (String content : pageContentSplit)
					productList.add(content);
			}

			removeHeaderProductFile(productList);
			ArrayList<Product> products = convertToProduct(productList);
			
			insertToDatabase(products);
			
			pdfReader.close();
		} catch (IOException exception) {
			MessageHandler.error("Error Read Product File !");
			System.exit(0);
		}

	}

	private static void removeHeaderProductFile(ArrayList<String> productList) {
		for (int i = 0; i < 5; i++)
			productList.remove(0);
	}

	private static ArrayList<Product> convertToProduct(ArrayList<String> productList) {
		ArrayList<Product> products = new ArrayList<Product>();

		for (String productString : productList) {
			String[] productStringSplit = productString.split(" ");

			String productID = productStringSplit[0];
			String productName = "";
			for (int i = 1; i < productStringSplit.length - 2; i++)
				productName += productStringSplit[i] + " ";
			productName.trim();
			
			int productPrice = Integer.parseInt(productStringSplit[productStringSplit.length - 2]);
			int productQuantity = Integer.parseInt(productStringSplit[productStringSplit.length - 1]);
			
			Product product = new Product(productID, productName, productPrice, productQuantity);
			products.add(product);
		}

		return products;
	}
	
	private static void insertToDatabase(ArrayList<Product> products) {
		for (Product product : products) 
			ProductController.insertNewProduct(product);
	}

}
