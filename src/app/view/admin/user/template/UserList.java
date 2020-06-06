package app.view.admin.user.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.User;
import app.session.ImageCaching;
import app.util.ColorHandler;
import app.util.MessageHandler;

@SuppressWarnings("serial")
public class UserList extends JPanel implements UserInterface {

	private static final int PANEL_WIDTH = 840;
	private static final int PANEL_HEIGHT = 50;
	private static final int BORDER_SIZE = 1;
	private static final int BORDER_DATA_SIZE = 2;
	private static final int BORDER_DATA_LEFT_SIZE = 10;
	private static final int BORDER_BUTTON_SIZE = 2;
	private static final int BORDER_BUTTTON_RIGHT_SIZE = 20;
	private static final Color PANEL_COLOR = Color.WHITE;

	private JLabel nameLabel;
	private JLabel emailLabel;
	private JButton bannedButton;

	private Callable<Void> refreshCallable;

	private User user;

	public UserList(User user, Callable<Void> refreshCallable) {
		this.refreshCallable = refreshCallable;
		this.user = user;

		initializePanel();
		initializeComponent();

		getBannedButton().addActionListener(bannedActionListener);
	}

	@Override
	public void initializePanel() {
		Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
		Border panelBorder = new MatteBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE,
				ColorHandler.getColor(ColorHandler.PRIMARY_SECONDARY));

		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
		setBorder(panelBorder);
		setPreferredSize(dimension);
	}

	@Override
	public void initializeComponent() {
		this.add(getDataComponent(), BorderLayout.CENTER);
		this.add(getButtonComponent(), BorderLayout.EAST);
	}

	@Override
	public JPanel getDataComponent() {

		Border panelBorder = new EmptyBorder(BORDER_DATA_SIZE, BORDER_DATA_LEFT_SIZE, BORDER_DATA_SIZE,
				BORDER_DATA_SIZE);
		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
		jPanel.add(getNameLabel(), BorderLayout.NORTH);
		jPanel.add(getEmailLabel(), BorderLayout.SOUTH);

		return jPanel;
	}

	@Override
	public JPanel getButtonComponent() {

		Border panelBorder = new EmptyBorder(BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE, BORDER_BUTTON_SIZE,
				BORDER_BUTTTON_RIGHT_SIZE);
		JPanel jPanel = new JPanel();
		jPanel.setBackground(PANEL_COLOR);
		jPanel.setBorder(panelBorder);
		jPanel.add(getBannedButton(), BorderLayout.EAST);

		return jPanel;
	}

	@Override
	public JLabel getNameLabel() {
		if (nameLabel == null) {
			String userName = user.getName();
			nameLabel = LabelFactory.getInstance().create(userName, true);
		}

		return nameLabel;
	}

	@Override
	public JLabel getEmailLabel() {
		if (emailLabel == null) {
			String userEmail = user.getEmail();
			emailLabel = LabelFactory.getInstance().create(userEmail, false);
		}

		return emailLabel;
	}

	@Override
	public JButton getBannedButton() {
		if (bannedButton == null) {
			ImageIcon imageIconBanned = ImageCaching.getBannedIcon();
			bannedButton = ButtonFactory.getInstance().create(imageIconBanned, true);
		}

		return bannedButton;
	}

	private ActionListener bannedActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String message = "Do you want to banned " + user.getName() + " ?";
				int confirmationResult = MessageHandler.confirmation(message);

				if (confirmationResult == JOptionPane.YES_OPTION) {
					UserController.bannedUser(user);

					message = "Banned success !";
					MessageHandler.success(message);

					refreshCallable.call();
				}
			} catch (Exception exception) {
			}
		}
	};

}
