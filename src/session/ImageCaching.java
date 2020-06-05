package session;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import util.FileHandler;
import util.ImageHandler;

public class ImageCaching {

	private static ImageIcon logoSmallIcon;
	private static ImageIcon logoIcon;

	private static ImageIcon homeIcon;
	private static ImageIcon manageProductIcon;
	private static ImageIcon manageUserIcon;
	private static ImageIcon viewCartIcon;
	private static ImageIcon logoutIcon;

	private static ImageIcon addIcon;
	private static ImageIcon updateIcon;
	private static ImageIcon deleteIcon;
	private static ImageIcon bannedIcon;

	private static ImageIcon gridViewIcon;
	private static ImageIcon listViewIcon;

	private static ImageIcon searchIcon;
	private static ImageIcon payIcon;

	private static ImageIcon prevIcon;
	private static ImageIcon nextIcon;

	private static ArrayList<ImageIcon> imageSlideIcon;

	public static ImageIcon getLogoSmallIcon() {
		if (logoSmallIcon == null)
			logoSmallIcon = new ImageIcon(FileHandler.getAssetsPath("logo-small.png"));

		return logoSmallIcon;
	}

	public static ImageIcon getLogoIcon() {
		if (logoIcon == null)
			logoIcon = new ImageIcon(FileHandler.getAssetsPath("logo.png"));

		return logoIcon;
	}

	public static ImageIcon getHomeIcon() {
		if (homeIcon == null)
			homeIcon = new ImageIcon(FileHandler.getAssetsPath("home-icon.png"));

		return homeIcon;
	}

	public static ImageIcon getManageProductIcon() {
		if (manageProductIcon == null)
			manageProductIcon = new ImageIcon(FileHandler.getAssetsPath("manage-product-icon.png"));

		return manageProductIcon;
	}

	public static ImageIcon getManageUserIcon() {
		if (manageUserIcon == null)
			manageUserIcon = new ImageIcon(FileHandler.getAssetsPath("manage-user-icon.png"));

		return manageUserIcon;
	}

	public static ImageIcon getViewCartIcon() {
		if (viewCartIcon == null)
			viewCartIcon = new ImageIcon(FileHandler.getAssetsPath("view-cart-icon.png"));

		return viewCartIcon;
	}

	public static ImageIcon getLogoutIcon() {
		if (logoutIcon == null)
			logoutIcon = new ImageIcon(FileHandler.getAssetsPath("home-icon.png"));

		return logoutIcon;
	}

	public static ImageIcon getAddIcon() {
		if (addIcon == null)
			addIcon = new ImageIcon(FileHandler.getAssetsPath("add-icon.png"));

		return addIcon;
	}

	public static ImageIcon getUpdateIcon() {
		if (updateIcon == null)
			updateIcon = new ImageIcon(FileHandler.getAssetsPath("update-icon.png"));

		return updateIcon;
	}

	public static ImageIcon getDeleteIcon() {
		if (deleteIcon == null)
			deleteIcon = new ImageIcon(FileHandler.getAssetsPath("delete-icon.png"));

		return deleteIcon;
	}

	public static ImageIcon getBannedIcon() {
		if (bannedIcon == null)
			bannedIcon = new ImageIcon(FileHandler.getAssetsPath("banned-icon.png"));

		return bannedIcon;
	}

	public static ImageIcon getGridViewIcon() {
		if (gridViewIcon == null)
			gridViewIcon = new ImageIcon(FileHandler.getAssetsPath("grid-view-icon.png"));

		return gridViewIcon;
	}

	public static ImageIcon getListViewIcon() {
		if (listViewIcon == null)
			listViewIcon = new ImageIcon(FileHandler.getAssetsPath("list-view-icon.png"));

		return listViewIcon;
	}

	public static ImageIcon getSearchIcon() {
		if (searchIcon == null)
			searchIcon = new ImageIcon(FileHandler.getAssetsPath("search-icon.png"));

		return searchIcon;
	}

	public static ImageIcon getPayIcon() {
		if (payIcon == null)
			payIcon = new ImageIcon(FileHandler.getAssetsPath("pay-icon.png"));

		return payIcon;
	}

	public static ImageIcon getIcon() {
		if (searchIcon == null)
			searchIcon = new ImageIcon(FileHandler.getAssetsPath("search-icon.png"));

		return searchIcon;
	}

	public static ArrayList<ImageIcon> getImageSlider() {
		File file = new File(FileHandler.getAssetsPath() + "\\slider-image");
		ArrayList<String> fileNames = FileHandler.getDirectory(file);

		if (imageSlideIcon == null || fileNames.size() != imageSlideIcon.size()) {
			imageSlideIcon = new ArrayList<ImageIcon>();
			for (String fileName : fileNames) {
				ImageIcon imageIcon = new ImageIcon(fileName);
				imageIcon = ImageHandler.getNewIconSize(imageIcon, 780, 390);
				imageSlideIcon.add(imageIcon);
			}
		}

		return imageSlideIcon;
	}

	public static ImageIcon getPrevIcon() {
		if (prevIcon == null)
			prevIcon = new ImageIcon(FileHandler.getAssetsPath("prev-icon.png"));

		return prevIcon;
	}

	public static ImageIcon getNextIcon() {
		if (nextIcon == null)
			nextIcon = new ImageIcon(FileHandler.getAssetsPath("next-icon.png"));

		return nextIcon;
	}

}
