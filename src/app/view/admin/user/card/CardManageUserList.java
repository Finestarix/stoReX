package app.view.admin.user.card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import app.controller.UserController;
import app.model.User;
import app.view.admin.user.CardManageUserPanel;
import app.view.admin.user.template.UserList;

@SuppressWarnings("serial")
public class CardManageUserList extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 500;

	private JPanel userListPanel;
	private JScrollPane jScrollPane;
	private JScrollBar jScrollBar;

	private int totalComponentLoaded = 0;
	private int currentUserPage = 1;
	private int totalUser = 0;
	
	private CardManageUserPanel cardManageUserPanel;

	public CardManageUserList(CardManageUserPanel cardManageUserPanel) {
		this.cardManageUserPanel = cardManageUserPanel;
		
		initializePanel();
		initializeUserListPanel();
		add(jScrollPane);
	}

	private void initializePanel() {
		setOpaque(false);
		setBackground(PANEL_COLOR);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}

	private JPanel initializeUserListPanel() {

		if (userListPanel == null) {
			GridBagLayout gridBagLayout = new GridBagLayout();
			userListPanel = new JPanel(gridBagLayout);
			userListPanel.setBackground(PANEL_COLOR);
			userListPanel.setOpaque(false);

			jScrollPane = new JScrollPane(userListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
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

		return userListPanel;
	}

	public void refreshPanel(boolean isRefresh, boolean isAdd) {

		if (isAdd)
			totalComponentLoaded++;
		
		if (isRefresh) 
			initializeUserListPanel().removeAll();

		totalUser = UserController.getTotalUser();
		new UserFetcher(isRefresh).execute();
	}

	private AdjustmentListener adjustmentListener = new AdjustmentListener() {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {

			totalComponentLoaded = initializeUserListPanel().getComponentCount();
			totalUser = UserController.getTotalUser();

			if (!e.getValueIsAdjusting() && e.getValue() == jScrollBar.getMaximum() - jScrollBar.getVisibleAmount()
					&& totalComponentLoaded < totalUser) {
				currentUserPage++;
				refreshPanel(false, false);
			}
		}
	};

	public class UserFetcher extends SwingWorker<Void, User> {

		private GridBagConstraints gridBagConstraints;

		private boolean isRefresh;

		public UserFetcher(boolean isRefresh) {
			this.isRefresh = isRefresh;

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
		}

		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<User> users = (!isRefresh) ? UserController.getUsersPerPage(currentUserPage)
					: UserController.getUsersAlreadyLoaded(totalComponentLoaded);
			for (User user : users)
				publish(user);
			return null;
		}

		@Override
		protected void process(List<User> chunks) {
			for (int i = 0; i < chunks.size(); i++) {
				User user = chunks.get(i);
				UserList userList = new UserList(user, () -> {
					cardManageUserPanel.getCardManageUserGrid().refreshPanel(true, false);
					cardManageUserPanel.getCardManageUserList().refreshPanel(true, false);
					return null;
				});
				initializeUserListPanel().add(userList, gridBagConstraints);
			}
		}

		@Override
		protected void done() {
			initializeUserListPanel().revalidate();
			initializeUserListPanel().repaint();
		}

	}
	
}
