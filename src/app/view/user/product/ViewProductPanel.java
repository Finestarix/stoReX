package app.view.user.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.TextFieldFactory;
import app.model.Product;
import app.view.user.product.template.ProductList;
import session.ImageCaching;
import util.ColorHandler;

@SuppressWarnings("serial")
public class ViewProductPanel extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 500;
	private final static int BORDER_FIELD_TEXT = 1;
	private static final int TEXT_FIELD_WIDTH = 690;	private static final int TEXT_FIELD_HEIGHT = 40;

	private JTextField searchTextField;
	private JButton searchButton;
	private JPanel productListPanel;
	private JScrollPane jScrollPane;
	private JScrollBar jScrollBar;

	private int totalComponentLoaded = 0;
	private int currentProductPage = 1;
	private int totalProduct = 0;

	public ViewProductPanel() {
		initializePanel();
		initializeProductListPanel();
		initializeComponent();

		getSearchButton().addActionListener(searchActionListener);
		
	}

	private void initializePanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
	}

	private void initializeComponent() {
		add(initializeTopComponent(), BorderLayout.NORTH);
		add(jScrollPane, BorderLayout.CENTER);
	}

	private JPanel initializeTopComponent() {
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setBackground(PANEL_COLOR);
		jPanel.add(getSearchTextField(), BorderLayout.WEST);
		jPanel.add(getSearchButton(), BorderLayout.CENTER);

		return jPanel;
	}

	private JTextField getSearchTextField() {
		if (searchTextField == null) {
			Dimension dimension = new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
			MatteBorder matteBorder = new MatteBorder(BORDER_FIELD_TEXT, BORDER_FIELD_TEXT, BORDER_FIELD_TEXT,
					BORDER_FIELD_TEXT, ColorHandler.getColor(ColorHandler.PRIMARY_BACKGROUND));

			searchTextField = TextFieldFactory.getInstance().create(false, null);
			searchTextField.setBorder(matteBorder);
			searchTextField.setPreferredSize(dimension);
			searchTextField.setFont(searchTextField.getFont().deriveFont(20));
		}

		return searchTextField;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			ImageIcon imageIconSearch = ImageCaching.getSearchIcon();
			searchButton = ButtonFactory.getInstance().create(imageIconSearch, true);
		}

		return searchButton;
	}

	private JPanel initializeProductListPanel() {

		if (productListPanel == null) {
			GridBagLayout gridBagLayout = new GridBagLayout();
			productListPanel = new JPanel(gridBagLayout);
			productListPanel.setBackground(PANEL_COLOR);
			productListPanel.setOpaque(false);

			jScrollPane = new JScrollPane(productListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
			jScrollPane.setBackground(PANEL_COLOR);
			jScrollPane.setLayout(new ScrollPaneLayout());
			jScrollPane.getViewport().setBackground(PANEL_COLOR);

			jScrollBar = jScrollPane.getVerticalScrollBar();
			jScrollBar.addAdjustmentListener(adjustmentListener);
			refreshPanel(false);
		}

		return productListPanel;
	}

	public void refreshPanel(boolean isRefresh) {
		if (isRefresh)
			initializeProductListPanel().removeAll();

		String searchCondition = getSearchTextField().getText().toString();
		totalProduct = (searchCondition.isEmpty()) ? ProductController.getTotalProduct()
				: ProductController.getTotalProduct(searchCondition);
		new ProductFetcher(isRefresh, searchCondition).execute();
	}

	private AdjustmentListener adjustmentListener = new AdjustmentListener() {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {

			totalComponentLoaded = initializeProductListPanel().getComponentCount();
			totalProduct = ProductController.getTotalProduct();

			if (!e.getValueIsAdjusting() && e.getValue() == jScrollBar.getMaximum() - jScrollBar.getVisibleAmount()
					&& totalComponentLoaded < totalProduct) {
				currentProductPage++;
				refreshPanel(false);
			}
		}
	};

	public class ProductFetcher extends SwingWorker<Void, Product> {

		private GridBagConstraints gridBagConstraints;

		private boolean isRefresh;
		private String searchCondition;

		public ProductFetcher(boolean isRefresh, String searchCondition) {
			this.isRefresh = isRefresh;
			this.searchCondition = searchCondition;

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
		}

		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<Product> products = null;
			if (searchCondition.isEmpty())
				products = (!isRefresh) ? ProductController.getProductsPerPage(currentProductPage)
						: ProductController.getProductsAlreadyLoaded(totalComponentLoaded);
			else
				products = ProductController.getProductsPerPage(currentProductPage, searchCondition);

			for (Product product : products)
				publish(product);
			return null;
		}

		@Override
		protected void process(List<Product> chunks) {
			for (int i = 0; i < chunks.size(); i++) {
				Product product = chunks.get(i);
				ProductList productList = new ProductList(product, () -> {
					refreshPanel(true);
					return null;
				});
				initializeProductListPanel().add(productList, gridBagConstraints);
			}
		}

		@Override
		protected void done() {
			initializeProductListPanel().revalidate();
			initializeProductListPanel().repaint();
		}

	}

	private ActionListener searchActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentProductPage = 1;
			refreshPanel(true);
		}
	};


}
