package app.controller;

import java.util.Date;
import java.util.UUID;

import app.model.TransactionHeader;
import app.repository.TransactionHeaderRepository;

public class TransactionHeaderController {

	public static TransactionHeader insertTransactionHeader(String userID, String reportName) {
		String id = UUID.randomUUID().toString();
		Date date = new Date();
		
		TransactionHeader transactionHeader = new TransactionHeader(id, userID, date, reportName);
		TransactionHeaderRepository.insertTransactionHeader(transactionHeader);
		
		return transactionHeader;
	}
	
}
