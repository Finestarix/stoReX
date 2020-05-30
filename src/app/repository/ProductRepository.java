package app.repository;

import java.util.ArrayList;

import app.model.Product;
import app.repository.parent.Repository;

public class ProductRepository extends Repository<Product> {
	
	public final static int ITEM_LOAD = 20;
	
	private final static ArrayList<Product> products = new ArrayList<Product>();
	
	
}
