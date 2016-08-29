package dashboard.db.service;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import dashboard.db.dao.UserDao;
import dashboard.db.daoimpl.UserDaoImpl;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.User;




@Service("userService")
public class UserService {
	private static final Logger logger = Logger.getLogger(UserService.class);
	public static final int NoError=1;
	public static final int GenericError=0;
	public static final int SQLUserNameUniqueViolation=-1;
	
	public UserService() {
		super();
		
	}
	
	public Boolean existsUser(){
		UserDao dao = new UserDaoImpl();
		try{
			return dao.existsUser();
		}catch(Exception e){
			logger.error("existsUser",e);
		}
		return false;	
	}
	

	public User isValidUser(String username,String password){
		UserDao dao = new UserDaoImpl();
		try{
			return dao.getUser(username, password);
		}catch(Exception e){
			logger.error("isValidUser",e);
		}
		return null;	
	}
 
	public int storeUser(User user){
		logger.debug("DEBUG--SERVICE--storeUser");
		UserDao dao = new UserDaoImpl();
		try{
			 dao.updateUser(user); 
			 return UserService.NoError;
		}catch(ConstraintViolationException e){
			logger.error("storeUser",e);
			return UserService.SQLUserNameUniqueViolation;
		}catch(Exception e){
			logger.error("storeUser",e);
		}
		return UserService.GenericError;
	}
	
	public Long addUser(User user){
		logger.debug("DEBUG--SERVICE--addUser ");
		try{
			UserDao dao = new UserDaoImpl();
			return dao.addUser(user);
		}catch(Exception e){
			logger.error("addUser",e);
		}
		return new Long(0);
	}
	
	public boolean deleteUser(Long iduser){
		logger.debug("DEBUG--SERVICE--deleteUser ");
		UserDao dao = new UserDaoImpl();
		try{
			return dao.deleteUser(iduser);
		}catch(Exception e){
			logger.error("deleteUser",e);
		}
		return false;
	}
	
	
	public List<User> findUser(){
		logger.debug("DEBUG--SERVICE--findUser ");
		UserDao dao = new UserDaoImpl();
		try{
			return dao.getUser();
		}catch(Exception e){
			logger.error("findUser",e);
		}
		return new ArrayList<User>();
		
	}
	
	public List<User> findUser(String user){
		logger.debug("DEBUG--SERVICE--findUser by user ");
		UserDao dao = new UserDaoImpl();
		try{
			return dao.getUser(user);
		}catch(Exception e){
			logger.error("findUser by user",e);
		}
		return new ArrayList<User>();
		
	}
	
	public List<String> findUserInfo(String name){
		logger.debug("DEBUG--SERVICE--findUserInfo ");
		UserDao dao = new UserDaoImpl();
		try{
			return dao.getUserByName(name);
		}catch(Exception e){
			logger.error("findUserInfo",e);
		}
		return new ArrayList<String>();
	}
	
 
   
}
