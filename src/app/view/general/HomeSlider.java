package app.view.general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import app.factory.LabelFactory;
import session.ImageCaching;

@SuppressWarnings("serial")
public class HomeSlider extends JPanel {

	private static final int X_GAP = 10;
	private static final int Y_GAP = 0;

	private ArrayList<ImageIcon> imageSliders;
	private JLabel imageSliderContainer;
	private JLabel prevLabel;
	private JLabel nextButton;

	private int currentImage;

	public HomeSlider() {
		initializePanel();
		initializeComponent();

		getPrevButton().addMouseListener(prevMouseAdapter);
		getNextButton().addMouseListener(nextMouseAdapter);
	}

	private void initializePanel() {
		setLayout(new BorderLayout());
	}

	private void initializeComponent() {
		refreshSlider();

		add(getPrevButton(), BorderLayout.WEST);
		add(getImageSliderContainer(), BorderLayout.CENTER);
		add(getNextButton(), BorderLayout.EAST);
	}

	public void refreshSlider() {
		imageSliders = ImageCaching.getImageSlider();
		currentImage = 0;
	}

	private JLabel getImageSliderContainer() {
		if (imageSliderContainer == null) {
			MatteBorder border = new MatteBorder(Y_GAP, X_GAP, Y_GAP, X_GAP, Color.WHITE);

			imageSliderContainer = new JLabel();
			imageSliderContainer.setHorizontalAlignment(JLabel.CENTER);
			imageSliderContainer.setIcon(imageSliders.get(0));
			imageSliderContainer.setBorder(border);
		}

		return imageSliderContainer;
	}

	private JLabel getPrevButton() {
		if (prevLabel == null) {
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

			ImageIcon prevImageIcon = ImageCaching.getPrevIcon();
			prevLabel = LabelFactory.getInstance().create(prevImageIcon);
			prevLabel.setCursor(cursor);
		}

		return prevLabel;
	}

	private JLabel getNextButton() {
		if (nextButton == null) {
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

			ImageIcon nextImageIcon = ImageCaching.getNextIcon();
			nextButton = LabelFactory.getInstance().create(nextImageIcon);
			nextButton.setCursor(cursor);
		}

		return nextButton;
	}

	private MouseAdapter prevMouseAdapter = new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
			if (currentImage > 0)
				getImageSliderContainer().setIcon(imageSliders.get(--currentImage));
			else {
				currentImage = imageSliders.size() - 1;
				getImageSliderContainer().setIcon(imageSliders.get(currentImage));
			}
		};
	};

	private MouseAdapter nextMouseAdapter = new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
			if (currentImage < imageSliders.size() - 1)
				getImageSliderContainer().setIcon(imageSliders.get(++currentImage));
			else {
				currentImage = 0;
				getImageSliderContainer().setIcon(imageSliders.get(currentImage));
			}
		};
	};
}
