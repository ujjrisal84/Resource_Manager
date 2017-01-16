/**
 * 
 */
package de.jura.user.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.jura.user.dao.UserDAOImpl;
import de.jura.user.data.User;


@ManagedBean(name = "userService")
public class UserServiceImpl implements UserService{
	
	@ManagedProperty(value="#{userDao}")
	private UserDAOImpl userDao;
	
	/* (non-Javadoc)
	 * @see de.jura.user.service.UserService#retriveUserByLogin(java.lang.String)
	 */
	@Override
	public User retrieveUserByLogin(String loginName) {
		return userDao.getUserByLogin(loginName);
	}

	/* (non-Javadoc)
	 * @see de.jura.user.service.UserService#retriveUserById(java.lang.String)
	 */
	@Override
	public User retriveUserById(String userId) {
		return userDao.getUser(userId);
	}

	/* (non-Javadoc)
	 * @see de.jura.user.service.UserService#saveUser(de.jura.user.data.User)
	 */
	@Override
	public int saveUser(User user) {
		return userDao.saveUser(user);
		
	}

	public void setUserDao(UserDAOImpl userDao) {
		this.userDao = userDao;
	}
	
	public List<User> retrieveAllUsers(){
		return userDao.getAllUsers();
	}

}
