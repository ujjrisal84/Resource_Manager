/**
 * 
 */
package de.jura.user.dao;

import java.util.List;

import de.jura.user.data.User;

/**
 * @author Ujjwal
 *
 */
public interface UserDAO {

	/**
	 * Returns user with given login name
	 * @param login login name
	 * @return user having login name {@code login}
	 */
	public User getUserByLogin(String login);
	
	
	/**
	 * Returns user with given user id
	 * @param id user id
	 * @return user with {@code id}
	 */
	public User getUser(String id);
	
	/**
	 * Delete user with given id
	 * @param id user id
	 */
	public void deleteUser(String id);
	
	/**
	 * Save given user
	 * @param user user to save
	 */
	public int saveUser(User user);
	
	/**
	 * Udpate given user
	 * @param user user to update
	 */	
	public void updateUser(User user);
	
	public List<User> getAllUsers();
	
	
}
