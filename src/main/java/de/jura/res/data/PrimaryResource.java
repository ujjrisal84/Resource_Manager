package de.jura.res.data;

import java.util.ArrayList;
import java.util.List;

public class PrimaryResource {

	private int id;
	private String resource_id;
	private String resource_name;
	private String resource_type;
	private String resource_company;
	private String installation_date;
	private String ip_address;
	private String mac_address;
	private String faculty;
	private String settings;
	
	private  List<SecondaryResource> secResList;

	public List<SecondaryResource> getSecResList() {
		if(secResList==null){
			System.out.println("I am at secreslist null");
			secResList = new ArrayList<SecondaryResource>();
		}
		System.out.println("I am at secreslist not null: "+secResList.size());
		return secResList;
	}

	public void setSecResList(List<SecondaryResource> secResList) {
		this.secResList = secResList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getResource_company() {
		return resource_company;
	}

	public void setResource_company(String resource_company) {
		this.resource_company = resource_company;
	}

	public String getInstallation_date() {
		return installation_date;
	}

	public void setInstallation_date(String installation_date) {
		this.installation_date = installation_date;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}
	
	

	/*
	 * public void setInstallation_date(String installation_date) {
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd"); Date
	 * date; try { date = formatter.parse(installation_date);
	 * this.installation_date = date; } catch (ParseException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}