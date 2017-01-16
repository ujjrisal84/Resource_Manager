/**
 * 
 */
package de.jura.user.service;

import java.util.List;

import de.jura.user.data.User;


public interface UserService {

	/**
	 * Retrieve user with given login name
	 * @param loginName login
	 * @return user
	 */
	public User retrieveUserByLogin(String loginName);
	
	/**
	 * Retrieve user with given id
	 * @param userId user id
	 * @return user with {@code userId}
	 */
	public User retriveUserById(String userId);
	
	/**
	 * Save user
	 * @param user user to save
	 */
	public int saveUser(User user);
	
	public List<User> retrieveAllUsers();
}
