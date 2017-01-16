package de.jura.res.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.UIExtendedDataTable;

import de.jura.login.data.UserSession;
import de.jura.res.data.PrimaryResource;
import de.jura.res.data.SecondaryResource;
import de.jura.res.service.ResourceServiceImplementor;
import de.jura.user.data.User;
import de.jura.util.ExportExcel;
import de.jura.util.SearchFilter;

@ManagedBean(name = "resController")
@RequestScoped
public class ResourceController {

	@ManagedProperty(value = "#{resService}")
	ResourceServiceImplementor resService;

	@ManagedProperty(value = "#{UserSession}")
	UserSession userSession;

	private static final String SELECTION_ALL = "ALL";

	private String rowId;

	private String resId;

	// This list is for displaying primary resources
	// in the main table and also for the search operation
	private List<PrimaryResource> presList = new ArrayList<PrimaryResource>();
	// private ArrayList<PrimaryResource> presList =
	private List<SecondaryResource> secResList;

	private String currentCompanyItem = SELECTION_ALL;
	private String currentFacultyItem = SELECTION_ALL;

	private List<SelectItem> companyList = new ArrayList<SelectItem>();

	/* For the extended data table selection part */
	private Collection<Object> selection;
	private List<SecondaryResource> selectionItems = new ArrayList<SecondaryResource>();

	private List<SelectItem> facultyList = new ArrayList<SelectItem>();

	// don't write anything in the constructor that calls the database
	// functionality

	SearchFilter filter = new SearchFilter();

	// filter.setCompanyFilter("");

	public void setCurrentCompanyItem(String currentCompanyItem) {

		this.currentCompanyItem = currentCompanyItem;

	}

	public String getCurrentCompanyItem() {
		return currentCompanyItem;
	}

	public String getCurrentFacultyItem() {
		return currentFacultyItem;
	}

	public void setCurrentFacultyItem(String currentFacultyItem) {
		this.currentFacultyItem = currentFacultyItem;
	}

	// For company based filter
	public List<SelectItem> getFirstList() {

		SelectItem item = new SelectItem(SELECTION_ALL);
		companyList.clear();
		companyList.add(item);
		for (String company : getDistinctResourceCompany()) {
			item = new SelectItem(company);
			companyList.add(item);
		}

		return companyList;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public void setFacultyList(List<SelectItem> facultyList) {
		this.facultyList = facultyList;
	}

	public List<SelectItem> getFacultyList() {

		SelectItem item = new SelectItem(SELECTION_ALL);
		facultyList.clear();
		facultyList.add(item);

		String[] facultyName = { "edv-team", "cip-pool", "bibliothek",
				"wirtschaftsrecht" };

		for (String name : facultyName) {

			item = new SelectItem(name);
			facultyList.add(item);

		}

		return facultyList;
	}

	public void setCompanyList(List<SelectItem> companyList) {
		this.companyList = companyList;
	}

	public Collection<Object> getSelection() {

		return selection;
	}

	public void setSelection(Collection<Object> rows) {

		this.selection = rows;
	}

	public List<SecondaryResource> getSelectionItems() {
		return selectionItems;
	}

	public ResourceServiceImplementor getResService() {
		return resService;
	}

	public void setResService(ResourceServiceImplementor resService) {
		this.resService = resService;
	}

	public List<SecondaryResource> getSecResList() {
		return secResList;
	}

	// don't write anything in the constructor that calls the database
	// functionality

	public List<PrimaryResource> getPresList() {

		searchAction();
		return presList;

	}

	// This method listens the row selection of main table in welcome_new page

	public void selectionListener(AjaxBehaviorEvent event) {
		System.out.println("I am inside the selection listener");
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();

		Object originalKey = dataTable.getRowKey();
		System.out.println("The original key is:" + dataTable.getRows());
		System.out.println("The getrowkey is:" + dataTable.getRowKey());
		System.out.println("The selection is:" + selection);
		selectionItems.clear();
		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				System.out.println("The size of selection items is:"
						+ selectionItems.size());
				selectionItems
						.addAll(((PrimaryResource) dataTable.getRowData())
								.getSecResList());
				System.out.println("The size of selection items is:"
						+ selectionItems.size());
				System.out.println(selectionItems.get(0).getClass());

			}
		}
		dataTable.setRowKey(originalKey);
	}

	public List<SecondaryResource> getSelectionItem() {
		return selectionItems;
	}

	public List<String> getDistinctResourceCompany() {
		return resService.retrieveDistinctResourceCompany();
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResId() {
		return resId;
	}

	public void searchAction() {

		System.out.println("current comp item:" + currentCompanyItem);
		System.out.println("current faculty item:" + currentFacultyItem);

		if (currentCompanyItem.equals(SELECTION_ALL)) {
			filter.setCompanyFilter("");

		} else {
			filter.setCompanyFilter(currentCompanyItem);

		}

		if (currentFacultyItem.equals(SELECTION_ALL)) {
			filter.setFacultyFilter("");
		} else {
			filter.setFacultyFilter(currentFacultyItem);
		}

		System.out.println("the resId is:" + resId);
		filter.setResourceNameFilter(resId);
		presList = resService.retrieveResource(filter);

	}

	public void exportExcel() {

		List<PrimaryResource> pres = resService
				.retrieveResource(new SearchFilter());

		ExportExcel.exportToExcel(pres);
	}

	public void editAction(AjaxBehaviorEvent event) {

		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();
		System.out.println("+++");
		System.out.println("The row id is:" + rowId);
	}

	public void valueChanged(ValueChangeEvent event) {

		if (null != event.getNewValue()) {

			currentCompanyItem = event.getNewValue().toString();

			System.out.println("The current item is:" + currentCompanyItem);

		}

	}

	public boolean hasRoleEdit() {
		userSession = (UserSession) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSession");
		User currentUser = userSession.getUser();
		System.out.println("checking role edit: "+currentUser); 
		if (currentUser.getRole().getPermissions().equals("edit_resource")) {
			return true;
		}

		return false;
	}

	public boolean hasRoleDelete() {
		User currentUser = userSession.getUser();
		if (currentUser.getRole().getPermissions().equals("delete_resource")) {
			return true;
		}
		return false;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}
}
