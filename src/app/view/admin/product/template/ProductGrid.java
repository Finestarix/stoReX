package app.view.admin.product.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import app.factory.DialogFactory;
import app.factory.LabelFactory;
import app.model.Product;
import app.session.ImageCaching;
import app.util.ColorHandler;
import app.util.CurrencyHandler;
import app.util.MessageHandler;
import app.view.admin.product.dialog.DialogUpdateProduct;

@SuppressWarnings("serial")
public class ProductGrid extends JPanel implements ProductInterface {

	private static final int PANEL_WIDTH = 840 / 4;
	private static final int PANEL_HEIGHT = 110;
	private static final int BORDER_SIZE = 1;
	private static final int BORDER_ALL_TOP = 0;
	private static final int BORDER_ALL_BOTTOM = 0;
	private static final int BORDER_ALL_LEFT = 10;
	private static final int BORDER_ALL_RIGHT = 20;
	private static final Color PANEL_COLOR = Color.WHITE;

	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel quantityLabel;
	private JButton updateButton;
	private JButton deleteButton;

	private Callable<Void> refreshCallable;

	private DialogUpdateProduct dialogUpdateProduct;
	private Product product;

	public ProductGrid(Product product, Callable<Void> refreshCallable) {
		this.refreshCallable = refreshCallable;
		this.product = product;

		initializePanel();
		initializeComponent();

		getUpdateButton().addActionListener(updateActionListener);
		getDeleteButton().addActionListener(deleteActionListener);
	}

	@Override
	public void initializePanel() {

		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		Border panelBorder = new MatteBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
				ColorHandler.getColor(ColorHandler.PRIMARY_SECONDARY));

		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
		setPreferredSize(dimension);
		setBorder(panelBorder);
	}

	@Override
	public void initializeComponent() {
		this.add(getDataComponent(), BorderLayout.CENTER);
		this.add(getButtonComponent(), BorderLayout.SOUTH);
	}

	@Override
	public JPanel getDataComponent() {

		Border panelBorder = new EmptyBorder(BORDER_ALL_TOP, BORDER_ALL_LEFT, BORDER_ALL_BOTTOM, BORDER_ALL_RIGHT);

		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
		jPanel.add(getNameLabel(), BorderLayout.NORTH);
		jPanel.add(getPriceLabel(), BorderLayout.CENTER);
		jPanel.add(getQuantityLabel(), BorderLayout.SOUTH);

		return jPanel;
	}

	@Override
	public JPanel getButtonComponent() {

		JPanel jPanel = new JPanel();
		jPanel.setBackground(PANEL_COLOR);
		jPanel.add(getUpdateButton(), BorderLayout.WEST);
		jPanel.add(getDeleteButton(), BorderLayout.EAST);

		return jPanel;
	}

	@Override
	public JLabel getNameLabel() {
		if (nameLabel == null) {
			String productName = product.getName();
			nameLabel = LabelFactory.getInstance().create(productName, true);
		}

		return nameLabel;
	}

	@Override
	public JLabel getPriceLabel() {
		if (priceLabel == null) {
			int productPrice = product.getPrice();
			String productPriceString = CurrencyHandler.getRupiahFormat(productPrice);
			priceLabel = LabelFactory.getInstance().create(productPriceString, false);
		}

		return priceLabel;
	}

	@Override
	public JLabel getQuantityLabel() {
		if (quantityLabel == null) {
			String productQuantity = "Stock : " + Integer.toString(product.getQuantity());
			quantityLabel = LabelFactory.getInstance().create(productQuantity, false);
		}

		return quantityLabel;
	}

	@Override
	public JButton getUpdateButton() {
		if (updateButton == null) {
			ImageIcon imageIconUpdate = ImageCaching.getUpdateIcon();
			updateButton = ButtonFactory.getInstance().create(imageIconUpdate, true);
		}

		return updateButton;
	}

	@Override
	public JButton getDeleteButton() {
		if (deleteButton == null) {
			ImageIcon imageIconDelete = ImageCaching.getDeleteIcon();
			deleteButton = ButtonFactory.getInstance().create(imageIconDelete, true);
		}

		return deleteButton;
	}

	private ActionListener updateActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				dialogUpdateProduct = new DialogUpdateProduct(product);
				dialogUpdateProduct.setCallable(() -> {
					refreshCallable.call();
					dialogUpdateProduct.close();
					if (dialogUpdateProduct.getDialogResult() == DialogFactory.CONFIRM) {
						String message = "Update success !";
						MessageHandler.success(message);
					}
					return null;
				});

				dialogUpdateProduct.setVisible(true);
			} catch (Exception exception) {
			}
		}
	};

	private ActionListener deleteActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				String message = "Do you want to delete " + product.getName() + " ?";
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
