package de.jura.login.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import de.jura.user.data.User;
import de.jura.user.service.UserServiceImpl;

@ManagedBean(name="loginController")
@RequestScoped
public class LoginController_New {
	
	private String userName;
	
	private String password;
	
	boolean isLogged = false;
	
	@ManagedProperty(value="#{userService}") 
	UserServiceImpl userService;
	
	public LoginController_New() {
		
	}

	public String login() {

		User user = userService.retrieveUserByLogin(getUserName());
		
		if(null==user){			
			return "login.xhtml?faces-redirect=true";
		}
			
		
		if(user.getUsername().equals(getUserName()) && user.getPassword().equals(getPassword())){
			isLogged = true;
			return "welcome_new.xhtml?faces-redirect=true";
		}
		
		return "login.xhtml?faces-redirect=true";
	}
	
	public boolean Logout(){
		boolean success = false;
//		success =  HardwareDatabaseImplementor.getInstance().Logout(user);
		return success;
			}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

}
