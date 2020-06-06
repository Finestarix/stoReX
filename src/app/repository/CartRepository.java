package app.repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

import app.model.Cart;

public class CartRepository {

	private static ArrayList<Cart> carts = new ArrayList<Cart>();

	public static ArrayList<Cart> getAllCarts() {
		return carts;
	}

	public static ArrayList<Cart> getAllCarts(String searchCondition) {
		return (ArrayList<Cart>) carts.stream()
				.filter(cart -> cart.getName().toLowerCase().contains(searchCondition.toLowerCase()))
				.collect(Collectors.toList());
	}

	public static Cart getCartByID(String id) {
		for (Cart cart : carts)
			if (cart.getId().equals(id))
				return cart;
		return null;
	}
	
	public static int getTotalCart() {
		return carts.size();
	}

	public static boolean isCartExist(String id) {
		for (Cart cart : carts)
			if (cart.getId().equals(id))
				return true;
		return false;
	}

	public static void addNewCart(Cart cart) {
		carts.add(cart);
	}

	public static void updateCart(String id, int quantity) {
		for (Cart cart : carts)
			if (cart.getId().equals(id)) {
				int oldQuantity = cart.getQuantity();
				cart.setQuantity(oldQuantity + quantity);
			}
	}

	public static void removeCart(Cart cart) {
		carts.remove(cart);
	}

	public static void removeAllCart() {
		carts.clear();
	}

}
