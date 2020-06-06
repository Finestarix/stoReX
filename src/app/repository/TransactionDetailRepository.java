package app.repository;

import app.model.TransactionDetail;
import app.repository.parent.Repository;

public class TransactionDetailRepository extends Repository<TransactionDetail> {

	private static final String TABLE_NAME = "transaction_detail";

	public static void insertTransactioDetail(TransactionDetail transactionDetail) {
		String query = String.format("INSERT INTO %s (transaction_id, product_id, quantity) VALUES(?, ?, ?)",
				TABLE_NAME);
		ProductRepository.executeUpdate(query, transactionDetail.getTransactionID(), transactionDetail.getProductID(),
				Integer.toString(transactionDetail.getQuantity()));
	}

}
