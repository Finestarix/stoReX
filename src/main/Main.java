package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import app.controller.ProductController;
import app.controller.UserController;
import app.util.product.ReadProductHandler;
import app.util.user.ReadUserHandler;
import app.view.LoginFrame;

public class Main {

	public Main() {
		JFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}

	public static void main(String[] args) {

		/**
		 * Generate Product Data
		 */
//		WriteProductHandler.generateProductReport("products.pdf");
		
		/**
		 * Generate User Data
		 */
//		WriteUserHandler.generateUserReport("users.json");

		if (ProductController.getTotalProduct() == 0)
			ReadProductHandler.readProductHandler("products.pdf");
		
		if (UserController.getTotalUser() == 0)
			ReadUserHandler.readProductHandler("users.json");

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				new Main();
			}
		};

		EventQueue.invokeLater(runnable);
	}
}
