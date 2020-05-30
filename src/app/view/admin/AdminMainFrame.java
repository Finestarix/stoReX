package app.view.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.factory.ButtonFactory;
import util.FileHandler;

@SuppressWarnings("serial")
public class AdminMainFrame extends JFrame {

	private static final String FRAME_TITLE = "stoReX Admin Page";
	private static final Color FRAME_COLOR = Color.WHITE;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	private static final int PANEL_ZERO = 0;
	private static final int PANEL_TITLE_BORDER = 30;
	private static final int PANEL_TOP_WIDTH = 850;
	private static final int PANEL_TOP_HEIGHT = 130;
	private static final int PANEL_TOP_BORDER_HEIGHT = 30;

	private Insets insets;
	private AdminCardPanel adminCardPanel;
	private JLabel lblLogo;
	private JButton homeButton;
	private JButton userButton;
	private JButton productButton;
	private JButton logoutButton;

	public AdminMainFrame() {
		initializeFrame();
		initializeComponent();

		getUserButton().addActionListener(userActionListener);
		getProductButton().addActionListener(productActionListener);
		getLogoutButton().addActionListener(closeActionListener);
	}

	private void initializeFrame() {
		setTitle(FRAME_TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		ImageIcon frameIcon = new ImageIcon(FileHandler.getAssetsPath("logo-small.png"));
		setIconImage(frameIcon.getImage());
	}

	private void initializeComponent() {
		insets = new Insets(PANEL_ZERO, PANEL_TITLE_BORDER, PANEL_TITLE_BORDER, PANEL_TITLE_BORDER);

		JPanel jPanel = new JPanel();
		jPanel.setBackground(FRAME_COLOR);
		jPanel.setBorder(new EmptyBorder(insets));

		jPanel.add(getTopComponent(), BorderLayout.NORTH);
		jPanel.add(getAdminCardPanel(), BorderLayout.CENTER);

		this.add(jPanel);
	}

	private JPanel getTopComponent() {
		JPanel eastPanel = new JPanel(new FlowLayout());
		Insets eastPanelInsets = new Insets(PANEL_TOP_BORDER_HEIGHT, PANEL_ZERO, PANEL_ZERO, PANEL_ZERO);
		eastPanel.setBackground(FRAME_COLOR);
		eastPanel.setBorder(new EmptyBorder(eastPanelInsets));
		eastPanel.add(getHomeButton());
		eastPanel.add(getUserButton());
		eastPanel.add(getProductButton());
		eastPanel.add(getLogoutButton());

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(FRAME_COLOR);
		topPanel.setPreferredSize(new Dimension(PANEL_TOP_WIDTH, PANEL_TOP_HEIGHT));
		topPanel.add(getLogoLabel(), BorderLayout.WEST);
		topPanel.add(eastPanel, BorderLayout.EAST);

		return topPanel;
	}

	private JLabel getLogoLabel() {
		if (lblLogo == null) {
			lblLogo = new JLabel();
			lblLogo.setIcon(new ImageIcon(FileHandler.getAssetsPath("logo.png")));
		}

		return lblLogo;
	}

	private JButton getHomeButton() {
		if (homeButton == null)
			homeButton = ButtonFactory.getInstance().create("Home", true);

		return homeButton;
	}

	private JButton getUserButton() {
		if (userButton == null)
			userButton = ButtonFactory.getInstance().create("Manage User", true);

		return userButton;
	}

	private JButton getProductButton() {
		if (productButton == null)
			productButton = ButtonFactory.getInstance().create("Manage Product", true);

		return productButton;
	}

	private JButton getLogoutButton() {
		if (logoutButton == null)
			logoutButton = ButtonFactory.getInstance().create("Logout", true);

		return logoutButton;
	}

	private AdminCardPanel getAdminCardPanel() {
		if (adminCardPanel == null)
			adminCardPanel = new AdminCardPanel();

		return adminCardPanel;
	}

	ActionListener userActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = adminCardPanel.getCardLayout();
			cardLayout.show(adminCardPanel, AdminCardPanel.MANAGE_USER_PANEL);
		}
	};

	ActionListener productActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = adminCardPanel.getCardLayout();
			cardLayout.show(adminCardPanel, AdminCardPanel.MANAGE_PRODUCT_PANEL);
		}
	};

	ActionListener closeActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			AdminMainFrame.this.dispose();
		}
	};

}
