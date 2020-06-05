package app.view.user;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import app.view.user.cart.ViewCartPanel;
import app.view.user.home.HomePanel;
import app.view.user.product.ViewProductPanel;

@SuppressWarnings("serial")
public class CardMainFrame extends JPanel {

	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 550;
	
	public final static String HOME_PANEL = "Home Panel";
	public final static String VIEW_PRODUCT_PANEL = "View Product Panel";
	public final static String VIEW_CART_PANEL = "View Cart Panel";
	
	private CardLayout cardLayout;
	private ViewCartPanel viewCartPanel;
	
	public CardMainFrame() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		HomePanel userHome = new HomePanel();
		this.add(userHome, HOME_PANEL);
		
		ViewProductPanel viewProductPanel = new ViewProductPanel();
		this.add(viewProductPanel, VIEW_PRODUCT_PANEL);
		
		viewCartPanel = new ViewCartPanel();
		this.add(viewCartPanel, VIEW_CART_PANEL);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	
	public ViewCartPanel getViewCartPanel() {
		return viewCartPanel;
	}
	
}
