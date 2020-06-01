package app.view.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
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
public class MainFrame extends JFrame {

	private static final String FRAME_TITLE = "stoReX Admin Page";
	private static final Color FRAME_COLOR = Color.WHITE;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	
	private static final int PANEL_TOP_LOGO_WEIGHT = 90;
	
	private static final int PANEL_TOP_WIDTH = 850;
	private static final int PANEL_TOP_HEIGHT = 90;
	
	private static final int PANEL_TOP_BORDER_ZERO = 0;
	private static final int PANEL_TOP_BORDER_HEIGHT = 15;

	private CardMainFrame adminCardPanel;
	private JLabel lblLogo;
	private JButton homeButton;
	private JButton userButton;
	private JButton productButton;
	private JButton logoutButton;

	public MainFrame() {
		initializeFrame();
		initializeComponent();

		getHomeButton().addActionListener(homeActionListener);
		getUserButton().addActionListener(userActionListener);
		getProductButton().addActionListener(productActionListener);
		getLogoutButton().addActionListener(closeActionListener);
	}

	private void initializeFrame() {
		setTitle(FRAME_TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		// TODO: Change This		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		ImageIcon frameIcon = new ImageIcon(FileHandler.getAssetsPath("logo-small.png"));
		setIconImage(frameIcon.getImage());
	}

	private void initializeComponent() {
		JPanel jPanel = new JPanel();
		jPanel.setBackground(FRAME_COLOR);
		jPanel.add(getTopComponent(), BorderLayout.NORTH);
		jPanel.add(getAdminCardPanel(), BorderLayout.CENTER);

		this.add(jPanel);
	}

	private JPanel getTopComponent() {
		JPanel eastPanel = new JPanel(new FlowLayout());
		Insets eastPanelInsets = new Insets(PANEL_TOP_BORDER_HEIGHT, PANEL_TOP_BORDER_ZERO, PANEL_TOP_BORDER_ZERO, PANEL_TOP_BORDER_ZERO);
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
			Image imageLogo = new ImageIcon(FileHandler.getAssetsPath("logo.png")).getImage();
			imageLogo = imageLogo.getScaledInstance(PANEL_TOP_LOGO_WEIGHT, PANEL_TOP_LOGO_WEIGHT, Image.SCALE_SMOOTH);
			
			lblLogo = new JLabel();
			lblLogo.setIcon(new ImageIcon(imageLogo));
		}

		return lblLogo;
	}

	private JButton getHomeButton() {
		if (homeButton == null) {
			ImageIcon imageIconHome = new ImageIcon(FileHandler.getAssetsPath("home-icon.png"));
			homeButton = ButtonFactory.getInstance().create("Home", imageIconHome);
		}

		return homeButton;
	}

	private JButton getUserButton() {
		if (userButton == null) {
			ImageIcon imageIconUser = new ImageIcon(FileHandler.getAssetsPath("manage-user-icon.png"));
			userButton = ButtonFactory.getInstance().create("Manage User", imageIconUser);
		}

		return userButton;
	}

	private JButton getProductButton() {
		if (productButton == null) {
			ImageIcon imageIconProduct = new ImageIcon(FileHandler.getAssetsPath("manage-product-icon.png"));
			productButton = ButtonFactory.getInstance().create("Manage Product", imageIconProduct);
		}

		return productButton;
	}

	private JButton getLogoutButton() {
		if (logoutButton == null) {
			ImageIcon imageIconLogout = new ImageIcon(FileHandler.getAssetsPath("logout-icon.png"));			
			logoutButton = ButtonFactory.getInstance().create("Logout", imageIconLogout);
		}

		return logoutButton;
	}

	private CardMainFrame getAdminCardPanel() {
		if (adminCardPanel == null)
			adminCardPanel = new CardMainFrame();

		return adminCardPanel;
	}

	private ActionListener homeActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = adminCardPanel.getCardLayout();
			cardLayout.show(adminCardPanel, CardMainFrame.HOME_PANEL);
		}
	};
	
	private ActionListener userActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = adminCardPanel.getCardLayout();
			cardLayout.show(adminCardPanel, CardMainFrame.MANAGE_USER_PANEL);
		}
	};

	private ActionListener productActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = adminCardPanel.getCardLayout();
			cardLayout.show(adminCardPanel, CardMainFrame.MANAGE_PRODUCT_PANEL);
		}
	};

	private ActionListener closeActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.dispose();
		}
	};

}