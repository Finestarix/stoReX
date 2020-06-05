package app.controller;

import java.util.ArrayList;

import app.model.Cart;
import app.repository.CartRepository;

public class CartController {

	public static ArrayList<Cart> getAllCarts() {
		return CartRepository.getAllCarts();
	}
	
	public static ArrayList<Cart> getAllCarts(String searchCondition) {
		return CartRepository.getAllCarts(searchCondition);
	}
	
	public static Cart getCartByID(String id) {
		return CartRepository.getCartByID(id);
	}
	
	public static boolean isCartExist(String id) {
		return CartRepository.isCartExist(id);
	}
	
	public static void addNewCart(String id, String name, int price, int quantity) {
		Cart cart = new Cart(id, name, price, quantity);
		CartRepository.addNewCart(cart);
	}
	
	public static void updateCart(String id, int quantity) {
		CartRepository.updateCart(id, quantity);
	}
	
	public static void removeCart(Cart cart) {
		CartRepository.removeCart(cart);
	}
	
	public static void removeAllCart() {
		CartRepository.removeAllCart();
	}
	
}
