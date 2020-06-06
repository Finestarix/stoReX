package app.model;

import app.annotation.FieldDatabase;

public class TransactionDetail {

	@FieldDatabase(name = "transaction_id")
	private String transactionID;

	@FieldDatabase(name = "product_id")
	private String productID;

	@FieldDatabase(name = "quantity")
	private int quantity;

	public TransactionDetail() {
	}

	public TransactionDetail(String transactionID, String productID, int quantity) {
		super();
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
