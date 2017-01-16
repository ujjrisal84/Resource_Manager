package de.jura.role.dao;

import java.util.List;

import de.jura.role.data.Permission;
import de.jura.role.data.Role;

public interface RoleDAO {
	
	public List<Role> retrieveRole();
	public int saveRole(Role role);
	public void deleteRole();
	public void updateRole();
	public List<Permission> getPermission();
	public List<Permission> getPermissionByRole(int roleId);
	
	public List<String> getAllRoles();

}
