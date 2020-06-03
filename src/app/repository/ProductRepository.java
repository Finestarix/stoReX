package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.model.Product;
import app.repository.parent.Repository;

public class ProductRepository extends Repository<Product> {

	public static final int ITEM_LOAD = 20;
	private static final String TABLE_NAME = "products";

	public static ArrayList<Product> getAllProducts() {
		String query = String.format("SELECT * FROM %s ORDER BY created_at", TABLE_NAME);
		ResultSet resultSet = Repository.executeQuery(query);
		return Repository.toObject(Product.class, resultSet);
	}

	public static ArrayList<Product> getProductsAlreadyLoaded(int currentLoadedItem) {
		String query = String.format("SELECT * FROM %s ORDER BY created_at LIMIT %d", TABLE_NAME, currentLoadedItem);
		ResultSet result = Repository.executeQuery(query);
		ArrayList<Product> loadedProducts = Repository.toObject(Product.class, result);
		return loadedProducts;
	}
	
	public static ArrayList<Product> getProductsAlreadyLoaded(int currentLoadedItem, String searchCondition) {
		String query = String.format("SELECT * FROM %s WHERE name LIKE ? ORDER BY created_at LIMIT %d", TABLE_NAME, currentLoadedItem);
		ResultSet result = Repository.executeQuery(query, "%" + searchCondition + "%");
		ArrayList<Product> loadedProducts = Repository.toObject(Product.class, result);
		return loadedProducts;
	}

	public static ArrayList<Product> getProductsPerPage(int currentLoadedPage) {
		String query = String.format("SELECT * FROM %s ORDER BY created_at LIMIT %d,%d", TABLE_NAME,
				(currentLoadedPage - 1) * ITEM_LOAD, ITEM_LOAD);
		ResultSet result = Repository.executeQuery(query);
		ArrayList<Product> loadedProducts = Repository.toObject(Product.class, result);
		return loadedProducts;
	}
	
	public static ArrayList<Product> getProductsPerPage(int currentLoadedPage, String searchCondition) {
		String query = String.format("SELECT * FROM %s WHERE name LIKE ? ORDER BY created_at LIMIT %d,%d", TABLE_NAME,
				(currentLoadedPage - 1) * ITEM_LOAD, ITEM_LOAD);
		System.out.println(query);
		ResultSet result = Repository.executeQuery(query, "%" + searchCondition + "%");
		ArrayList<Product> loadedProducts = Repository.toObject(Product.class, result);
		return loadedProducts;
	}

	public static int getTotalProduct() {
		try {
			String query = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME);
			ResultSet result = Repository.executeQuery(query);
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
		}

		return -1;
	}
	
	public static int getTotalProduct(String searchCondition) {
		try {
			String query = String.format("SELECT COUNT(*) FROM %s WHERE name LIKE ?", TABLE_NAME);
			ResultSet result = Repository.executeQuery(query, "%" + searchCondition + "%");
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
		}

		return -1;
	}


	public static void insertProduct(Product product) {
		String query = String.format("INSERT INTO %s (id, name, price, quantity) VALUES(?, ?, ?, ?)", TABLE_NAME);
		ProductRepository.executeUpdate(query, product.getId(), product.getName(), Integer.toString(product.getPrice()),
				Integer.toString(product.getQuantity()));
	}

	public static void updateProduct(Product product) {
		String query = String.format("UPDATE %s SET name = ?, price = ?, quantity = ? WHERE id LIKE ?", TABLE_NAME);
		ProductRepository.executeUpdate(query, product.getName(), Integer.toString(product.getPrice()),
				Integer.toString(product.getQuantity()), product.getId());
	}

	public static void deleteProduct(Product product) {
		String query = String.format("DELETE FROM %s WHERE id LIKE ?", TABLE_NAME);
		ProductRepository.executeUpdate(query, product.getId());
	}

}
