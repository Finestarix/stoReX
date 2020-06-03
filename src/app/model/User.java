package app.model;

import app.annotation.FieldDatabase;

public class User {

	@FieldDatabase(name = "id")
	private String id;

	@FieldDatabase(name = "name")
	private String name;

	@FieldDatabase(name = "email")
	private String email;

	@FieldDatabase(name = "password")
	private String password;

	@FieldDatabase(name = "role")
	private String role;

	@FieldDatabase(name = "status")
	private String status;

	public User() {
	}

	public User(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = "User";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
