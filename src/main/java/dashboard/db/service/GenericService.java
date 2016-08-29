package dashboard.db.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import dashboard.db.HibernateUtil;
import dashboard.db.dao.ActivityDao;
import dashboard.db.dao.UserDao;
import dashboard.db.daoimpl.ActivityDaoImpl;
import dashboard.db.daoimpl.UserDaoImpl;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Activity.Status;
import dashboard.db.jpa.User;

@Service("genericService")
public class GenericService {
	/*private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
	public void provaTransaction(){
		Session session=null;
		Transaction transaction=null;
		try{
			session=sessionFactory.openSession();
		    transaction=session.beginTransaction();
			UserDao dao=new UserDaoImpl(session);
			
			User user=new User();
			user.setUsername("xxx1");
			user.setPassword("xxx2");
			dao.addUser(user);
			
			Activity activity=new Activity();
			activity.setCreationdate(new Date());
			activity.setStatus(Status.New);
			activity.setDescription("Inserimento da programma");
			ActivityDao daoa=new ActivityDaoImpl(session);
			daoa.addActivity(activity);
			
		}finally{
			transaction.commit();
			session.close();
		}
		
		
	}*/
	
}
