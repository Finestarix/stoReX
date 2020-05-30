package app.view.user;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import util.FileHandler;

public class UserMainFrame extends JFrame {

	private static final String FRAME_TITLE = "stoReX User Page";
	private static final Color FRAME_COLOR = Color.WHITE;
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	private static final int PANEL_TITLE_BORDER = 10;
	
	public UserMainFrame() {
		initializeFrame();
//		initializeComponent();

//		getLoginButton().addActionListener(loginActionListener);
//		getRegisterLabel().addMouseListener(registerMouseAdapter);
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

}
