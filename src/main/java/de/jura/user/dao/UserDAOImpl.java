/**
 * 
 */
package de.jura.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.role.data.Role;
import de.jura.user.data.User;


@Component("userDao")
public class UserDAOImpl extends BaseDAO implements UserDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jura.user.dao.UserDAO#getUserByLogin(java.lang.String)
	 */
	@Override
	public User getUserByLogin(String login) {
		String strSql = "select * from tbl_user where username=?";

		RowMapper mapper = new RowMapper() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException { 
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setRoleId(rs.getInt(6));
				return user;
			} 
				
		};
			
			

		User user = (User) this.jdbcTemplate.queryForObject(strSql,
				new Object[] { login }, mapper); 

		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jura.user.dao.UserDAO#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jura.user.dao.UserDAO#deleteUser(java.lang.String)
	 */
	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jura.user.dao.UserDAO#saveUser(de.jura.user.data.User)
	 */
	@Override
	public int saveUser(User user) {
		// String strSql =
		// "insert into tbl_user( username, password, role, name, email) values(?,?,?,?,?)";

		String strSql = "insert into tbl_user(username,password,name,email,role) (select ?,?,?,?, id from tbl_role where name='"
				+ user.getRole() + "'" + ")";

		System.out.println(strSql);

		int retVal = this.jdbcTemplate.update(
				strSql,
				new Object[] { user.getUsername(), user.getPassword(),
						user.getName(), user.getEmail() });
		return retVal;

		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.jura.user.dao.UserDAO#updateUser(de.jura.user.data.User)
	 */
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	public List<User> getAllUsers() {

		// String sqlString = "select * from tbl_user";

		String sqlString = "select tbl_user.username, tbl_user.name,"
				+ " tbl_user.email, tbl_role.id,tbl_role.name as role from tbl_user "
				+ "left join tbl_role on tbl_user. role = tbl_role.id order by tbl_user.name";

		System.out.println("The sqlString is:" + sqlString);

		RowMapper mapper = new RowMapper() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setUsername(rs.getString(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setRole(new Role(rs.getInt(4),rs.getString(5)));
				return user;

			}

		};

		List<User> userList = (List<User>) this.jdbcTemplate.query(sqlString,
				mapper);

		System.out.println("The size of list is:" + userList.size());
		return userList;

	}

}
