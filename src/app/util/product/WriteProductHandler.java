package app.util.product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import app.controller.ProductController;
import app.model.Product;
import app.util.FileHandler;
import app.util.MessageHandler;

public class WriteProductHandler {

	private static final String FILE = FileHandler.getDatabasePath();

	private static Font regularFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL);

	public static void generateProductReport(String fileName) {

		fileName = FILE + "//" + fileName;

		try {
			File file = new File(fileName);
			file.createNewFile();
		} catch (IOException e1) {
			MessageHandler.error("Error Generating File !");
			System.exit(0);
		}
		
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(fileName));

			document.open();
			addMetaData(document);
			addHeaderPage(document);
			addTransactionContent(document);
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			MessageHandler.error("Error Generating PDF !");
			System.exit(0);
		}

	}

	private static void addMetaData(Document document) {
		document.addTitle("stoReX Products");
		document.addSubject("Created by RX19-2");
		document.addKeywords("JavaH2BP, PDF, Products");
		document.addAuthor("RX19-2");
		document.addCreator("RX19-2");
	}

	private static void addHeaderPage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();

		preface.add(new Paragraph("stoReX Products", regularFont));
		addEmptyLine(preface, 1);

		Date date = new Date();
		preface.add(new Paragraph("Generate Date: " + date, boldFont));
		addEmptyLine(preface, 1);

		document.add(preface);
	}

	private static void addTransactionContent(Document document) throws DocumentException {
		PdfPTable pdfPTable = new PdfPTable(4);
		pdfPTable.setWidthPercentage(100);
		pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell pdfPCell = null;

		pdfPCell = new PdfPCell(new Phrase("Product ID", smallFont));
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase("Product Name", smallFont));
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase("Product Price", smallFont));
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(pdfPCell);

		pdfPCell = new PdfPCell(new Phrase("Product Quantity", smallFont));
		pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pdfPTable.addCell(pdfPCell);

		ArrayList<Product> products = ProductController.getAllProduct();
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);

			String id = product.getId();
			String name = product.getName();
			String price = Integer.toString(product.getPrice());
			String quantity = Integer.toString(product.getQuantity());

			pdfPTable.addCell(new PdfPCell(new Phrase(id, smallFont)));
			pdfPTable.addCell(new PdfPCell(new Phrase(name, smallFont)));
			pdfPTable.addCell(new PdfPCell(new Phrase(price, smallFont)));
			pdfPTable.addCell(new PdfPCell(new Phrase(quantity, smallFont)));
		}

		document.add(pdfPTable);
	}

	private static void addEmptyLine(Paragraph paragraph, int enter) {
		for (int i = 0; i < enter; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
}
