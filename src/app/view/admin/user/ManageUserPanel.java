package app.view.admin.user;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import util.ColorHandler;
import util.FileHandler;

@SuppressWarnings("serial")
public class ManageUserPanel extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_TOP_HEIGHT = 40;
	private final static int BORDER_FIELD_TEXT = 2;
	private static final int TEXT_FIELD_WIDTH = 690;
	private static final int TEXT_FIELD_HEIGHT = 40;

	private CardManageUserPanel cardManageUserPanel;

	private JLabel titleLabel;
	private JTextField searchTextField;
	private JButton listViewButton;
	private JButton gridViewButton;
	private JButton searchButton;

	public ManageUserPanel() {
		initializePanel();
		initializeComponent();

		getListButton().addActionListener(listActionListener);
		getGridButton().addActionListener(gridActionListener);
	}

	private void initializePanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
	}

	private void initializeComponent() {
		add(initializeTopComponent(), BorderLayout.NORTH);
		add(getManageUserPanel(), BorderLayout.CENTER);
	}

	private JPanel initializeTopComponent() {
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.setBackground(PANEL_COLOR);
		eastPanel.add(getGridButton(), BorderLayout.CENTER);
		eastPanel.add(getListButton(), BorderLayout.WEST);

		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_TOP_HEIGHT);
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setPreferredSize(dimension);
		jPanel.add(eastPanel, BorderLayout.EAST);
		jPanel.add(getTitleLabel(), BorderLayout.CENTER);
		
//		jPanel.add(getSearchTextField(), BorderLayout.WEST);
//		jPanel.add(getSearchButton(), BorderLayout.CENTER);

		return jPanel;
	}

	private JLabel getTitleLabel() {
		if (titleLabel == null)
			titleLabel = LabelFactory.getInstance().create("View Active User", true);
		
		return titleLabel;
	}
	
	@SuppressWarnings("unused")
	private JTextField getSearchTextField() {
		if (searchTextField == null) {
			Dimension dimension = new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
			MatteBorder matteBorder = new MatteBorder(BORDER_FIELD_TEXT, BORDER_FIELD_TEXT, BORDER_FIELD_TEXT,
					BORDER_FIELD_TEXT, ColorHandler.getColor(ColorHandler.PRIMARY_BACKGROUND));
			searchTextField = TextFieldFactory.getInstance().create(false, null);
			searchTextField.setBorder(matteBorder);
			searchTextField.setPreferredSize(dimension);
		}

		return searchTextField;
	}

	@SuppressWarnings("unused")
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
	
	private CardManageUserPanel getManageUserPanel() {
		if (cardManageUserPanel == null) {
			cardManageUserPanel = new CardManageUserPanel();
			cardManageUserPanel.setBackground(PANEL_COLOR);
		}

		return cardManageUserPanel;
	}

	private ActionListener listActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = cardManageUserPanel.getCardLayout();
			cardLayout.show(cardManageUserPanel, CardManageUserPanel.VIEW_LIST_PANEL);
			cardManageUserPanel.getCardManageUserList().setBackground(PANEL_COLOR);
		}
	};

	private ActionListener gridActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = cardManageUserPanel.getCardLayout();
			cardLayout.show(cardManageUserPanel, CardManageUserPanel.VIEW_GRID_PANEL);
			cardManageUserPanel.getCardManageUserGrid().setBackground(PANEL_COLOR);
		}
	};
}
