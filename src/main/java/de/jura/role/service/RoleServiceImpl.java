package de.jura.role.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import de.jura.role.dao.RoleDAOImpl;
import de.jura.role.data.Permission;
import de.jura.role.data.Role;

@ManagedBean(name = "roleService")
public class RoleServiceImpl implements RoleService {

	@ManagedProperty(value = "#{roleDAO}")
	private RoleDAOImpl roleDao;

	public RoleDAOImpl getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAOImpl roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> getRole() {

		return null;
	}

	@Override
	public int saveRole(Role role) {

		return roleDao.saveRole(role);
	}

	@Override
	public void deleteRole(int roleId) {

	}

	@Override
	public void updateRole() {

	}

	@Override
	public List<Permission> getPermission() {

		return roleDao.getPermission();
	}

	public List<String> retrieveAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public Role getRole(int id) {
		// return roleDao.getRoleById(id);
		return null;
	}

	@Override
	public List<Permission> retrievePermissionByRole(int roleId) {
		return this.roleDao.getPermissionByRole(roleId);
		
	}

}
