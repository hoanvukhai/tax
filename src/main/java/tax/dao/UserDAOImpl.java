package tax.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import tax.model.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<User> list() {
		String sql = "SELECT * FROM User";

		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Boolean isAdmin = rs.getBoolean("is_admin");

				return new User(userId, username, password, isAdmin);
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public boolean register(User user) {

		List<User> userList = list();

		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equals(userList.get(i).getUsername()) || user.getPassword().length() < 6
					|| user.getPassword().length() > 50 || user.getUsername().length() < 6
					|| user.getUsername().length() > 50) {
				return false;
			}
		}

		String sql = "INSERT INTO User (username, password, is_admin) VALUE ( ?, ?, ?)";
		return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getIsAdmin()) > 0;
	}

	@Override
	public User login(User user) {
		List<User> userList = list();
		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equals(userList.get(i).getUsername())
					&& user.getPassword().equals(userList.get(i).getPassword())
					&& userList.get(i).getIsAdmin() == false) {
				return new User(userList.get(i).getUserId(), userList.get(i).getUsername(),
						userList.get(i).getPassword(), userList.get(i).getIsAdmin());
			}
		}
		return null;
	}

	@Override
	public Boolean loginForAdmin(User user) {
		List<User> userList = list();

		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equals(userList.get(i).getUsername())
					&& user.getPassword().equals(userList.get(i).getPassword())
					&& (userList.get(i).getIsAdmin() == true)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		if (user.getPassword().length() < 6 || user.getPassword().length() > 50 || user.getUsername().length() < 6
				|| user.getUsername().length() > 50) {
			return false;
		}

		String sql = "UPDATE user SET username=?, password=?, is_admin=? WHERE user_id=?";
		return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getIsAdmin(),
				user.getUserId()) > 0;
	}

	@Override
	public User getOneUser(Integer userId) {
		String sql = "SELECT * FROM user WHERE user_id=" + userId;
		ResultSetExtractor<User> extractor = new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Integer userId = rs.getInt("user_id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					Boolean isAdmin = rs.getBoolean("is_admin");

					return new User(userId, username, password, isAdmin);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public boolean deleteUser(Integer userId) {
		String sql = "DELETE from user WHERE user_id=" + userId;
		return jdbcTemplate.update(sql) > 0;
	}

}
