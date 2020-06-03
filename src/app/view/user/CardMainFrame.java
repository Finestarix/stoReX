package app.view.user;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.view.user.cart.ViewCartPanel;
import app.view.user.home.HomePanel;
import app.view.user.product.ViewProductPanel;

public class CardMainFrame extends JPanel {

	public final static String HOME_PANEL = "Home Panel";
	public final static String VIEW_PRODUCT_PANEL = "View Product Panel";
	public final static String VIEW_CART_PANEL = "View Cart Panel";
	
	private CardLayout cardLayout;
	
	public CardMainFrame() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		HomePanel userHome = new HomePanel();
		this.add(userHome, HOME_PANEL);
		
		ViewProductPanel adminManageProduct = new ViewProductPanel();
		this.add(adminManageProduct, VIEW_PRODUCT_PANEL);
		
		ViewCartPanel adminManageUser = new ViewCartPanel();
		this.add(adminManageUser, VIEW_CART_PANEL);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	
}
