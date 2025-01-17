package tax.model;

public class User {
	private Integer userId;
	private String username;
	private String password;
	private Boolean isAdmin;

	public User(Integer userId, String username, String password, Boolean isAdmin) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public User(Integer userId) {
		this.userId = userId;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin
				+ "]";
	}

}
