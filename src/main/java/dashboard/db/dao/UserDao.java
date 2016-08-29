package dashboard.db.dao;

import java.util.List;

import dashboard.db.jpa.Activity;
import dashboard.db.jpa.User;

public interface UserDao {
	public Long addUser(User user);
    public User getUser(String username,String password);
    public List<String> getUserByName(String name);
	public boolean updateUser(User user);
    public List<User> getUser(); 
    public List<User> getUser(String user);
    public User getUserByID(Long id);
    public boolean deleteUser(Long iduser);
    public boolean  existsUser();
}
