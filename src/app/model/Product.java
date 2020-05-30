package app.model;

import app.annotation.FieldDatabase;

public class Product {

	@FieldDatabase(name = "id")
	private String id;

	@FieldDatabase(name = "name")
	private String name;

	@FieldDatabase(name = "price")
	private int price;

	@FieldDatabase(name = "quantity")
	private int quantity;

	public Product() {
	}

	public Product(String id, String name, int price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
