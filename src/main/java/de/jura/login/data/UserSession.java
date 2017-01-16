package de.jura.login.data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import de.jura.user.data.User;

@ManagedBean(name="UserSession")
@SessionScoped
public class UserSession {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
