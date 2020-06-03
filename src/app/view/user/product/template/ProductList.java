package app.view.user.product.template;

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

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.Product;
import session.ImageCaching;
import util.ColorHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class ProductList extends JPanel {
	
	private static final int PANEL_WIDTH = 800;
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
	private JButton addButton;

	private Callable<Void> refreshCallable;

	private Product product;

	public ProductList(Product product, Callable<Void> refreshCallable) {
		this.refreshCallable = refreshCallable;
		this.product = product;

		initializePanel();
		initializeComponent();

		getAddButton().addActionListener(addActionListener);
	}

	public void initializePanel() {
		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		Border panelBorder = new MatteBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
				ColorHandler.getColor(ColorHandler.PRIMARY_SECONDARY));

		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
		setBorder(panelBorder);
		setPreferredSize(dimension);
	}

	public void initializeComponent() {
		this.add(getDataComponent(), BorderLayout.CENTER);
		this.add(getButtonComponent(), BorderLayout.EAST);
	}

	public JPanel getDataComponent() {

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

	public JPanel getButtonComponent() {

		Border panelBorder = new EmptyBorder(BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE,
				BORDER_BUTTTON_RIGHT_SIZE);
		JPanel jPanel = new JPanel();
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
		jPanel.add(getAddButton(), BorderLayout.EAST);

		return jPanel;
	}

	public JLabel getNameLabel() {
		if (nameLabel == null) {
			String productName = product.getName();
			nameLabel = LabelFactory.getInstance().create(productName, true);
		}

		return nameLabel;
	}

	public JLabel getPriceLabel() {
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

	public JLabel getQuantityLabel() {
		if (quantityLabel == null) {
			String productQuantity = "Stock : " + Integer.toString(product.getQuantity());
			quantityLabel = LabelFactory.getInstance().create(productQuantity, false);
		}

		return quantityLabel;
	}

	public JButton getAddButton() {
		if (addButton == null) {
			ImageIcon imageIconUpdate = ImageCaching.getAddIcon();
			addButton = ButtonFactory.getInstance().create(imageIconUpdate, true);
		}

		return addButton;
	}

	private ActionListener addActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				
				String message = "Do you want to add " + product.getName() + " ?";
				int confirmationResult = MessageHandler.confirmation(message);

				if (confirmationResult == JOptionPane.YES_OPTION) {

					ProductController.deleteProduct(product);

					message = "Delete success !";
					MessageHandler.success(message);

					refreshCallable.call();
				}
				
			} catch (Exception exception) {
			}
		}
	};
	
}
