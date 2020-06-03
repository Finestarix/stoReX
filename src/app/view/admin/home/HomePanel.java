package app.view.admin.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.view.general.HomeSlider;
import session.UserSession;
import util.ColorHandler;
import util.FileHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {

	private static final Color PANEL_COLOR = Color.WHITE;
	private static final int X_GAP = 0;
	private static final int Y_GAP = 30;
	private static final int Y_GAP_INSIDE = 15;

	private JLabel titleHome;
	private HomeSlider homeSlider;
	private JLabel descriptionSlider;
	private JButton fileUploadButton;

	public HomePanel() {
		initializePanel();
		initializeComponent();

		getFileUploadButton().addActionListener(uploadActionListener);
	}

	private void initializePanel() {
		setOpaque(false);
		setLayout(new BorderLayout(X_GAP, Y_GAP));
		setBackground(PANEL_COLOR);
	}

	private void initializeComponent() {
		JPanel jPanel = new JPanel(new BorderLayout(X_GAP, Y_GAP_INSIDE));
		jPanel.add(getDescriptionLabel(), BorderLayout.CENTER);
		jPanel.add(getFileUploadButton(), BorderLayout.SOUTH);

		add(getTitleHomeLabel(), BorderLayout.NORTH);
		add(getHomeSlider(), BorderLayout.CENTER);
		add(jPanel, BorderLayout.SOUTH);
	}

	private JLabel getTitleHomeLabel() {
		if (titleHome == null) {
			String message = "Welcome, " + UserSession.getUser().getName() + " (" + UserSession.getUser().getEmail()
					+ ") as " + UserSession.getUser().getRole();
			titleHome = LabelFactory.getInstance().create(message, true);
			titleHome.setHorizontalAlignment(JLabel.CENTER);
		}

		return titleHome;
	}

	private HomeSlider getHomeSlider() {
		if (homeSlider == null)
			homeSlider = new HomeSlider();

		return homeSlider;
	}

	private JLabel getDescriptionLabel() {
		if (descriptionSlider == null) {
			String message = "*You can manage image slider image manually in /assets/image-slider or "
					+ "you can use button below to add image slider image";
			descriptionSlider = LabelFactory.getInstance().create(message, false);
			descriptionSlider.setHorizontalAlignment(JLabel.CENTER);
			descriptionSlider.setForeground(ColorHandler.getColor(ColorHandler.PRIMARY_DANGER));
		}

		return descriptionSlider;
	}

	private JButton getFileUploadButton() {
		if (fileUploadButton == null)
			fileUploadButton = ButtonFactory.getInstance().create("Upload Image Here");

		return fileUploadButton;
	}

	private ActionListener uploadActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			String directoryName = FileHandler.getAssetsPath() + "//slider-image";
			FileFilter imageFilter = new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes());

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(directoryName));
			fileChooser.setFileFilter(imageFilter);

			int resultFileChooser = fileChooser.showOpenDialog(HomePanel.this);

			if (resultFileChooser == JFileChooser.APPROVE_OPTION) {
				String fileToUpload = fileChooser.getSelectedFile().getAbsolutePath();
				File newFile = new File(fileToUpload);
				newFile.renameTo(new File(directoryName + "//" + newFile.getName()));
				homeSlider.refreshSlider();
				
				String message = "Upload image success !";
				MessageHandler.success(message);				
			}
		}
	};
}
