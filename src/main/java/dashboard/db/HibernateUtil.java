package dashboard.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/*import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;*/
import org.hibernate.service.ServiceRegistry;

import dashboard.db.jpa.Action;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Conditioner;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.Note;
import dashboard.db.jpa.Scheduler;
import dashboard.db.jpa.Setting;
import dashboard.db.jpa.User;



public class HibernateUtil {
	 /***
     * Session factoty for local db
     */
	private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());
	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	static {
        
        try {
                // Create the SessionFactory from standard (hibernate.cfg.xml)// config file.
        		Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Activity.class);
                configuration.addAnnotatedClass(Setting.class);
                configuration.addAnnotatedClass(Note.class);
                configuration.addAnnotatedClass(Device.class);
                configuration.addAnnotatedClass(Action.class);
                configuration.addAnnotatedClass(Conditioner.class);
                
                configuration.addAnnotatedClass(Scheduler.class);
                ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();    
                sessionFactory = configuration.buildSessionFactory(sr);
                /*Session session=sessionFactory.openSession();
                FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
                fullTextSession.createIndexer().startAndWait();
                fullTextSession.close();*/
        } catch (Throwable ex) {
            // Log the exception.
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void releaseSessionFactory() {
        sessionFactory.close(); 
        sessionFactory=null;
    }

}
