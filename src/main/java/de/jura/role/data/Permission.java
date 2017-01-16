package de.jura.role.data;

public class Permission {
	
	private int id;
	private String name;
	
	public Permission(String name) {
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
	public void setName(String permission_Name) {
		this.name = permission_Name;
	}

}
