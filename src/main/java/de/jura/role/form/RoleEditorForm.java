package de.jura.role.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import de.jura.role.data.Permission;
import de.jura.role.data.Role;
import de.jura.role.service.RoleServiceImpl;

@ManagedBean(name = "rolEditFrm")
@RequestScoped
public class RoleEditorForm {

	@ManagedProperty(value = "#{roleService}")
	private RoleServiceImpl roleServiceImpl;

	private Role role;

	private List<String> selectedPermissions = new ArrayList<String>();

	public Role getRole() {

		if (null == role) {

			role = new Role();
		}

		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Permission> getAllPermissions() {

		return roleServiceImpl.getPermission();
	}

	// public void setPermissionSet(List<Permission> permissionSet) {
	// this.permissionSet = permissionSet;
	// }

	public RoleServiceImpl getRoleServiceImpl() {
		return roleServiceImpl;
	}

	public void setRoleServiceImpl(RoleServiceImpl roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

	public String save() {

		String message = null;

		for (String permission : getSelectedPermissions()) {
			getRole().getPermissions().add(new Permission(permission));
		}
		int retval = this.roleServiceImpl.saveRole(this.role);

		if (retval == 0) {
			message = "can't create role";

		} else {

			message = "user creation successful";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(message));

		return "role.xhtml?faces-redirect=true";
	}

	public void delete() {

	}

	public List<String> getSelectedPermissions() {
		return selectedPermissions;
	}

	public void setSelectedPermissions(List<String> selectedPermissions) {
		this.selectedPermissions = selectedPermissions;
	}

	public List<String> getAllRoles() {
		return roleServiceImpl.retrieveAllRoles();
	}
}
