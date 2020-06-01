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
		String query = String.format("SELECT * FROM %s", TABLE_NAME);
		ResultSet resultSet = Repository.executeQuery(query);
		return Repository.toObject(Product.class, resultSet);
	}

	public static ArrayList<Product> getProductsPerPage(int currentLoadedPage) {
		String query = String.format("SELECT * FROM %s LIMIT %d,%d", TABLE_NAME, (currentLoadedPage - 1) * ITEM_LOAD, ITEM_LOAD);
		System.out.println(query);
		ResultSet result = Repository.executeQuery(query);
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
			e.printStackTrace();
		}

		return -1;
	}
}
