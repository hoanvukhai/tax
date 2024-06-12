package tax.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import tax.model.MonthlyTax;

public class MonthlyTaxDAOImpl implements MonthlyTaxDAO {

	private JdbcTemplate jdbcTemplate;

	public MonthlyTaxDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveMonthlyTax(MonthlyTax monthlyTax) {
		String sql = "INSERT INTO Monthly_Tax (time, status, tax_id) VALUE ( ?, ?, ?)";
		return jdbcTemplate.update(sql, monthlyTax.getTime(), monthlyTax.getStatus(), monthlyTax.getTaxId()) > 0;
	}

	@Override
	public boolean updateMonthlyTax(MonthlyTax monthlyTax) {
		String sql = "UPDATE monthly_tax SET time=?, status=?, tax_id=? WHERE monthly_tax_id=?";
		return jdbcTemplate.update(sql, monthlyTax.getTime(), monthlyTax.getStatus(), monthlyTax.getTaxId(),
				monthlyTax.getMonthlyTaxId()) > 0;
	}

	@Override
	public MonthlyTax getOneMonthlyTax(Integer id) {
		String sql = "SELECT * FROM monthly_tax WHERE monthly_tax_id=" + id;
		ResultSetExtractor<MonthlyTax> extractor = new ResultSetExtractor<MonthlyTax>() {

			@Override
			public MonthlyTax extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer monthlyTaxId = rs.getInt("monthly_tax_id");
					String time = rs.getString("time");
					Boolean status = rs.getBoolean("status");
					Integer taxId = rs.getInt("tax_id");

					return new MonthlyTax(monthlyTaxId, time, status, taxId);
				}
				return null;
			}

		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public boolean deleteOneMonthlyTax(Integer id) {
		String sql = "DELETE from monthly_tax WHERE monthly_tax_id=" + id;
		return jdbcTemplate.update(sql) > 0;
	}

	@Override
	public List<MonthlyTax> listMonthlyTax() {
		String sql = "SELECT * FROM monthly_tax";

		RowMapper<MonthlyTax> rowMapper = new RowMapper<MonthlyTax>() {

			@Override
			public MonthlyTax mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer monthlyTaxId = rs.getInt("monthly_tax_id");
				String time = rs.getString("time");
				Boolean status = rs.getBoolean("status");
				Integer taxId = rs.getInt("tax_id");
				return new MonthlyTax(monthlyTaxId, time, status, taxId);
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<MonthlyTax> listMonthlyTax(Integer taxId) {
		String sql = "SELECT * FROM monthly_tax WHERE tax_id=" + taxId;

		RowMapper<MonthlyTax> rowMapper = new RowMapper<MonthlyTax>() {

			@Override
			public MonthlyTax mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer monthlyTaxId = rs.getInt("monthly_tax_id");
				String time = rs.getString("time");
				Boolean status = rs.getBoolean("status");
				Integer taxId = rs.getInt("tax_id");
				return new MonthlyTax(monthlyTaxId, time, status, taxId);
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

}
