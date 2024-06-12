package tax.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import tax.model.Tax;

class TaxDAOImplTest {

	private DriverManagerDataSource dataSource;
	private TaxDAO dao;

	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/TaxManagement");
		dataSource.setUsername("root");
		dataSource.setPassword("123456789");

		dao = new TaxDAOImpl(dataSource);
	}

	@Test
	void testSaveTax() {
		Tax tax = new Tax(0, "Mien", "A company", "mien@gmail.com", "129 TK", "0123456789", 20000.0, 2000.0, 11);
		assertTrue(dao.saveTax(tax));
	}

	/*
	 * nhập dài (51 ký tự) ( -> không lưu
	 */
	@Test
	void testSaveTax2() {
		Tax tax = new Tax(0, "Miennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "A company", "mien@gmail.com",
				"129 TK", "0123456789", 20000.0, 2000.0, 11);
		assertTrue(!dao.saveTax(tax));
	}

	@Test
	void testEditTax() {
		Tax tax = new Tax(12, "Hoan", "ABC company", "hoan@gmail.com", "123 TK", "0123456789", 20000.0, 2000.0, 12);
		assertTrue(dao.editTax(tax));
	}
	
	/*
	 * nhập dài (51 ký tự) ( -> không lưu
	 */
	@Test
	void testEditTax2() {
		Tax tax = new Tax(0, "Miennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "ABC company", "hoan@gmail.com", "123 TK", "0123456789", 20000.0, 2000.0, 13);
		assertTrue(!dao.editTax(tax));
	}

	@Test
	void testListTax() {
		List<Tax> listTax = dao.listTax();
		System.out.println(listTax.size());

		for (Tax tax : listTax) {
			System.out.println(tax);
		}

		assertTrue(listTax.size() > 0);
	}

	@Test
	void testGetTax() {
		Tax t = dao.getTaxByTaxId(4);
		System.out.println(t);
		assertTrue(t != null);
	}

}
