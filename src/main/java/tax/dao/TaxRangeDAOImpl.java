package tax.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import tax.model.TaxRange;

public class TaxRangeDAOImpl implements TaxRangeDAO {

	private JdbcTemplate jdbcTemplate;

	public TaxRangeDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createTaxRange(TaxRange taxRange) {
		if (taxRange.getStartRange() < 0 || taxRange.getEndRange() < 0 || taxRange.getTaxPercentage() < 0
				|| taxRange.getTaxPercentage() > 100) {
			return false;
		}

		String sql = "INSERT INTO tax_range (start_range, end_range, tax_percentage) VALUE ( ?, ?, ?)";
		return jdbcTemplate.update(sql, taxRange.getStartRange(), taxRange.getEndRange(),
				taxRange.getTaxPercentage()) > 0;
	}

	@Override
	public double getTaxPercentge(Double salary) {
		List<TaxRange> taxListRange = getTaxRangeList();
		for (TaxRange taxRange : taxListRange) {
			if (salary > taxRange.getStartRange() && salary <= taxRange.getEndRange()) {
				return taxRange.getTaxPercentage();
			}
		}
		return 5;
	}

	@Override
	public TaxRange getOneTaxRange(Integer id) {
		String sql = "SELECT * FROM tax_range WHERE id=" + id;
		ResultSetExtractor<TaxRange> extractor = new ResultSetExtractor<TaxRange>() {

			@Override
			public TaxRange extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer id = rs.getInt("id");
					Double startRange = rs.getDouble("start_range");
					Double endRange = rs.getDouble("end_range");
					Double taxPercentage = rs.getDouble("tax_percentage");

					return new TaxRange(id, startRange, endRange, taxPercentage);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public List<TaxRange> getTaxRangeList() {
		String sql = "SELECT * FROM tax_range";

		RowMapper<TaxRange> rowMapper = new RowMapper<TaxRange>() {

			@Override
			public TaxRange mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				Double startRange = rs.getDouble("start_range");
				Double endRange = rs.getDouble("end_range");
				Double taxPercentage = rs.getDouble("tax_percentage");
				return new TaxRange(id, startRange, endRange, taxPercentage);
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public boolean removeTaxRange(Integer id) {
		String sql = "DELETE from tax_range WHERE id=" + id;
		return jdbcTemplate.update(sql) > 0;
	}

	@Override
	public boolean updateTaxRange(TaxRange taxRange) {
		if (taxRange.getStartRange() < 0 || taxRange.getEndRange() < 0 || taxRange.getTaxPercentage() < 0
				|| taxRange.getTaxPercentage() > 100) {
			return false;
		}

		String sql = "UPDATE tax_range SET start_range=?, end_range=?, tax_percentage=? WHERE id=?";
		return jdbcTemplate.update(sql, taxRange.getStartRange(), taxRange.getEndRange(), taxRange.getTaxPercentage(),
				taxRange.getId()) > 0;
	}

}
