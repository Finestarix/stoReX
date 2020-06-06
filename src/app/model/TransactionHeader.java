package app.model;

import java.util.Date;

import app.annotation.FieldDatabase;

public class TransactionHeader {

	@FieldDatabase(name = "id")
	private String id;

	@FieldDatabase(name = "user_id")
	private String userID;

	@FieldDatabase(name = "date")
	private Date date;

	@FieldDatabase(name = "report_name")
	private String reportName;

	public TransactionHeader() {
	}

	public TransactionHeader(String id, String userID, Date date, String reportName) {
		super();
		this.id = id;
		this.userID = userID;
		this.date = date;
		this.reportName = reportName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

}
