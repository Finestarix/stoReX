package app.view.user.payment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.controller.CartController;
import app.controller.ProductController;
import app.controller.TransactionDetailController;
import app.controller.TransactionHeaderController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.Cart;
import app.model.Product;
import app.model.TransactionHeader;
import app.session.UserSession;
import app.util.CurrencyHandler;
import app.util.MessageHandler;
import app.util.ReceiptHandler;

@SuppressWarnings("serial")
public class PaymentFrame extends JFrame {

	private static final String FRAME_TITLE = "stoReX Transaction Page";
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 520;

	private ArrayList<Cart> carts;
	private double totalPrice;
	private String columnName[];
	private String rowData[][];
	private JTable jTable;
	private JScrollPane jScrollPane;
	private JLabel totalPriceLabel;
	private JButton proceedButton;
	
	private Callable<Void> callable;

	public PaymentFrame(Callable<Void> callable) {
		this.callable = callable;
		
		initializeFrame();
		initializeComponent();

		getProceedButton().addActionListener(proceedActionListener);
	}

	private void initializeFrame() {
		setTitle(FRAME_TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
	}

	private void initializeComponent() {
		add(getScrollPane(), BorderLayout.NORTH);
		add(getTotalPriceLabel(), BorderLayout.CENTER);
		add(getProceedButton(), BorderLayout.SOUTH);
	}

	private JTable getTable() {
		if (jTable == null)
			jTable = new JTable(getRowData(), getColumnName());

		return jTable;
	}

	private String[] getColumnName() {
		if (columnName == null) {
			columnName = new String[4];
			columnName[0] = "Product Name";
			columnName[1] = "Product Price";
			columnName[2] = "Cart Quantity";
			columnName[3] = "Sub-Total";
		}

		return columnName;
	}

	private String[][] getRowData() {
		if (rowData == null) {
			int row = CartController.getTotalCart();
			int column = 4;
			rowData = new String[row][column];

			carts = CartController.getAllCarts();
			for (int i = 0; i < row; i++) {
				Cart cart = carts.get(i);

				String price = CurrencyHandler.getRupiahFormat(cart.getPrice());
				String quantity = Integer.toString(cart.getQuantity()) + " Piece(s)";
				String subTotal = CurrencyHandler.getRupiahFormat(cart.getPrice() * cart.getQuantity());
				totalPrice += cart.getPrice() * cart.getQuantity();

				rowData[i][0] = cart.getName();
				rowData[i][1] = price;
				rowData[i][2] = quantity;
				rowData[i][3] = subTotal;
			}
		}

		return rowData;
	}

	private JScrollPane getScrollPane() {
		if (jScrollPane == null)
			jScrollPane = new JScrollPane(getTable());

		return jScrollPane;
	}

	private JButton getProceedButton() {
		if (proceedButton == null)
			proceedButton = ButtonFactory.getInstance().create("Proceed");

		return proceedButton;
	}

	private JLabel getTotalPriceLabel() {
		if (totalPriceLabel == null)
			totalPriceLabel = LabelFactory.getInstance()
					.create("Total Price:" + CurrencyHandler.getRupiahFormat(totalPrice), true);

		return totalPriceLabel;
	}

	private ActionListener proceedActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String message = "Are you sure proceed this cart ?";
			int confirmationResult = MessageHandler.confirmation(message);

			if (confirmationResult == JOptionPane.YES_OPTION) {
				String fileName = UserSession.getUser().getName() + "_" + new Date().getTime() + ".pdf";
				ReceiptHandler.getReceiptPDF(fileName);
				
				String userID = UserSession.getUser().getId();
				TransactionHeader transactionHeader = TransactionHeaderController.insertTransactionHeader(userID, fileName);

				String transactionID = transactionHeader.getId();
				for (Cart cart : carts) {
					String productID = cart.getId();
					int quantity = cart.getQuantity();
					
					Product product = ProductController.getProductByID(productID);
					ProductController.updateProductDecreaseQuantity(product, quantity);
					
					TransactionDetailController.insertTransactionDetail(transactionID, productID, quantity);
				}
				
				CartController.removeAllCart();
				
				try {
					PaymentFrame.this.callable.call();
				} catch (Exception exception) {
					exception.printStackTrace();;
				}
				
				message = "Transaction success !";
				MessageHandler.success(message);
				
				PaymentFrame.this.dispose();
			}
			
		}
	};

}
