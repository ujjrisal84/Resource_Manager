package de.jura.system.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.system.data.Field;

@Component("fieldDaoImpl")
public class FieldDaoImpl extends BaseDAO implements FieldDao {

	@Override
	public List<Field> getFieldSets() {
		// TODO Auto-generated method stub

		String sqlQuery = "select id, field_name from tbl_field where fieldset_id is null";

		RowMapper mapper = new RowMapper() {

			@Override
			public Field mapRow(ResultSet rs, int arg1) throws SQLException {
				Field field = new Field();
				field.setId(rs.getInt(1));
				field.setName(rs.getString(2));
				return field;

			}
		};

		List<Field> fieldList = (List<Field>) this.jdbcTemplate.query(sqlQuery,
				mapper);

		System.out.println("size of fieldset is:" + fieldList.size());
		return fieldList;
	}

	@Override
	public List<Field> getFields(int fieldSetId) {
		String sqlQuery = "select id, field_name, field_type from tbl_field where fieldset_id=?";

		RowMapper mapper = new RowMapper() {

			@Override
			public Field mapRow(ResultSet rs, int arg1) throws SQLException {
				Field field = new Field();
				field.setId(rs.getInt(1));
				field.setName(rs.getString(2));
				field.setType(rs.getString(3));
				return field;

			}
		};

		List<Field> fieldList = (List<Field>) this.jdbcTemplate.query(sqlQuery,
				new Object[] { fieldSetId }, mapper);

		return fieldList;
	}

	@Override
	public int addFieldset(Field field) {

		System.out.println("I am at daoaddfieldset");
		String sqlQuery = "insert into tbl_field (field_name,field_type) values(?,?)";

		int retVal = this.jdbcTemplate.update(sqlQuery,
				new Object[] { field.getName(), field.getType(), });

		System.out.println("The retval is:" + retVal);

		return retVal;

	}

	@Override
	public int addField(Field field) {

		String sqlString = "insert into tbl_field (field_name, field_type, fieldset_id)  values(?, ?, ? )";

		int retVal = this.jdbcTemplate.update(
				sqlString,
				new Object[] { field.getName(), field.getType(),
						field.getFieldSet_Id() });
		return retVal;
	}

	@Override
	public int editField(Field field) {

		System.out.println("I am at edit field method");
		String sqlString = "update tbl_field set field_name = ? where id = ?";

		int retVal = this.jdbcTemplate.update(sqlString,
				new Object[] { field.getName(), field.getId() });
		return retVal;

	}

	@Override
	public int deleteFieldSet(Field field) {

		String sqlString = "delete from tbl_field where id = ?";

		int retVal = this.jdbcTemplate.update(sqlString,
				new Object[] { field.getId() });
		return retVal;

	}

}
