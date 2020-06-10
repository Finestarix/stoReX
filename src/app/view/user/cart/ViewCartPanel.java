package app.view.user.cart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;

import app.controller.CartController;
import app.factory.ButtonFactory;
import app.factory.TextFieldFactory;
import app.model.Cart;
import app.session.ImageCaching;
import app.util.ColorHandler;
import app.util.MessageHandler;
import app.view.user.UserMainFrame;
import app.view.user.cart.template.CartList;
import app.view.user.payment.PaymentFrame;

@SuppressWarnings("serial")
public class ViewCartPanel extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 400;
	private final static int BORDER_FIELD_TEXT = 1;
	private static final int TEXT_FIELD_WIDTH = 690;
	private static final int TEXT_FIELD_HEIGHT = 40;

	private JTextField searchTextField;
	private JButton searchButton;
	private JButton payButton;
	private JPanel cartListPanel;
	private JScrollPane jScrollPane;

	public ViewCartPanel() {
		initializePanel();
		initializeCartListPanel();
		initializeComponent();

		getSearchButton().addActionListener(searchActionListener);
		getPayButton().addActionListener(payActionListener);
	}

	private void initializePanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
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
		jPanel.add(getPayButton(), BorderLayout.EAST);

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

	private JButton getPayButton() {
		if (payButton == null) {
			ImageIcon imageIconPay = ImageCaching.getPayIcon();
			payButton = ButtonFactory.getInstance().create(imageIconPay, true);
		}

		return payButton;
	}

	private JPanel initializeCartListPanel() {

		if (cartListPanel == null) {
			GridBagLayout gridBagLayout = new GridBagLayout();
			cartListPanel = new JPanel(gridBagLayout);
			cartListPanel.setBackground(PANEL_COLOR);
			cartListPanel.setOpaque(false);

			jScrollPane = new JScrollPane(cartListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
			jScrollPane.setBackground(PANEL_COLOR);
			jScrollPane.setLayout(new ScrollPaneLayout());
			jScrollPane.getViewport().setBackground(PANEL_COLOR);

			refreshPanel();
		}

		return cartListPanel;
	}

	public void refreshPanel() {		
		initializeCartListPanel().removeAll();

		String searchCondition = getSearchTextField().getText().toString();
		new ProductFetcher(searchCondition).execute();
	}

	public class ProductFetcher extends SwingWorker<Void, Cart> {

		private GridBagConstraints gridBagConstraints;

		private String searchCondition;

		public ProductFetcher(String searchCondition) {
			this.searchCondition = searchCondition;

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
		}

		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<Cart> carts = (searchCondition.isEmpty()) ? CartController.getAllCarts()
					: CartController.getAllCarts(searchCondition);
			for (Cart cart : carts)
				publish(cart);
			return null;
		}

		@Override
		protected void process(List<Cart> chunks) {
			for (int i = 0; i < chunks.size(); i++) {
				Cart cart = chunks.get(i);
				CartList cartList = new CartList(cart);
				cartList.setRefreshCallable(() -> {
					refreshPanel();
					return null;
				});

				initializeCartListPanel().add(cartList, gridBagConstraints);
			}
		}

		@Override
		protected void done() {
			initializeCartListPanel().revalidate();
			initializeCartListPanel().repaint();
		}

	}

	private ActionListener searchActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			refreshPanel();
		}
	};

	private ActionListener payActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (CartController.getAllCarts().size() == 0) {
				String message = "Your cart is empty !";
				MessageHandler.error(message);
				return;
			}
			
			new PaymentFrame(() -> {
				UserMainFrame.cardMainFrame.getViewCartPanel().refreshPanel();
				UserMainFrame.cardMainFrame.getViewProductPanel().refreshPanel(true);
				UserMainFrame.cardMainFrame.getViewProductPanel().currentProductPage = 1;
				return null;
			});
		}
	};
}
