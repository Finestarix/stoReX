package app.view.admin;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.view.admin.product.ManageProductPanel;
import app.view.admin.user.AdminCardManageUser;
import app.view.admin.user.card.AdminCardHome;

@SuppressWarnings("serial")
public class CardMainFrame extends JPanel {

	public final static String HOME_PANEL = "Home Panel";
	public final static String MANAGE_PRODUCT_PANEL = "Manage Product Panel";
	public final static String MANAGE_USER_PANEL = "Manage User Panel";
	
	private CardLayout cardLayout;
	
	public CardMainFrame() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		AdminCardHome adminHome = new AdminCardHome();
		this.add(adminHome, HOME_PANEL);
		
		ManageProductPanel adminManageProduct = new ManageProductPanel();
		this.add(adminManageProduct, MANAGE_PRODUCT_PANEL);
		
		AdminCardManageUser adminManageUser = new AdminCardManageUser();
		this.add(adminManageUser, MANAGE_USER_PANEL);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

}
