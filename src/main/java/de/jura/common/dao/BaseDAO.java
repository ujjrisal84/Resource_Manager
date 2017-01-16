/**
 * 
 */
package de.jura.common.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;


import de.jura.res.data.PrimaryResource;

/**
 * @author Ujjwal
 *
 */
public abstract class BaseDAO { //yeutai conn use garna lai......
	
	protected JdbcTemplate jdbcTemplate;//utility method query run garna lai......
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

	
 
}
