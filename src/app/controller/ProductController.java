package app.controller;

import java.util.ArrayList;

import app.model.Product;
import app.repository.ProductRepository;

public class ProductController {

	public static ArrayList<Product> getAllProduct() {
		return ProductRepository.getAllProducts();
	}
	
	public static ArrayList<Product> getProductsAlreadyLoaded(int currentLoadedItem) {
		return ProductRepository.getProductsAlreadyLoaded(currentLoadedItem);
	}

	public static ArrayList<Product> getProductsPerPage(int currentPageLoaded) {
		return ProductRepository.getProductsPerPage(currentPageLoaded);
	}

	public static int getTotalProduct() {
		return ProductRepository.getTotalProduct();
	}
}
