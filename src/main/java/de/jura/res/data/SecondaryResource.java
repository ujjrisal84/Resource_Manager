package de.jura.res.data;

import javax.xml.bind.annotation.XmlAccessType;
	import javax.xml.bind.annotation.XmlAccessorType;
	import javax.xml.bind.annotation.XmlRootElement;


	@XmlRootElement(name="resource")
	@XmlAccessorType (XmlAccessType.FIELD)
	public class SecondaryResource {
		
		private String type;
		private String size;
		private String company;
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		public String getSize() {
			return size;
		}
		
		public void setSize(String size) {
			this.size = size;
		}
		public String getCompany() {
			return company;
		}
		
		public void setCompany(String company) {
			this.company = company;
		}
		
		public void clear(){
			this.company=null;
			this.size=null;
			this.type=null;
		}
		
		public SecondaryResource copy(){
			SecondaryResource res = new SecondaryResource();
			res.setCompany(this.company);
			res.setType(this.type);
			res.setSize(this.size);
			return res;
		}
	}
		