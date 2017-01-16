package de.jura.role.service;

import java.util.List;

import de.jura.role.data.Permission;
import de.jura.role.data.Role;

public interface RoleService {
	
	public List<Role> getRole();
	public Role getRole(int id);
	public int saveRole(Role role);
	public void deleteRole(int roleId);
	public void updateRole();
	public List<Permission> getPermission();
	public List<String> retrieveAllRoles();
	
	public List<Permission> retrievePermissionByRole(int roleId );

}
