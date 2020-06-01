package app.view.admin.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.TextFieldFactory;
import app.model.Product;
import app.view.admin.product.list.ProductList;
import util.ColorHandler;
import util.FileHandler;

@SuppressWarnings("serial")
public class ManageProductPanel extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_TOP_HEIGHT = 40;
	private static final int BORDER_ZERO = 0;
	private static final int BORDER_SIZE = 30;
	private static final int BORDER_SCROLL_SIZE = 10;
	private final static int BORDER_FIELD_TEXT = 2;
	private static final int TEXT_FIELD_WIDTH = 690;
	private static final int TEXT_FIELD_HEIGHT = 40;
	private static final int SCROLL_WIDTH = 0;
	private static final int SCROLL_HEIGHT = 530;

	private JTextField searchTextField;
	private JButton listViewButton;
	private JButton gridViewButton;
	private JButton addButton;
	private JButton searchButton;
	private JPanel productListPanel;
	private JScrollPane jScrollPane;
	private JScrollBar jScrollBar;
	
	private int currentProductPage = 1;
	private int totalProductLoaded = 0;
	
	public ManageProductPanel() {
		initializePanel();
		initializeProductListPanel();
		initializeComponent();
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
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.setBackground(PANEL_COLOR);
		eastPanel.add(getAddButton(), BorderLayout.EAST);
		eastPanel.add(getGridButton(), BorderLayout.CENTER);
		eastPanel.add(getListButton(), BorderLayout.WEST);

		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_TOP_HEIGHT);
		Border panelBorder = new EmptyBorder(BORDER_ZERO, BORDER_SIZE, BORDER_ZERO, BORDER_SIZE);
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setPreferredSize(dimension);
		jPanel.setBorder(panelBorder);
		jPanel.add(getSearchTextField(), BorderLayout.WEST);
		jPanel.add(getSearchButton(), BorderLayout.CENTER);
		jPanel.add(eastPanel, BorderLayout.EAST);

		return jPanel;
	}

	private JTextField getSearchTextField() {
		if (searchTextField == null) {
			Dimension dimension = new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
			MatteBorder matteBorder = new MatteBorder(BORDER_FIELD_TEXT, BORDER_FIELD_TEXT, BORDER_FIELD_TEXT, BORDER_FIELD_TEXT,
					ColorHandler.getColor(ColorHandler.PRIMARY_BACKGROUND));
			searchTextField = TextFieldFactory.getInstance().create(false, null);
			searchTextField.setBorder(matteBorder);
			searchTextField.setPreferredSize(dimension);
		}

		return searchTextField;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			ImageIcon imageIconSearch = new ImageIcon(FileHandler.getAssetsPath("search-icon.png"));
			searchButton = ButtonFactory.getInstance().create(imageIconSearch, false);
		}

		return searchButton;
	}

	private JButton getListButton() {
		if (listViewButton == null) {
			ImageIcon imageIconList = new ImageIcon(FileHandler.getAssetsPath("list-view-icon.png"));
			listViewButton = ButtonFactory.getInstance().create(imageIconList, true);
		}

		return listViewButton;
	}

	private JButton getGridButton() {
		if (gridViewButton == null) {
			ImageIcon imageIconGrid = new ImageIcon(FileHandler.getAssetsPath("grid-view-icon.png"));
			gridViewButton = ButtonFactory.getInstance().create(imageIconGrid, true);
		}

		return gridViewButton;
	}

	private JButton getAddButton() {
		if (addButton == null) {
			ImageIcon imageIconAdd = new ImageIcon(FileHandler.getAssetsPath("add-icon.png"));
			addButton = ButtonFactory.getInstance().create(imageIconAdd, true);
		}

		return addButton;
	}

	private JPanel initializeProductListPanel() {
		
		if (productListPanel == null) {
			GridBagLayout gridBagLayout = new GridBagLayout();
			productListPanel = new JPanel(gridBagLayout);
			productListPanel.setOpaque(false);
			
			Border panelBorder = new EmptyBorder(BORDER_SCROLL_SIZE, BORDER_SIZE, BORDER_SCROLL_SIZE, BORDER_SIZE);
			jScrollPane = new JScrollPane(productListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setPreferredSize(new Dimension(SCROLL_WIDTH, SCROLL_HEIGHT));
			jScrollPane.getVerticalScrollBar().setUnitIncrement(25);
			jScrollPane.setBackground(PANEL_COLOR);
			jScrollPane.setLayout(new ScrollPaneLayout());
			jScrollPane.getViewport().setBackground(PANEL_COLOR);
			jScrollPane.setBorder(panelBorder);
			
			jScrollBar = jScrollPane.getVerticalScrollBar();
			jScrollBar.addAdjustmentListener(adjustmentListener);
			refreshPanel();
		}
		
		return productListPanel;
	}
	
	private void refreshPanel() {
		totalProductLoaded = ProductController.getTotalProduct();
		new ProductFetcher().execute();
	}
		
	private AdjustmentListener adjustmentListener = new AdjustmentListener() {
		
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			int totalComponentLoaded = ManageProductPanel.this.initializeProductListPanel().getComponentCount();
			if (!e.getValueIsAdjusting() &&
					e.getValue() == jScrollBar.getMaximum() - jScrollBar.getVisibleAmount() &&
					totalComponentLoaded != totalProductLoaded) {
				currentProductPage++;
				refreshPanel();
			}
		}
	};
	
	public class ProductFetcher extends SwingWorker<Void, Product> {

		private GridBagConstraints gridBagConstraints;
		
		public ProductFetcher() {
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
		}
		
		@Override
		protected Void doInBackground() throws Exception {			
			ArrayList<Product> products = null;
			products = ProductController.getProductsPerPage(currentProductPage);
			for(Product product : products) 
				publish(product);
			return null;
		}
		
		@Override
		protected void process(List<Product> chunks) {
			for (int i = 0 ; i < chunks.size() ; i++) {
				Product product = chunks.get(i);
				ProductList productList = new ProductList(product, () -> {
					refreshPanel();
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

}
