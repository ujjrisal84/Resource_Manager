package de.jura.role.data;

import java.util.HashSet;
import java.util.Set;

public class Role {

	int id;
	
	String name;
	
	Set<Permission> permissions=new HashSet<Permission>();
	
	public Role() {
	}
	
	public Role(int id,String name) {
		this.id=id;
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
