package dashboard.db.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dashboard.db.ASDataFormat;
import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.UserDao;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.User;


public class UserDaoImpl extends AbstractDao<User> implements UserDao {
	
	public UserDaoImpl() {
		super();
		
	}
	public UserDaoImpl(Session session) {
		super(session);
	}
	
	@Override
	public boolean existsUser() {
		try{
			this.openTransaction();
			
			Criteria cr = this.getSession().createCriteria(User.class);
			Number n=(Number) cr.setProjection(Projections.rowCount()).uniqueResult();//.add(Restrictions.eq("profile", User.ProfileAccess.Administration)).
			if (n.longValue()==0)
				return false;

		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}

		return true;
		
	}

	@Override
	public User getUser(String username, String password) {
		try{
			this.openTransaction();
			
			Criteria cr = this.getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("username", username));
			cr.add(Restrictions.eq("password",User.getPasswordEncrypt(password)));
			List results = cr.list();
			if (results.size() == 1)
			  return (User) results.get(0);

		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}

		return null;
		
	}

	@Override
	public Long addUser(User user) {
		Long iduser=null;
		try{
			this.openTransaction();
			user.setPasswordEncrypt();
			this.save(user);
			iduser=user.getId();
			System.out.println("Inserita user "+iduser);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return iduser;
	}

	@Override
	public boolean updateUser(User user) {
		boolean ok=false;
		try{
			User userDB=null;
			if (user.getId()!=null){
				userDB=this.getUserByID(user.getId());
			}
			this.openTransaction();
			if (userDB!=null){
				if (user.getPassword().equalsIgnoreCase("*********")){
					user.setPassword(userDB.getPassword());
				}else if (!user.getPassword().equalsIgnoreCase(userDB.getPassword()) ){
					user.setPasswordEncrypt();
				}
			}else{
				user.setPasswordEncrypt();
			}
			this.saveOrUpdate(user);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}

	@Override
	public List<User> getUser() {
		List<User> users=null;
		try{
			this.openTransaction();
			users=this.find(User.class,"id",false);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return users;
	}

	@Override
	public boolean deleteUser(Long iduser) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.delete(User.class,iduser);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}

	@Override
	public List<String> getUserByName(String name) {
		List<String> names=null;
		boolean ok=false;
		try{
			this.openTransaction();
			Criteria cr = this.getSession().createCriteria(User.class);
			cr=cr.setProjection(Projections.property("user"));
			if (name!=null && !name.equals(""))
			cr=cr.add(Restrictions.like("user", name.trim()+"%"));
			
			names=cr.list();
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return names;
	}

	@Override
	public User getUserByID(Long id) {
		User user=null;
		try{
			this.openTransaction();
			user=this.get(User.class, id);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return user;
	}
	@Override
	public List<User> getUser(String user) {
		List<User> users=null;
		try{
			this.openTransaction();
			Criteria cr = this.getSession().createCriteria(User.class);
			//cr=cr.setProjection(Projections.property("user"));
			if (user!=null && !user.equals(""))
			cr=cr.add(Restrictions.like("user", user.trim()+"%"));
			
			
			users= cr.list();
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return users;
	}
	

}
