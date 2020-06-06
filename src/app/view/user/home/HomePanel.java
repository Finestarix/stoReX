package app.view.user.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.factory.LabelFactory;
import app.session.UserSession;
import app.view.general.HomeSlider;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int X_GAP = 0;
	private static final int Y_GAP = 30;
	private static final int Y_GAP_INSIDE = 15;

	private Insets insets;
	private JLabel titleHome;
	private HomeSlider homeSlider;

	public HomePanel() {
		initializePanel();
		initializeComponent();
	}

	private void initializePanel() {
		setOpaque(false);
		setLayout(new BorderLayout());
		setBackground(PANEL_COLOR);
	}

	private void initializeComponent() {		
		add(getTitleHomeLabel(), BorderLayout.NORTH);
		add(getHomeSlider(), BorderLayout.CENTER);
	}

	private JLabel getTitleHomeLabel() {
		if (titleHome == null) {
			insets = new Insets(Y_GAP_INSIDE, X_GAP, Y_GAP_INSIDE, X_GAP);
			
			String message = "Welcome, " + UserSession.getUser().getName() + " (" + UserSession.getUser().getEmail()
					+ ") as " + UserSession.getUser().getRole();
			titleHome = LabelFactory.getInstance().create(message, true);
			titleHome.setHorizontalAlignment(JLabel.CENTER);
			titleHome.setBorder(new EmptyBorder(insets));
		}

		return titleHome;
	}

	private HomeSlider getHomeSlider() {
		if (homeSlider == null) {
			insets = new Insets(Y_GAP, X_GAP, Y_GAP, X_GAP);
			
			homeSlider = new HomeSlider();
			homeSlider.setBorder(new MatteBorder(insets, Color.WHITE));
		}

		return homeSlider;
	}
	
}
