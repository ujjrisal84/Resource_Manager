package de.jura.res.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "resources")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecondaryResources {
	
	@XmlElement(name="resource")
	private List<SecondaryResource> resList = null;

	public List<SecondaryResource> getResources() {
		return resList;
	}

	
	public void setResources(List<SecondaryResource> resList) {

		this.resList = resList;

	}
}
