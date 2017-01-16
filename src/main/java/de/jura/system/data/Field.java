package de.jura.system.data;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean (name="field")
@RequestScoped

@XmlRootElement(name = "field")
@XmlAccessorType (XmlAccessType.FIELD)
public class Field {
	
	private int id;
	private String name;
	private String type;
	private int fieldSet_Id;
	private String value;
	
	@XmlElement(name="fields")
	private List<Field> fields;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getFieldSet_Id() {
		return fieldSet_Id;
	}
	public void setFieldSet_Id(int fieldset_Id) {
		this.fieldSet_Id = fieldset_Id;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
}
