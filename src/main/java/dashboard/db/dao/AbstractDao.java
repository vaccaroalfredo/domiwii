package dashboard.db.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import dashboard.db.HibernateUtil;

public class AbstractDao<T> {
	   
	    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    private Session session;
	    private Transaction transaction;
	    private boolean concludeoperation;
	    
	    
		public AbstractDao() {
			super();
			this.session=null;
			this.transaction=null;
			this.concludeoperation=true;
		}
		public AbstractDao(Session session) {
			super();
			this.reuseTransaction(session, session.getTransaction());
		}

		
		public void openTransaction(){
			if (this.session==null){
				this.openSession();
				this.setTransaction(this.getSession().beginTransaction());
			}
		}
		
		public void reuseTransaction(Session session,Transaction transaction){
			this.setSession(session);
			this.setTransaction(transaction);
			this.concludeoperation=false;
		}
		
		public void closeTransaction(boolean commit){
				if (this.getSession()!=null && this.getSession().isConnected()==true){
					if (this.concludeoperation==true){
						if (commit)
							this.getTransaction().commit();
						else
							this.getTransaction().rollback();
						
						this.closeSession();
					}
				}
		}
		
		public void saveOrUpdate(T entity) {
			
			this.getSession().saveOrUpdate(entity);
		}
	    
	    public void save(T entity) {
		       this.getSession().save(entity);
		}
	 
	    public void persist(T entity) {
	       this.getSession().persist(entity);
	    }
	 
	    public void delete(Class<T> entity, Serializable id) {
	        Object persistentInstance = this.getSession().load(entity, id);
	        if (persistentInstance != null) {
	            this.getSession().delete(persistentInstance);
	        }
	       
	    
	    }
	    
	    public List<T> find(Class<T> className,String orderColunm, Boolean asc ) {
	    	Criteria c = this.getSession().createCriteria(className);
	    	if (orderColunm!=null)
	    	c.addOrder(asc ?  Order.asc(orderColunm): Order.desc(orderColunm));
	    	return c.list();
	    }
	    
	    public  T get(Class<T> classType,Long identity) {
	    	return this.getSession().get(classType, identity);
	    }
	    
	    private void openSession(){
			
			this.setSession(this.getSessionFactory().openSession());
		}
		
		
		private void closeSession(){
			
			this.getSession().close();
			this.setSession(null);
			this.setTransaction(null);
		}
	    
	    public Session getSession() {
			return session;
		}

		public void setSession(Session session) {
			this.session = session;
		}
		
		public Transaction getTransaction() {
			return transaction;
		}

		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		public boolean isConcludeoperation() {
			return concludeoperation;
		}
		public void setConcludeoperation(boolean concludeoperation) {
			this.concludeoperation = concludeoperation;
		}

		
	    
	    
}
