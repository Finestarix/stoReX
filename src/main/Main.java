package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import app.view.LoginFrame;

public class Main {

	public Main() {
		JFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}

	public static void main(String[] args) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				new Main();
			}
		};

		EventQueue.invokeLater(runnable);
	}
}
