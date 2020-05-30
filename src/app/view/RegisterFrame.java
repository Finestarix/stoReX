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
import app.validator.Validator;
import app.validator.rule.EmailRule;
import app.validator.rule.NameRule;
import app.validator.rule.PasswordRule;
import app.validator.rule.parent.Rule;
import util.ColorHandler;
import util.FileHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class RegisterFrame extends JFrame {
	
	private static final String FRAME_TITLE = "stoReX Register Page";
	private static final Color FRAME_COLOR = Color.WHITE;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 350;
	private static final int PANEL_TITLE_BORDER = 10;

	private Insets insets;

	private JLabel lblLogo;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnRegister;
	private JLabel lblLogin;
	
	
	public RegisterFrame() {
		initializeFrame();
		initializeComponent();
		
		getRegisterButton().addActionListener(registerActionListener);
		getLoginLabel().addMouseListener(loginMouseAdapter);
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
		 * Name
		 */
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBackground(FRAME_COLOR);
		namePanel.add(getNameLabel(), BorderLayout.NORTH);
		namePanel.add(Box.createVerticalStrut(5));
		namePanel.add(getNameTextField(), BorderLayout.SOUTH);
		setNameConstraint(gridBagConstraints);
		jPanel.add(namePanel, gridBagConstraints);

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
		 * Register Button
		 */
		setRegisterConstraint(gridBagConstraints);
		jPanel.add(getRegisterButton(), gridBagConstraints);

		/**
		 * Login Label
		 */
		setLoginConstraint(gridBagConstraints);
		jPanel.add(getLoginLabel(), gridBagConstraints);

		this.add(jPanel, BorderLayout.CENTER);
	}
	
	private void setLogoConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridheight = 7;
		gridBagConstraints.weightx = 1;
	}
	
	private void setNameConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setEmailConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setPasswordConstraint(GridBagConstraints gridBagConstraints) {
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

	private void setLoginConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 9;
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
	
	private JLabel getNameLabel() {
		if (lblName == null)
			lblName = LabelFactory.getInstance().create("Name");

		return lblName;
	}

	private JTextField getNameTextField() {
		if (txtName == null)
			txtName = TextFieldFactory.getInstance().create(false, null);

		return txtName;
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

	private JButton getRegisterButton() {
		if (btnRegister == null)
			btnRegister = ButtonFactory.getInstance().create("Register");

		return btnRegister;
	}

	private JLabel getLoginLabel() {
		if (lblLogin == null)
			lblLogin = LabelFactory.getInstance().create("Back to Login", ColorHandler.PRIMARY_DANGER, true);

		return lblLogin;
	}
	
	ActionListener registerActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Rule nameField = new NameRule(getNameTextField());
			Rule emailField = new EmailRule(getEmailTextField());
			Rule passwordField = new PasswordRule(getPasswordTextField());

			if (Validator.validate(nameField, emailField, passwordField)) {
				
				getRegisterButton().setText("Registering");

				String name = getNameTextField().getText();
				String email = getEmailTextField().getText();
				String password = getPasswordTextField().getText();

				UserController.insertNewUser(name, email, password);

				getRegisterButton().setText("Register");
				getNameTextField().setText("");
				getEmailTextField().setText("");
				getPasswordTextField().setText("");
				
				RegisterFrame.this.dispose();
			} else {
				String errorMessage = Validator.getErrorMessages().get(0);
				MessageHandler.error(errorMessage);
			}

		}
	};

	MouseAdapter loginMouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			RegisterFrame.this.dispose();
		}
	};
}
	