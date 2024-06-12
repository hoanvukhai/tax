package tax.dao;

import java.util.List;

import tax.model.User;

public interface UserDAO {
	public boolean register(User user);

	public User login(User user);

	public List<User> list();
	
	public Boolean loginForAdmin(User user);

	public boolean updateUser(User user);
	
	public User getOneUser(Integer userId);
	
	public boolean deleteUser(Integer userId);

}
