package app.controller;

import java.util.ArrayList;
import java.util.UUID;

import app.model.Product;
import app.repository.ProductRepository;

public class ProductController {

	public static ArrayList<Product> getAllProduct() {
		return ProductRepository.getAllProducts();
	}
	
	public static ArrayList<Product> getProductsAlreadyLoaded(int currentLoadedItem) {
		return ProductRepository.getProductsAlreadyLoaded(currentLoadedItem);
	}
	
	public static ArrayList<Product> getProductsAlreadyLoaded(int currentLoadedItem, String searchCondition) {
		return ProductRepository.getProductsAlreadyLoaded(currentLoadedItem, searchCondition);
	}

	public static ArrayList<Product> getProductsPerPage(int currentPageLoaded) {
		return ProductRepository.getProductsPerPage(currentPageLoaded);
	}
	
	public static ArrayList<Product> getProductsPerPage(int currentPageLoaded, String searchCondition) {
		return ProductRepository.getProductsPerPage(currentPageLoaded, searchCondition);
	}

	public static int getTotalProduct() {
		return ProductRepository.getTotalProduct();
	}
	
	public static int getTotalProduct(String searchCondition) {
		return ProductRepository.getTotalProduct(searchCondition);
	}
	
	public static void insertNewProduct(String name, int price, int quantity) {
		String id = UUID.randomUUID().toString();
		Product product = new Product(id, name, price, quantity);
		ProductRepository.insertProduct(product);
	}
	
	public static void updateProduct(Product product) {
		ProductRepository.updateProduct(product);;
	}
	
	public static void deleteProduct(Product product) {
		ProductRepository.deleteProduct(product);
	}
}
