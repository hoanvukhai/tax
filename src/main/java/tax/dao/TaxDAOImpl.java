package tax.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import tax.model.Tax;
import tax.model.TaxRange;

public class TaxDAOImpl implements TaxDAO {

	private JdbcTemplate jdbcTemplate;

	public TaxDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveTax(Tax tax) {
		if (tax.getFullName().length() > 50 || tax.getAddress().length() > 100 || tax.getCompanyName().length() > 50
				|| tax.getEmail().length() > 50 || tax.getPhone().length() > 15) {
			return false;
		}
		double salary = tax.getSalary();
		double taxMoney = salary * getTaxPercentge(salary) / 100;

		String sql = "INSERT INTO Tax (full_name, company_name, email, address, phone, salary, tax, user_id) VALUE ( ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, tax.getFullName(), tax.getCompanyName(), tax.getEmail(), tax.getAddress(),
				tax.getPhone(), salary, taxMoney, tax.getUserId()) > 0;
	}

	@Override
	public boolean editTax(Tax tax) {
		if (tax.getFullName().length() > 50 || tax.getAddress().length() > 100 || tax.getCompanyName().length() > 50
				|| tax.getEmail().length() > 50 || tax.getPhone().length() > 15) {
			return false;
		}
		double salary = tax.getSalary();
		double taxMoney = salary * getTaxPercentge(salary) / 100;

		String sql = "UPDATE Tax SET full_name=?, company_name=?, email=?, address=?, phone=?, salary=?, tax=? WHERE tax_id=?";
		return jdbcTemplate.update(sql, tax.getFullName(), tax.getCompanyName(), tax.getEmail(), tax.getAddress(),
				tax.getPhone(), salary, taxMoney, tax.getTaxId()) > 0;
	}

	@Override
	public List<Tax> listTax() {
		String sql = "SELECT * FROM Tax";

		RowMapper<Tax> rowMapper = new RowMapper<Tax>() {

			@Override
			public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer taxId = rs.getInt("tax_id");
				String fullName = rs.getString("full_name");
				String companyName = rs.getString("company_name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				Double salary = rs.getDouble("salary");
				Double tax = rs.getDouble("tax");
				Integer userId = rs.getInt("user_id");

				return new Tax(taxId, fullName, companyName, email, address, phone, salary, tax, userId);
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Tax getTaxByUserId(int userId) {
		String sql = "SELECT * FROM Tax WHERE user_id=" + userId;
		ResultSetExtractor<Tax> extractor = new ResultSetExtractor<Tax>() {

			@Override
			public Tax extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer taxId = rs.getInt("tax_id");
					String fullName = rs.getString("full_name");
					String companyName = rs.getString("company_name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					Double salary = rs.getDouble("salary");
					Double tax = rs.getDouble("tax");
					Integer userId = rs.getInt("user_id");

					return new Tax(taxId, fullName, companyName, email, address, phone, salary, tax, userId);
				}
				return null;
			}

		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public Tax getTaxByTaxId(int taxId) {
		String sql = "SELECT * FROM Tax WHERE tax_id=" + taxId;
		ResultSetExtractor<Tax> extractor = new ResultSetExtractor<Tax>() {

			@Override
			public Tax extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer taxId = rs.getInt("tax_id");
					String fullName = rs.getString("full_name");
					String companyName = rs.getString("company_name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					Double salary = rs.getDouble("salary");
					Double tax = rs.getDouble("tax");
					Integer userId = rs.getInt("user_id");

					return new Tax(taxId, fullName, companyName, email, address, phone, salary, tax, userId);
				}
				return null;
			}

		};
		return jdbcTemplate.query(sql, extractor);
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
}
