package app.view.admin.product.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.DialogFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.validator.Validator;
import app.validator.rule.ProductNameRule;
import app.validator.rule.ProductPriceRule;
import app.validator.rule.ProductQuantityRule;
import app.validator.rule.parent.Rule;
import util.ColorHandler;
import util.MessageHandler;

@SuppressWarnings("serial")
public class DialogAddProduct extends DialogFactory implements AutoCloseable {

	private static final int BORDER_WEIGHT = 10;
	private static final Color FRAME_COLOR = Color.WHITE;

	private Insets insets;

	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel lblQuantity;
	private JTextField txtQuantity;
	private JButton btnAdd;
	private JButton btnCancel;

	private Callable<Void> callable;

	public DialogAddProduct() {
		initializeComponent();

		getAddButton().addActionListener(addActionListener);
		getCancelButton().addActionListener(cancelActionListener);
	}

	public void setCallable(Callable<Void> callable) {
		this.callable = callable;
	}

	private void initializeComponent() {
		insets = new Insets(BORDER_WEIGHT, BORDER_WEIGHT, BORDER_WEIGHT, BORDER_WEIGHT);
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JPanel jPanel = new JPanel(gridBagLayout);
		jPanel.setBackground(FRAME_COLOR);
		jPanel.setBorder(new EmptyBorder(insets));

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
		 * Price
		 */
		JPanel pricePanel = new JPanel(new BorderLayout());
		pricePanel.setBackground(FRAME_COLOR);
		pricePanel.add(getPriceLabel(), BorderLayout.NORTH);
		pricePanel.add(Box.createVerticalStrut(5));
		pricePanel.add(getPriceTextField(), BorderLayout.SOUTH);
		setPriceConstraint(gridBagConstraints);
		jPanel.add(pricePanel, gridBagConstraints);

		/**
		 * Quantity
		 */
		JPanel quantityPanel = new JPanel(new BorderLayout());
		quantityPanel.setBackground(FRAME_COLOR);
		quantityPanel.add(getQuantityLabel(), BorderLayout.NORTH);
		quantityPanel.add(Box.createVerticalStrut(5));
		quantityPanel.add(getQuantityTextField(), BorderLayout.SOUTH);
		setQuantityConstraint(gridBagConstraints);
		jPanel.add(quantityPanel, gridBagConstraints);

		/**
		 * Add Button
		 */
		setAddConstraint(gridBagConstraints);
		jPanel.add(getAddButton(), gridBagConstraints);

		/**
		 * Cancel Button
		 */
		setCancelConstraint(gridBagConstraints);
		jPanel.add(getCancelButton(), gridBagConstraints);

		this.add(jPanel);
	}

	private void setNameConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setPriceConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setQuantityConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setAddConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private void setCancelConstraint(GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.insets = insets;
	}

	private JLabel getNameLabel() {
		if (lblName == null)
			lblName = LabelFactory.getInstance().create("Name", false);

		return lblName;
	}

	private JTextField getNameTextField() {
		if (txtName == null)
			txtName = TextFieldFactory.getInstance().create(false, null);

		return txtName;
	}

	private JLabel getPriceLabel() {
		if (lblPrice == null)
			lblPrice = LabelFactory.getInstance().create("Price", false);

		return lblPrice;
	}

	private JTextField getPriceTextField() {
		if (txtPrice == null)
			txtPrice = TextFieldFactory.getInstance().create(false, null);

		return txtPrice;
	}

	private JLabel getQuantityLabel() {
		if (lblQuantity == null)
			lblQuantity = LabelFactory.getInstance().create("Quantity", false);

		return lblQuantity;
	}

	private JTextField getQuantityTextField() {
		if (txtQuantity == null)
			txtQuantity = TextFieldFactory.getInstance().create(false, null);

		return txtQuantity;
	}

	private JButton getAddButton() {
		if (btnAdd == null)
			btnAdd = ButtonFactory.getInstance().create("Add");

		return btnAdd;
	}

	private JButton getCancelButton() {
		if (btnCancel == null)
			btnCancel = ButtonFactory.getInstance().create("Cancel", ColorHandler.PRIMARY_SECONDARY);

		return btnCancel;
	}

	@Override
	public void close() throws Exception {
		this.dispose();
	}

	private ActionListener addActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				Rule nameField = new ProductNameRule(getNameTextField());
				Rule priceField = new ProductPriceRule(getPriceTextField());
				Rule quantityField = new ProductQuantityRule(getQuantityTextField());

				if (Validator.validate(nameField, priceField, quantityField)) {
	
					getAddButton().setEnabled(false);
					getCancelButton().setEnabled(false);

					String name = getNameTextField().getText();
					String price = getPriceTextField().getText();
					String quantity = getQuantityTextField().getText();

					ProductController.insertNewProduct(name, Integer.parseInt(price), Integer.parseInt(quantity));
					DialogAddProduct.this.dialogResult = DialogFactory.CONFIRM;
					callable.call();
					
				} else {
					String errorMessage = Validator.getErrorMessages().get(0);
					MessageHandler.error(errorMessage);
				}
				
			} catch (Exception exception) {
			}
		}
	};

	private ActionListener cancelActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				DialogAddProduct.this.dialogResult = DialogFactory.CANCEL;
				DialogAddProduct.this.close();
			} catch (Exception exception) {
			}

		}
	};
}
