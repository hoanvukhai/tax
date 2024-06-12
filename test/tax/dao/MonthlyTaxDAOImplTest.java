package tax.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import tax.model.MonthlyTax;

class MonthlyTaxDAOImplTest {

	private DriverManagerDataSource dataSource;
	private MonthlyTaxDAO dao;

	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/TaxManagement");
		dataSource.setUsername("root");
		dataSource.setPassword("123456789");

		dao = new MonthlyTaxDAOImpl(dataSource);
	}

	@Test
	void testSaveMonthlyTax() {

		MonthlyTax monthlyTax = new MonthlyTax("tháng 11", false, 2);
		assertTrue(dao.saveMonthlyTax(monthlyTax));
	}

	@Test
	void testUpdateMonthlyTax() {
		MonthlyTax monthlyTax = new MonthlyTax(5, "Tháng 9", false, 2);
		System.out.println(monthlyTax);
		assertTrue(dao.updateMonthlyTax(monthlyTax));
	}

	@Test
	void testGetMonthlyTax() {
		MonthlyTax m = dao.getOneMonthlyTax(5);
		System.out.println(m);
		assertTrue(m != null);
	}

	@Test
	void testListMonthlyTax() {
		List<MonthlyTax> listMonthlyTax = dao.listMonthlyTax();
		System.out.println(listMonthlyTax.size());

		for (MonthlyTax monthlyTax : listMonthlyTax) {
			System.out.println(monthlyTax);
		}

		assertTrue(listMonthlyTax.size() > 0);
	}

	@Test
	void testRemoveMonthlyTax() {
		assertTrue(dao.deleteOneMonthlyTax(1));
	}

}
