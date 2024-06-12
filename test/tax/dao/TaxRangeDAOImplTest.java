package tax.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import tax.model.TaxRange;

class TaxRangeDAOImplTest {

	private DriverManagerDataSource dataSource;
	private TaxRangeDAO dao;

	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/TaxManagement");
		dataSource.setUsername("root");
		dataSource.setPassword("123456789");

		dao = new TaxRangeDAOImpl(dataSource);
	}

	@Test
	void testCreateTaxRange() {
		TaxRange tr = new TaxRange(0, 5000000.0, 10000000.0, 10.0);
		assertTrue(dao.createTaxRange(tr));
	}
	
	/*
	 * Nhập số âm -> mong muốn không tạo được dữ liệu vào cơ sở dữ liệu
	 */
	@Test
	void testCreateTaxRange1() {
		TaxRange tr = new TaxRange(0, -1.0, 10000000.0, 10.0);
		assertTrue(dao.createTaxRange(tr));
	}


	@Test
	void testGetTaxPercentge() {
		assertEquals(10, dao.getTaxPercentge((double) 7000000), 0.01);
	}

	@Test
	void testGetTaxRangeList() {
		List<TaxRange> taxRangeList = dao.getTaxRangeList();
		System.out.println(taxRangeList.size());

		for (TaxRange tr : taxRangeList) {
			System.out.println(tr);
		}

		assertTrue(taxRangeList.size() > 0);
	}

	@Test
	void testRemoveTaxRange() {
		assertTrue(dao.removeTaxRange(2));
	}

	@Test
	void testUpdateTaxRange() {
		TaxRange tr = new TaxRange(3, 4000000.0, 10000000.0, 10.0);
		assertTrue(dao.updateTaxRange(tr));
	}
	
	/*
	 * Nhập số âm -> mong muốn không sửa được dữ liệu vào cơ sở dữ liệu
	 */
	@Test
	void testUpdateTaxRange1() {
		TaxRange tr = new TaxRange(4, -1.0, 10000000.0, 10.0);
		assertTrue(!dao.updateTaxRange(tr));
	}

}
