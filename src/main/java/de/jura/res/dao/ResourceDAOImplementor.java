package de.jura.res.dao;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.res.data.PrimaryResource;
import de.jura.res.data.SecondaryResource;
import de.jura.res.data.SecondaryResources;
import de.jura.util.SearchFilter;
import de.jura.util.XMLParser;

@Component("resDao")
public class ResourceDAOImplementor extends BaseDAO implements ResourceDAO {

	private SecondaryResource secRes;
	private FileOutputStream fileOut;

	// private Date instDate;

	@Override
	public void deleteResource(String resId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int saveResource(PrimaryResource pres) {
		String strSql = "insert into tbl_resource( resource_id, resource_name, resource_type, "
				+ "resource_company, settings, ip_address, mac_address, faculty) values(?,?,?,?,?,?,?,?)";

		

		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");
		System.out.println("The installation date is:"
				+ pres.getInstallation_date());

		int retVal = this.jdbcTemplate.update(
				strSql,
				new Object[] { pres.getResource_id(), pres.getResource_name(),
						pres.getResource_type(), pres.getResource_company(),
						 pres.getSettings(),
						pres.getIp_address(), pres.getMac_address(),
						pres.getFaculty() });
		return retVal;

	}

	@Override
	public PrimaryResource getPrimaryResource(String resId) {

		String strSql = "select * from tbl_resource where resource_id='"
				+ resId + "'";
		System.out.println("The strsql is:" + strSql);

		RowMapper mapper = new RowMapper() {

			@Override
			public PrimaryResource mapRow(ResultSet rs, int arg1)
					throws SQLException {

				PrimaryResource primary_Resource = new PrimaryResource();
				primary_Resource.setResource_id(rs.getString(2));
				primary_Resource.setResource_name(rs.getString(3));
				primary_Resource.setResource_type(rs.getString(4));
				primary_Resource.setResource_company(rs.getString(5));

				//primary_Resource.setInstallation_date(rs.getDate(6).toString());
				primary_Resource.setIp_address(rs.getString(8));
				primary_Resource.setMac_address(rs.getString(9));
				primary_Resource.setFaculty(rs.getString(10));

				java.sql.Blob id = rs.getBlob(7);
				byte[] bdata = id.getBytes(1, (int) (id.length()));

				String s = new String(bdata);

				return primary_Resource;
			}

		};

		PrimaryResource pres = (PrimaryResource) this.jdbcTemplate
				.queryForObject(strSql, mapper);

		return pres;

	}

	@Override
	public List<PrimaryResource> getPrimaryResource(SearchFilter filter) {

		String sql = null;

		System.out.println("I am here");

		String where = " where 1=1 ";
		// company filter
		if (filter.getCompanyFilter() != null
				&& filter.getCompanyFilter() != "") {

			where += " and resource_company='" + filter.getCompanyFilter()
					+ "'";
		}

		// resource id
		if (filter.getResourceNameFilter() != null
				&& !filter.getResourceNameFilter().isEmpty()) {
			where += " and resource_id like'" + filter.getResourceNameFilter()
					+ "%" + "'";
		}

		// faculty filter
		if (filter.getFacultyFilter() != null
				&& !filter.getFacultyFilter().isEmpty()) {
			where += " and faculty='" + filter.getFacultyFilter() + "'";
		}

		sql = "select * from tbl_resource " + where;
		System.out.println("sql:" + sql);

		RowMapper mapper = new RowMapper() {

			public PrimaryResource mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				PrimaryResource primary_Resource = new PrimaryResource();

				primary_Resource.setResource_id(rs.getString(2));
				primary_Resource.setResource_name(rs.getString(3));
				primary_Resource.setResource_type(rs.getString(4));
				primary_Resource.setResource_company(rs.getString(5));
				// DateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
				System.out.println("The date is:" + rs.getDate(6));
				//primary_Resource.setInstallation_date(rs.getDate(6).toString());
				primary_Resource.setIp_address(rs.getString(8));
				primary_Resource.setMac_address(rs.getString(9));
				primary_Resource.setFaculty(rs.getString(10));

				java.sql.Blob id = rs.getBlob(7);
				byte[] bdata = id.getBytes(1, (int) (id.length()));

				String s = new String(bdata);

//				try {
//
//					(XMLParser.parseResource(s).getFields());
//
//				} catch (UnmarshalException e) {
//
//					e.printStackTrace();
//				} catch (JAXBException e) {
//
//					e.printStackTrace();
//				}

				return primary_Resource;

			}

		};

		List<PrimaryResource> priResList = (List<PrimaryResource>) this.jdbcTemplate
				.query(sql, mapper);
		System.out.println("The size of primary resource list is"
				+ priResList.size());

		return priResList;

	}

	@Override
	public List<String> getDistinctResourceCompany() {

		String sqlQuery = "select distinct resource_company from tbl_resource";

		List<String> companyList = (List<String>) jdbcTemplate.queryForList(
				sqlQuery, String.class);
		System.out.println("the size is" + companyList.size());
		return companyList;
	}

	@Override
	public int updateResource(PrimaryResource pres) {
		
		System.out.println("We are going to update");

		String updateQuery = "update tbl_resource set resource_name=?, resource_type=?,resource_company=? where resource_id=?";
		int rows = this.jdbcTemplate.update(updateQuery,

				new Object[] { pres.getResource_name(),
						pres.getResource_type(), pres.getResource_company(),pres.getResource_id()});

		return rows;
	}
	
	

}
