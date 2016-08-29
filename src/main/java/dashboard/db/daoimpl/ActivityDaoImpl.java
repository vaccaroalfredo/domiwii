package dashboard.db.daoimpl;

import java.util.Date;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/*import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;*/

import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.ActivityDao;
import dashboard.db.jpa.Activity;

public class ActivityDaoImpl extends AbstractDao<Activity> implements ActivityDao{
	
	
	

	public ActivityDaoImpl() {
		super();
	}
	
	public ActivityDaoImpl(Session session) {
		super(session);
	}
	

	@Override
	public boolean updateActivity(Activity activity) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.saveOrUpdate(activity);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}
	
	@Override
	public Long addActivity(Activity activity) {
		Long idactivity=null;
		try{
			this.openTransaction();
			this.persist(activity);
			idactivity=activity.getId();
			System.out.println("Inserita attivita "+idactivity);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return idactivity;
	}

	@Override
	public List<Activity> getActivities() {
		List<Activity> activity=null;
		try{
			this.openTransaction();
			activity=this.find(Activity.class,"id",false);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return activity;
	}

	@Override
	public boolean deleteActivity(Long idactivity) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.delete(Activity.class,idactivity);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	} 

	@Override
	public List<Activity> getActivitiesNotClosed() {
		List<Activity> activity=null;
		try{
			this.openTransaction();
			Criteria c=this.getSession().createCriteria(Activity.class);
			activity=c.add(Restrictions.not(Restrictions.eq("status", Activity.Status.Closed)))
					  //.add()
					  .addOrder(Order.asc("startdate"))
					  .list();
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return activity;
	}
	
	/*@Override
	public List<Activity> getActivities(String description, Activity.Status status, Date startdate, Date enddate) {
		List<Activity> activity=null;
		Session session=null;
		try{
			session=this.getSessionFactory().openSession();
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			Transaction tx = fullTextSession.beginTransaction();

			// create native Lucene query unsing the query DSL
			// alternatively you can write the Lucene query using the Lucene query parser
			// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
			QueryBuilder qb = (QueryBuilder) fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( Activity.class ).get();
			org.apache.lucene.search.Query query = (org.apache.lucene.search.Query) qb.keyword().onFields("description").matching(description);

			// wrap Lucene query in a org.hibernate.Query
			org.hibernate.Query hibQuery = 
			    fullTextSession.createFullTextQuery(query, Activity.class);

			// execute search
			List result = hibQuery.list();
			  
			tx.commit();
			session.close();
		}catch(Exception e){
			session.close();
		}finally{
			session.close();
		}
		return activity;
	}*/

	@Override
	public List<Activity> getActivities(String description, Activity.Status status, Date startdate, Date enddate) {
		List<Activity> activity=null;
		try{
			this.openTransaction();
			Criteria c=this.getSession().createCriteria(Activity.class);
			if (startdate!=null && enddate!=null){
				if (startdate.before(enddate)){
					c=c.add(Restrictions.between("startdate", startdate,enddate));
				}
			}else if (startdate==null){
				c=c.add(Restrictions.le("startdate", enddate));
			}else{
				c=c.add(Restrictions.ge("startdate", startdate));
			
			}
			if (status!=null){
				c=c.add(Restrictions.eq("status", status));
			}
			if (description!=null && description.trim().length()!=0)
				c=c.add(Restrictions.like("description","%"+description.trim()+"%"));
			
			c=c.addOrder(Order.desc("startdate"));
			activity=c.list();
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return activity;
	}

	
	
}
