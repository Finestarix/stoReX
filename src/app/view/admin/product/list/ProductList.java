package app.view.admin.product.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.Product;
import util.ColorHandler;
import util.FileHandler;

public class ProductList extends JPanel implements ProductInterface {

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
	private JButton updateButton;
	private JButton deleteButton;

	private Callable<Void> refreshCallable;

	private Product product;

	public ProductList(Product product, Callable<Void> refreshCallable) {
		this.refreshCallable = refreshCallable;
		this.product = product;

		initializePanel();
		initializeComponent();

		getUpdateButton().addActionListener(updateActionListener);
		getDeleteButton().addActionListener(deleteActionListener);
	}

	@Override
	public void initializePanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);

		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		setPreferredSize(dimension);
		Border panelBorder = new MatteBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
				ColorHandler.getColor(ColorHandler.PRIMARY_DANGER));
		setBorder(panelBorder);
	}

	@Override
	public void initializeComponent() {
		this.add(getDataComponent(), BorderLayout.CENTER);
		this.add(getButtonComponent(), BorderLayout.EAST);
	}

	@Override
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

	@Override
	public JPanel getButtonComponent() {

		Border panelBorder = new EmptyBorder(BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE,
				BORDER_BUTTTON_RIGHT_SIZE);
		JPanel jPanel = new JPanel();
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
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
			ImageIcon imageIconUpdate = new ImageIcon(FileHandler.getAssetsPath("update-icon.png"));
			updateButton = ButtonFactory.getInstance().create(imageIconUpdate, true);
		}

		return updateButton;
	}

	@Override
	public JButton getDeleteButton() {
		if (deleteButton == null) {
			ImageIcon imageIconDelete = new ImageIcon(FileHandler.getAssetsPath("delete-icon.png"));
			deleteButton = ButtonFactory.getInstance().create(imageIconDelete, true);
		}

		return deleteButton;
	}

	private ActionListener updateActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				refreshCallable.call();
			} catch (Exception exception) {
			}
		}
	};

	private ActionListener deleteActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				refreshCallable.call();
			} catch (Exception exception) {
			}
		}
	};

}