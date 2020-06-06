package app.repository;

import app.model.TransactionHeader;
import app.repository.parent.Repository;

public class TransactionHeaderRepository extends Repository<TransactionHeader> {

	private static final String TABLE_NAME = "transaction_header";

	public static void insertTransactionHeader(TransactionHeader transactionHeader) {
		String query = String.format("INSERT INTO %s (id, user_id, report_name) VALUES(?, ?, ?)", TABLE_NAME);
		ProductRepository.executeUpdate(query, transactionHeader.getId(), transactionHeader.getUserID(),
				transactionHeader.getReportName());
	}

}
