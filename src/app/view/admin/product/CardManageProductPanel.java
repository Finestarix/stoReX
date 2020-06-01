package app.view.admin.product;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.view.admin.product.card.CardManageProductGrid;
import app.view.admin.product.card.CardManageProductList;

@SuppressWarnings("serial")
public class CardManageProductPanel extends JPanel {

	public final static String VIEW_LIST_PANEL = "View List";
	public final static String VIEW_GRID_PANEL = "View Grid";
	
	private CardLayout cardLayout;
	private CardManageProductList cardManageProductList;
	private CardManageProductGrid cardManageProductGrid;
	
	public CardManageProductPanel() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		cardManageProductList = new CardManageProductList(this);
		this.add(cardManageProductList, VIEW_LIST_PANEL);
		
		cardManageProductGrid = new CardManageProductGrid(this);
		this.add(cardManageProductGrid, VIEW_GRID_PANEL);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}
	
	public CardManageProductList getCardManageProductList() {
		return cardManageProductList;
	}
	
	public CardManageProductGrid getCardManageProductGrid() {
		return cardManageProductGrid;
	}
}
