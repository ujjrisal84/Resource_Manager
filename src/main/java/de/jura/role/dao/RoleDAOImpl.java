package de.jura.role.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.role.data.Permission;
import de.jura.role.data.Role;

@Component("roleDAO")
public class RoleDAOImpl extends BaseDAO implements RoleDAO {

	@Override
	public List<Role> retrieveRole() {	
		return null;
	}

	@Override
	public int saveRole(Role role) {

		String strSql = "insert into tbl_role(name) values (?)";
		
		int toReturn;
		
		int retVal1 = 0;

		int retVal = this.jdbcTemplate.update(strSql,
				new Object[] { role.getName() });
		
		System.out.println("retval is:"+retVal);

//		Iterator<Permission> setIterator = role.getPermissions().iterator();

		for (Permission permission : role.getPermissions()) {

			String sqlString = "insert into role_permission (role_id,permission_id) " +
					"(select r.id,p.id from tbl_role r, tbl_permission p where r.name=? and p.permission_name=?)";
			
			retVal1 = this.jdbcTemplate.update(sqlString,
					new Object[] { role.getName(), permission.getName() });
		}
			
		
//		while(setIterator.hasNext()){
//			String sqlString = "insert into role_permission (role_id,permission_id) " +
//					"(select r.id,p.id from tbl_role r, tbl_permission p where r.name=? and p.permission_name=?)";
//			
//			int retVal1 = this.jdbcTemplate.update(sqlString,
//					new Object[] { role.getName(), setIterator.next() });
//
//		}
		
		if(retVal==1 && retVal1 ==1){
			
			toReturn = 1;
			
		}else {
			toReturn = 0;
		}
	
	return toReturn;
	
	}

	@Override
	public void deleteRole() {

	}

	@Override
	public void updateRole() {

	}

	public List<Permission> getPermission() {

		String sqlString = "select permission_name from tbl_permission";

		return (List<Permission>) this.jdbcTemplate.queryForList(sqlString,
				String.class);

	}
	
	
	public List<String> getAllRoles() {

		String sqlString = "select name from tbl_role";

		return (List<String>) this.jdbcTemplate.queryForList(sqlString,
				String.class);

	}

	@Override
	public List<Permission> getPermissionByRole(int roleId) {
		String strSql = "select permission_name from tbl_permission where id in(select role_id from role_permission where role_id=?)";
		
		RowMapper mapper = new RowMapper() {			
			@Override
			public Permission mapRow(ResultSet rs, int arg1) throws SQLException {
				Permission permission = new Permission(rs.getString(0));
				return permission;
			}
		};
		
		List<Permission> permissions = (List<Permission>)this.jdbcTemplate.query(strSql,new Object[]{roleId}, mapper);
		return permissions;
	}
	

}
