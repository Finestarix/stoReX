package app.factory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class DialogFactory extends JDialog {

	private static final String DIALOG_TITLE = "stoReX Add/Update Product Dialog";
	private static final Color DIALOG_COLOR = Color.WHITE;
	private static final int DIALOG_WIDTH = 300;
	private static final int DIALOG_HEIGHT = 350;
	
	public final static int INIT = -1;
	public final static int CONFIRM = 0;
	public final static int CANCEL = 1;
	public int dialogResult = INIT;

	public DialogFactory() {
		setTitle(DIALOG_TITLE);
		setBackground(DIALOG_COLOR);
		setLayout(new BorderLayout());
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public int getDialogResult() {
		return dialogResult;
	}

}
