package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.model.User;
import app.validator.Validator;
import app.validator.rule.EmailRule;
import app.validator.rule.PasswordRule;
import app.validator.rule.parent.Rule;
import app.view.admin.AdminMainFrame;
import app.view.user.UserMainFrame;
import util.ColorHandler;
import util.FileHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private static final String FRAME_TITLE = "stoReX Login Page";
	private static final Color FRAME_COLOR = Color.WHITE;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 300;
	private static final int PANEL_TITLE_BORDER = 10;

	private Insets insets;

	private JLabel lblLogo;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JLabel lblRegister;

	private UserMainFrame userMainFrame;
	private AdminMainFrame adminMainFrame;
	private RegisterFrame registerFrame;

	public LoginFrame() {
		initializeFrame();
		initializeComponent();

		getLoginButton().addActionListener(loginActionListener);
		getRegisterLabel().addMouseListener(registerMouseAdapter);
	}

	private void initializeFrame() {
		setTitle(FRAME_TITLE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		ImageIcon frameIcon = new ImageIcon(FileHandler.getAssetsPath("logo-small.png"));
		setIconImage(frameIcon.getImage());
	}

	private void initializeComponent() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		insets = new Insets(PANEL_TITLE_BORDER, PANEL_TITLE_BORDER, PANEL_TITLE_BORDER, PANEL_TITLE_BORDER);
		JPanel jPanel = new JPanel(gridBagLayout);
		jPanel.setBackground(FRAME_COLOR);
		jPanel.setBorder(new EmptyBorder(insets));

		/**
		 * Logo
		 */
		setLogoConstraint(gridBagConstraints);
		jPanel.add(getLogoLabel(), gridBagConstraints);

		/**
		 * Username
		 */
		JPanel usernamePanel = new JPanel(new BorderLayout());
		usernamePanel.setBackground(FRAME_COLOR);
		usernamePanel.add(getEmailLabel(), BorderLayout.NORTH);
		usernamePanel.add(Box.createVerticalStrut(5));
		usernamePanel.add(getEmailTextField(), BorderLayout.SOUTH);
		setEmailConstraint(gridBagConstraints);
		jPanel.add(usernamePanel, gridBagConstraints);

		/**
		 * Password
		 */
		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.setBackground(FRAME_COLOR);
		passwordPanel.add(getPasswordLabel(), BorderLayout.NORTH);
		passwordPanel.add(Box.createVerticalStrut(5));
		passwordPanel.add(getPasswordTextField(), BorderLayout.SOUTH);
		setPasswordConstraint(gridBagConstraints);
		jPanel.add(passwordPanel, gridBagConstraints);

		/**
		 * Login Button
		 */
		setLoginConstraint(gridBagConstraints);
		jPanel.add(getLoginButton(), gridBagConstraints);

		/**
		 * Register Label
		 */
		setRegisterConstraint(gridBagConstraints);
		jPanel.add(getRegisterLabel(), gridBagConstraints);

		this.add(jPanel, BorderLayout.CENTER);
	}

	private void setLogoConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridheight = 7;
		gridBagConstraints.weightx = 1;
	}

	private void setEmailConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setPasswordConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setLoginConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setRegisterConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private JLabel getLogoLabel() {
		if (lblLogo == null) {
			lblLogo = new JLabel();
			lblLogo.setIcon(new ImageIcon(FileHandler.getAssetsPath("logo.png")));
		}

		return lblLogo;
	}

	private JLabel getEmailLabel() {
		if (lblEmail == null)
			lblEmail = LabelFactory.getInstance().create("Email");

		return lblEmail;
	}

	private JTextField getEmailTextField() {
		if (txtEmail == null)
			txtEmail = TextFieldFactory.getInstance().create(false, null);

		return txtEmail;
	}

	private JLabel getPasswordLabel() {
		if (lblPassword == null)
			lblPassword = LabelFactory.getInstance().create("Password");

		return lblPassword;
	}

	private JTextField getPasswordTextField() {
		if (txtPassword == null)
			txtPassword = TextFieldFactory.getInstance().create(true, null);

		return txtPassword;
	}

	private JButton getLoginButton() {
		if (btnLogin == null)
			btnLogin = ButtonFactory.getInstance().create("Login", false);

		return btnLogin;
	}

	private JLabel getRegisterLabel() {
		if (lblRegister == null)
			lblRegister = LabelFactory.getInstance().create("Register Here", ColorHandler.PRIMARY_DANGER, true);

		return lblRegister;
	}

	private UserMainFrame getUserMainFrame() {
		LoginFrame.this.setVisible(false);

		if (userMainFrame == null) {
			userMainFrame = new UserMainFrame();
			userMainFrame.setVisible(true);
			userMainFrame.addWindowListener(showLoginWindowAdapter);
		} else
			userMainFrame.setVisible(true);

		return userMainFrame;
	}

	private AdminMainFrame getAdminMainFrame() {
		LoginFrame.this.setVisible(false);

		if (adminMainFrame == null) {
			adminMainFrame = new AdminMainFrame();
			adminMainFrame.setVisible(true);
			adminMainFrame.addWindowListener(showLoginWindowAdapter);
		} else
			adminMainFrame.setVisible(true);

		return adminMainFrame;
	}

	private UserMainFrame getRegisterFrame() {
		LoginFrame.this.setVisible(false);

		if (registerFrame == null) {
			registerFrame = new RegisterFrame();
			registerFrame.setVisible(true);
			registerFrame.addWindowListener(showLoginWindowAdapter);
		} else
			registerFrame.setVisible(true);

		return userMainFrame;
	}

	ActionListener loginActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			// User			
//			getEmailTextField().setText("rey@storex.com");
//			getPasswordTextField().setText("rey123");

			// Admin
			getEmailTextField().setText("renaldy@storex.com");
			getPasswordTextField().setText("storex123");
			
			Rule emailField = new EmailRule(getEmailTextField());
			Rule passwordField = new PasswordRule(getPasswordTextField());

			if (Validator.validate(emailField, passwordField)) {
				getLoginButton().setText("Authenticating");

				String email = getEmailTextField().getText();
				String password = getPasswordTextField().getText();

				User user = UserController.auth(email, password);

				getLoginButton().setText("Login");

				if (user == null) {
					String errorMessage = "Invalid email and password.";
					MessageHandler.error(errorMessage);
					return;
				}

				getEmailTextField().setText("");
				getPasswordTextField().setText("");

				if (user.getRole().equals("User"))
					getUserMainFrame();
				else if (user.getRole().equals("Admin"))
					getAdminMainFrame();

			} else {
				String errorMessage = Validator.getErrorMessages().get(0);
				MessageHandler.error(errorMessage);
			}

		}
	};

	MouseAdapter registerMouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getRegisterFrame();
		}
	};

	WindowAdapter showLoginWindowAdapter = new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
			LoginFrame.this.setVisible(true);
		}
	};

}