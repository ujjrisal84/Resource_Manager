package de.jura.res.dao;

import java.util.List;

import de.jura.res.data.PrimaryResource;
import de.jura.util.SearchFilter;

public interface ResourceDAO {

	public void deleteResource(String resId);

	public int saveResource(PrimaryResource pres);

	public int updateResource(PrimaryResource pres);

	public List<PrimaryResource> getPrimaryResource(SearchFilter filter);

	public List<String> getDistinctResourceCompany();
	
	public PrimaryResource getPrimaryResource(String resId);
	
	
	
	

}
