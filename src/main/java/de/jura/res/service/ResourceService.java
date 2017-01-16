package de.jura.res.service;

import java.util.List;

import de.jura.res.data.PrimaryResource;
import de.jura.system.data.Field;
import de.jura.util.SearchFilter;

public interface ResourceService {

	public void deleteResource(String resID);

	public int saveResource(PrimaryResource pres, List<Field> fieldList);

	public int updateResource(PrimaryResource pres);

	public List<String> retrieveDistinctResourceCompany();

	public List<PrimaryResource> retrieveResource(SearchFilter filter);

	public PrimaryResource retrievePrimaryResource(String resId);

}
