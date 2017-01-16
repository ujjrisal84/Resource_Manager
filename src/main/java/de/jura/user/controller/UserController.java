package de.jura.user.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.jura.user.data.User;
import de.jura.user.service.UserServiceImpl;

@ManagedBean(name = "userController")
@RequestScoped
public class UserController {

	@ManagedProperty(value = "#{userService}")
	UserServiceImpl userService;

	private User user;

	public UserController() {
		user = new User();

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String signUp() {

		int retVal;
		String message;
		retVal = userService.saveUser(user);

		if (retVal == 1) {

			message = "inserted successfully!!";

		} else {

			message = "user creation unsuccessful";

		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(message));

		return "signup.xhtml?faces-redirect=true";
	}

	// public boolean deleteUser() {
	//
	// boolean success = false;
	// success = HardwareDatabaseImplementor.getInstance().deleteUser(user);
	//
	// return success;
	//
	// }

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	public List<User> getAllUsers(){
		
		return userService.retrieveAllUsers();
		
		
		
	
	}

}
