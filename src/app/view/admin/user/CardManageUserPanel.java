package app.view.admin.user;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.view.admin.user.card.CardManageUserGrid;
import app.view.admin.user.card.CardManageUserList;

@SuppressWarnings("serial")
public class CardManageUserPanel extends JPanel {

	public final static String VIEW_LIST_PANEL = "View List";
	public final static String VIEW_GRID_PANEL = "View Grid";

	private CardLayout cardLayout;
	private CardManageUserList cardManageUserList;
	private CardManageUserGrid cardManageUserGrid;

	public CardManageUserPanel() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);

		cardManageUserList = new CardManageUserList(this);
		this.add(cardManageUserList, VIEW_LIST_PANEL);

		cardManageUserGrid = new CardManageUserGrid(this);
		this.add(cardManageUserGrid, VIEW_GRID_PANEL);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public CardManageUserList getCardManageUserList() {
		return cardManageUserList;
	}

	public CardManageUserGrid getCardManageUserGrid() {
		return cardManageUserGrid;
	}

}
