package app.view.admin.product.card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingWorker;

import app.controller.ProductController;
import app.model.Product;
import app.view.admin.product.CardManageProductPanel;
import app.view.admin.product.template.ProductGrid;

@SuppressWarnings("serial")
public class CardManageProductGrid extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 500;

	private JPanel productListPanel;
	private JScrollPane jScrollPane;
	private JScrollBar jScrollBar;

	private int totalComponentLoaded = 0;
	private int currentProductPage = 1;
	private int totalProduct = 0;

	private CardManageProductPanel cardManageProductPanel;
	
	public CardManageProductGrid(CardManageProductPanel cardManageProductPanel) {
		this.cardManageProductPanel = cardManageProductPanel;
		
		initializePanel();
		initializeProductGridPanel();
		add(jScrollPane);
	}

	private void initializePanel() {
		setOpaque(false);
		setBackground(PANEL_COLOR);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}

	private JPanel initializeProductGridPanel() {

		if (productListPanel == null) {
			productListPanel = new JPanel(new GridLayout(0, 4));
			productListPanel.setBackground(PANEL_COLOR);
			productListPanel.setOpaque(false);

			jScrollPane = new JScrollPane(productListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			jScrollPane.getVerticalScrollBar().setUnitIncrement(25);
			jScrollPane.setBackground(PANEL_COLOR);
			jScrollPane.setLayout(new ScrollPaneLayout());
			jScrollPane.getViewport().setBackground(PANEL_COLOR);

			jScrollBar = jScrollPane.getVerticalScrollBar();
			jScrollBar.addAdjustmentListener(adjustmentListener);
			refreshPanel(false, false);
		}

		return productListPanel;
	}

	public void refreshPanel(boolean isRefresh, boolean isAdd) {

		if (isAdd)
			totalComponentLoaded++;

		if (isRefresh)
			initializeProductGridPanel().removeAll();

		totalProduct = ProductController.getTotalProduct();
		new ProductFetcher(isRefresh).execute();
	}

	private AdjustmentListener adjustmentListener = new AdjustmentListener() {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {

			totalComponentLoaded = CardManageProductGrid.this.initializeProductGridPanel().getComponentCount();
			totalProduct = ProductController.getTotalProduct();

			if (!e.getValueIsAdjusting() && e.getValue() == jScrollBar.getMaximum() - jScrollBar.getVisibleAmount()
					&& totalComponentLoaded < totalProduct) {
				currentProductPage++;
				refreshPanel(false, false);
			}
		}
	};

	public class ProductFetcher extends SwingWorker<Void, Product> {

		private boolean isRefresh;

		public ProductFetcher(boolean isRefresh) {
			this.isRefresh = isRefresh;
		}

		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<Product> products = (!isRefresh) ? ProductController.getProductsPerPage(currentProductPage)
					: ProductController.getProductsAlreadyLoaded(CardManageProductGrid.this.totalComponentLoaded);
			for (Product product : products)
				publish(product);
			return null;
		}

		@Override
		protected void process(List<Product> chunks) {
			for (int i = 0; i < chunks.size(); i++) {
				Product product = chunks.get(i);
				ProductGrid productGrid = new ProductGrid(product, () -> {
					cardManageProductPanel.getCardManageProductGrid().refreshPanel(true, false);
					cardManageProductPanel.getCardManageProductList().refreshPanel(true, false);
					return null;
				});
				initializeProductGridPanel().add(productGrid);
			}
		}

		@Override
		protected void done() {
			initializeProductGridPanel().revalidate();
			initializeProductGridPanel().repaint();
		}

	}

}
