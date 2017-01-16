package de.jura.res.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.xml.bind.JAXBException;

import de.jura.res.dao.ResourceDAOImplementor;
import de.jura.res.data.PrimaryResource;
import de.jura.system.data.Field;
import de.jura.util.SearchFilter;
import de.jura.util.XMLParser;

@ManagedBean(name = "resService")
public class ResourceServiceImplementor implements ResourceService {

	@ManagedProperty(value = "#{resDao}")
	private ResourceDAOImplementor resDao;

	public ResourceDAOImplementor getResDao() {
		return resDao;
	}

	public void setResDao(ResourceDAOImplementor resDao) {
		this.resDao = resDao;
	}

	@Override
	public List<PrimaryResource> retrieveResource(SearchFilter filter) {

		return resDao.getPrimaryResource(filter);
	}

	@Override
	public PrimaryResource retrievePrimaryResource(String resId) {
		// TODO Auto-generated method stub
		return resDao.getPrimaryResource(resId);
	}

	@Override
	public void deleteResource(String resID) {

	}

	@Override
	public int updateResource(PrimaryResource pres) {
		int retVal = resDao.updateResource(pres);
		return retVal;

	}

	@Override
	public List<String> retrieveDistinctResourceCompany() {

		return resDao.getDistinctResourceCompany();
	}

	@Override
	public int saveResource(PrimaryResource pres, List<Field> fieldList) {

		Field field = new Field();
		field.setFields(fieldList);
		String settings;
		try {
			settings = XMLParser.getXML(field);
			pres.setSettings(settings);
		} catch (JAXBException e) {

			e.printStackTrace();
		}

		int retVal = resDao.saveResource(pres);

		return retVal;
	}

}
