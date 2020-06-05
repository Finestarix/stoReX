package app.view.user.product.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.controller.CartController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.model.Cart;
import app.model.Product;
import app.validator.Validator;
import app.validator.rule.ProductQuantityRule;
import app.validator.rule.parent.Rule;
import app.view.user.UserMainFrame;
import session.ImageCaching;
import util.ColorHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class ProductList extends JPanel {

	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 60;
	private static final int BORDER_SIZE = 1;
	private static final int BORDER_DATA_SIZE = 2;
	private static final int BORDER_DATA_LEFT_SIZE = 10;
	private static final int BORDER_BUTTON_SIZE = 2;
	private static final int BORDER_BUTTTON_RIGHT_SIZE = 20;
	private static final int QUANTITY_WIDTH = 30;
	private static final int QUANTITY_HEIGHT = 30;
	private static final Color PANEL_COLOR = Color.WHITE;
	private static final String QUANTITY_PLACEHOLDER = "Qty";

	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;
	private JButton addButton;
	private JTextField addQuantityField;

	private Product product;

	public ProductList(Product product) {
		this.product = product;

		initializePanel();
		initializeComponent();

		getAddButton().addActionListener(addActionListener);
	}

	private void initializePanel() {
		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		Border panelBorder = new MatteBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
				ColorHandler.getColor(ColorHandler.PRIMARY_SECONDARY));

		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
		setBorder(panelBorder);
		setPreferredSize(dimension);
	}

	private void initializeComponent() {
		this.add(getDataComponent(), BorderLayout.CENTER);
		this.add(getButtonComponent(), BorderLayout.EAST);
	}

	private JPanel getDataComponent() {

		Border panelBorder = new EmptyBorder(BORDER_DATA_SIZE, BORDER_DATA_LEFT_SIZE, BORDER_DATA_SIZE,
				BORDER_DATA_SIZE);
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
		jPanel.add(getNameLabel(), BorderLayout.NORTH);
		jPanel.add(getPriceLabel(), BorderLayout.CENTER);
		jPanel.add(getQuantityLabel(), BorderLayout.SOUTH);

		return jPanel;
	}

	private JPanel getButtonComponent() {

		Border panelBorder = new EmptyBorder(BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE,
				BORDER_BUTTTON_RIGHT_SIZE);
		JPanel jPanel = new JPanel();
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
		jPanel.add(getAddQuantityField());
		jPanel.add(getAddButton());

		return jPanel;
	}

	private JLabel getNameLabel() {
		if (nameLabel == null) {
			String productName = product.getName();
			nameLabel = LabelFactory.getInstance().create(productName, true);
		}

		return nameLabel;
	}

	private JLabel getPriceLabel() {
		if (priceLabel == null) {
			DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
			decimalFormatSymbols.setCurrencySymbol("Rp. ");
			decimalFormatSymbols.setMonetaryDecimalSeparator(',');
			decimalFormatSymbols.setGroupingSeparator('.');

			DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
			decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

			int productPrice = product.getPrice();
			String productPriceString = decimalFormat.format(productPrice);
			priceLabel = LabelFactory.getInstance().create(productPriceString, false);
		}

		return priceLabel;
	}

	private JLabel getQuantityLabel() {
		if (quantityLabel == null) {
			String productQuantity = "Stock : " + Integer.toString(product.getQuantity());
			quantityLabel = LabelFactory.getInstance().create(productQuantity, false);
		}

		return quantityLabel;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			ImageIcon imageIconUpdate = ImageCaching.getAddIcon();
			addButton = ButtonFactory.getInstance().create(imageIconUpdate, true);
		}

		return addButton;
	}

	private JTextField getAddQuantityField() {
		if (addQuantityField == null) {
			Dimension dimension = new Dimension(QUANTITY_WIDTH, QUANTITY_HEIGHT);
			addQuantityField = TextFieldFactory.getInstance().create(false, dimension);
			addQuantityField.setHorizontalAlignment(JTextField.CENTER);
			addQuantityField.setText(QUANTITY_PLACEHOLDER);
			addQuantityField.addFocusListener(quantityMouseAdapter);
		}

		return addQuantityField;
	}

	private ActionListener addActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				Rule quantityRule = new ProductQuantityRule(addQuantityField);

				if (Validator.validate(quantityRule)) {

					boolean isProductExist = CartController.isCartExist(product.getId());

					int productQuantity = product.getQuantity();
					int toAddQuantity = Integer.parseInt(getAddQuantityField().getText().toString());

					if (isProductExist) {
						Cart cart = CartController.getCartByID(product.getId());
						productQuantity -= cart.getQuantity();
					}

					if (toAddQuantity > productQuantity) {
						String errorMessage = "Product quantity must be less than product stock.";
						MessageHandler.error(errorMessage);
						return;
					}

					String message = "Do you want to add " + getAddQuantityField().getText().toString() + " "
							+ product.getName() + " ?";
					int confirmationResult = MessageHandler.confirmation(message);

					if (confirmationResult == JOptionPane.YES_OPTION) {

						String id = product.getId();
						
						if (isProductExist) 
							CartController.updateCart(id, toAddQuantity);
						else {
							String name = product.getName();
							int price = product.getPrice();
							CartController.addNewCart(id, name, price, toAddQuantity);
						}

						UserMainFrame.cardMainFrame.getViewCartPanel().refreshPanel();
						message = "Success add " + getAddQuantityField().getText().toString() + " " + product.getName()
								+ " !";
						MessageHandler.success(message);

						getAddQuantityField().setText(QUANTITY_PLACEHOLDER);
					}

				} else {
					String errorMessage = Validator.getErrorMessages().get(0);
					MessageHandler.error(errorMessage);
				}

			} catch (Exception exception) {
			}
		}
	};

	private FocusListener quantityMouseAdapter = new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
			String username = getAddQuantityField().getText();
			if (username.isEmpty()) {
				getAddQuantityField().setCaretPosition(0);
				getAddQuantityField().setText(QUANTITY_PLACEHOLDER);
			}
		}

		@Override
		public void focusGained(FocusEvent e) {
			String username = getAddQuantityField().getText();
			if (username.equals(QUANTITY_PLACEHOLDER)) {
				getAddQuantityField().setText(null);
				getAddQuantityField().setCaretPosition(0);
			}
		}

	};

}
