package app.view.user.cart.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.controller.CartController;
import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.Cart;
import session.ImageCaching;
import util.ColorHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class CartList extends JPanel {
	
	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 60;
	private static final int BORDER_SIZE = 1;
	private static final int BORDER_DATA_SIZE = 2;
	private static final int BORDER_DATA_LEFT_SIZE = 10;
	private static final int BORDER_BUTTON_SIZE = 2;
	private static final int BORDER_BUTTTON_RIGHT_SIZE = 20;
	private static final Color PANEL_COLOR = Color.WHITE;

	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;
	private JButton deleteButton;

	private Cart cart;
	private Callable<Void> refreshCallable;

	public CartList(Cart cart) {
		this.cart = cart;

		initializePanel();
		initializeComponent();

		getDeleteButton().addActionListener(deleteActionListener);
	}
	
	public void setRefreshCallable(Callable<Void> refreshCallable) {
		this.refreshCallable = refreshCallable;
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
		jPanel.add(getDeleteButton());

		return jPanel;
	}

	private JLabel getNameLabel() {
		if (nameLabel == null) {
			String productName = cart.getName();
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

			int productPrice = cart.getPrice();
			String productPriceString = decimalFormat.format(productPrice);
			priceLabel = LabelFactory.getInstance().create("@" + productPriceString, false);
		}

		return priceLabel;
	}

	private JLabel getQuantityLabel() {
		if (quantityLabel == null) {
			String productQuantity = "Stock : " + Integer.toString(cart.getQuantity());
			quantityLabel = LabelFactory.getInstance().create(productQuantity, false);
		}

		return quantityLabel;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			ImageIcon imageIconDelete = ImageCaching.getDeleteIcon();
			deleteButton = ButtonFactory.getInstance().create(imageIconDelete, true);
		}

		return deleteButton;
	}

	private ActionListener deleteActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String message = "Do you want to remove " + cart.getName() + " ?";
				int confirmationResult = MessageHandler.confirmation(message);

				if (confirmationResult == JOptionPane.YES_OPTION) {

					CartController.removeCart(cart);

					message = "Remove success !";
					MessageHandler.success(message);

					refreshCallable.call();
				}
			} catch (Exception exception) {
			}
		}
	};
	
}
