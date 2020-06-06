package app.controller;

import app.model.TransactionDetail;
import app.repository.TransactionDetailRepository;

public class TransactionDetailController {

	public static void insertTransactionDetail(String transactionID, String productID, int quantity) {
		TransactionDetail transactionDetail = new TransactionDetail(transactionID, productID, quantity);
		TransactionDetailRepository.insertTransactioDetail(transactionDetail);
	}
	
}
