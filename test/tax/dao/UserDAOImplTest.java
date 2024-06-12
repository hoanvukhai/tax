package tax.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import tax.model.User;

class UserDAOImplTest {

	private DriverManagerDataSource dataSource;
	private UserDAO dao;

	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/TaxManagement");
		dataSource.setUsername("root");
		dataSource.setPassword("123456789");

		dao = new UserDAOImpl(dataSource);
	}

	@Test
	void testList() {
		List<User> listUsers = dao.list();

		for (User aUser : listUsers) {
			System.out.println(aUser);
		}

		assertTrue(!listUsers.isEmpty());
	}

	/*
	 * lưu tài khoản thành công
	 */
	@Test
	void testRegister() {
//		User user = new User(0, "hoanVK", "pro123", false);
		User user = new User(0, "hoanVK1", "pro123", true);
		assertTrue(dao.register(user));
	}

	/*
	 * nhập dài (51 ký tự) ( -> không lưu
	 */
	@Test
	void testRegister2() {
//		User user = new User(0, "hoanVK", "pro123", false);
		User user = new User(0, "Miennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "pro123", true);
		assertTrue(!dao.register(user));
	}

	/*
	 * nhập ngắn (5 ký tự) ( -> không lưu
	 */
	@Test
	void testRegister3() {
//		User user = new User(0, "hoanVK", "pro123", false);
		User user = new User(0, "Mien", "pro123", true);
		assertTrue(!dao.register(user));
	}

	@Test
	void testLogin() {
		User user = new User(0, "hoanVK", "pro123", false);
		assertTrue(dao.login(user) != null);
	}

	@Test
	void testLoginForAdmin() {
		User user = new User(0, "hoanVK1", "pro123", true);

		assertTrue(dao.loginForAdmin(user));
	}

	@Test
	void testDeleteUser() {
		assertTrue(dao.deleteUser(4));
	}

	/*
	 * cập nhập tài khoản thành công
	 */
	@Test
	void testUpdateUser() {
		User user = new User(12, "hoanVK1", "pro123", true);

		assertTrue(dao.updateUser(user));
	}

	/*
	 * nhập dài (51 ký tự) ( -> không lưu
	 */
	@Test
	void testUpdateUser2() {
		User user = new User(12, "Miennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "pro123", true);

		assertTrue(!dao.updateUser(user));
	}
	
	/*
	 * nhập ngắn (5 ký tự) ( -> không lưu
	 */
	@Test
	void testUpdateUser3() {
		User user = new User(12, "Mien", "pro123", true);

		assertTrue(!dao.updateUser(user));
	}
}
