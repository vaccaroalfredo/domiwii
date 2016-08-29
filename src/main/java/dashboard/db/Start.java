package dashboard.db;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dashboard.db.jpa.User;
import dashboard.db.service.UserService;

public class Start {

	public static void main(String[] args) {
		
		
		
//		Configuration configuration = new Configuration().configure();
		 
//        ServiceRegistry serviceRegistry
//            = new StandardServiceRegistryBuilder()
//                .applySettings(configuration.getProperties()).build();
//         
//        // builds a session factory from the service registry
//        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry); 
		// open/read the application context file
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	 
	    // instantiate our spring dao object from the application context
	    
	    UserService fileEventDao = (UserService)ctx.getBean("userService");
	    
	    ctx.getBean("Device");
	   
	    //fileEventDao.find();

	}
	
	public static void db (){
		StandardServiceRegistry standardRegistry = new     StandardServiceRegistryBuilder()
			    .configure( "org/hibernate/example/MyCfg.xml" )
			    .build();

			Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( User.class )
			    .addAnnotatedClassName( "org.hibernate.example.Customer" )
			    .addResource( "org/hibernate/example/Order.hbm.xml" )
			    .addResource( "org/hibernate/example/Product.orm.xml" )
			    .getMetadataBuilder()
			    .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
			    .build();

			SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .applyBeanManager(null )
			    .build();
		
	}
	

}
