package session;

import app.model.User;

public class UserSession {

	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		UserSession.user = user;
	}

}
