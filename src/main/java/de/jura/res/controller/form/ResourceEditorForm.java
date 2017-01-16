package de.jura.res.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import de.jura.res.data.PrimaryResource;
import de.jura.res.service.ResourceServiceImplementor;
import de.jura.system.data.Field;
import de.jura.system.service.SystemService;

@ManagedBean(name = "resEditorForm")
public class ResourceEditorForm {

	@ManagedProperty(value = "#{resService}")
	private ResourceServiceImplementor resService;

	@ManagedProperty(value = "#{systemService}")
	private SystemService sysService;

	private PrimaryResource resource;

	private List<Field> fieldList;

	private String rowResId;

	@ManagedProperty(value = "#{param.resourceId}")
	public String resourceId;
	
	@ManagedProperty(value="#{param.action}")
	public String action;

	public String getRowResId() {
		return rowResId;
	}

	public void setRowResId(String rowResId) {
		this.rowResId = rowResId;
	}

	private String resNameItem = null;
	private List<SelectItem> resNameList = new ArrayList<SelectItem>();

	public PrimaryResource getResource() {
		if (resource == null) {

			if (resourceId != null) {
				this.resource = resService.retrievePrimaryResource(resourceId);
				// this.secResList = resource.getSecResList();
			} else {
				resource = new PrimaryResource();
			}
		}

		return resource;
	}

	public String save() {

		String message = "inserted successfully!!";
		String retString = "null";
		
		int retVal = resService.saveResource(resource, fieldList);
		

		if (retVal == 1) {
			
			System.out.println("retval");

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));
			
			retString = "welcome_new?faces-redirect=true";
		}

		return retString;

	}

	public ResourceServiceImplementor getResService() {
		return resService;
	}

	public void setResService(ResourceServiceImplementor resService) {
		this.resService = resService;
	}

	public String cancelAction() {

		System.out.println("I am at cancel action");
		return "welcome_new?faces-redirect=true";

	}

	public void setResource(PrimaryResource resource) {
		this.resource = resource;
	}

	public void setResNameList(List<SelectItem> resNameList) {
		this.resNameList = resNameList;
	}

	public List<SelectItem> getResNameList() {

		SelectItem item;
		resNameList.clear();
		item = new SelectItem(resNameItem);
		resNameList.add(item);
		String[] resName = { "CPU", "Printer", "Scanner", "Tape Drive" };

		for (String name : resName) {

			item = new SelectItem(name);
			resNameList.add(item);

		}// todo enum

		return resNameList;
	}

	public String editAction() {
	

		//this.resource = resService.retrievePrimaryResource(rowResId);
		// System.out.println("the col id:" + this.resource.getResource_id());

		String returnString = "insert_new.xhtml?faces-redirect=true&resourceId="
				+ rowResId + "&action=edit";
		System.out.println("retstr" + returnString);

		return returnString;

	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String updateResource() {
		int retVal = resService.updateResource(this.resource);
		String retString = null;
		if(retVal ==1){
			 retString = "welcome_new.xhtml?faces-redirect=true";
		}
		return retString;
	}

	public List<Field> getFieldList() {
		return this.sysService.getFieldSets();
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public SystemService getSysService() {
		return sysService;
	}

	public void setSysService(SystemService sysService) {
		this.sysService = sysService;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
