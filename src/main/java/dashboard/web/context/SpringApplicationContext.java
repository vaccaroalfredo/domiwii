package dashboard.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringApplicationContext implements ApplicationContextAware {

	  private static ApplicationContext CONTEXT_WEB;
	  private static ApplicationContext CONTEXT_DB;

	  
	 
	  /**
	   * This method is called from within the ApplicationContext once it is 
	   * done starting up, it will stick a reference to itself into this bean.
	   * @param context a reference to the ApplicationContext.
	   */
	  public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT_WEB = context;
	    System.out.println("WebApplicationContext ");
	    if (context!=null){
	    	System.out.println("Context found");	
	    	String[] beans=context.getBeanDefinitionNames();
	    	for(int i=0; i!=beans.length;i++)
	    	System.out.println("Beans "+beans[i]);	
	    	
	    }
	    CONTEXT_DB = new ClassPathXmlApplicationContext("applicationContext.xml");
	    System.out.println("DBApplicationContext ");
	    if (context!=null){
	    	System.out.println("Context found");	
	    	String[] beans=context.getBeanDefinitionNames();
	    	for(int i=0; i!=beans.length;i++)
	    	System.out.println("Beans "+beans[i]);	
	    	
	    }
	  }

	  /**
	   * This is about the same as context.getBean("beanName"), except it has its
	   * own static handle to the Spring context, so calling this method statically
	   * will give access to the beans by name in the Spring application context.
	   * As in the context.getBean("beanName") call, the caller must cast to the
	   * appropriate target class. If the bean does not exist, then a Runtime error
	   * will be thrown.
	   * @param beanName the name of the bean to get.
	   * @return an Object reference to the named bean.
	   */
	  public static Object getBean(String beanName) {
		  if (CONTEXT_WEB!=null){
		    	System.out.println("Web Context found");	
		    	String[] beans=CONTEXT_WEB.getBeanDefinitionNames();
		    	for(int i=0; i!=beans.length;i++){
		    		System.out.println("Beans "+beans[i]);	
		    	}
		    }   
		  
		  return CONTEXT_WEB.getBean(beanName);
		
	  }
	  
	  public static Object getServiceBean(String beanName) {
		  if (CONTEXT_DB!=null){
		    	System.out.println("DB Context found");	
		    	String[] beans=CONTEXT_DB.getBeanDefinitionNames();
		    	for(int i=0; i!=beans.length;i++){
		    		System.out.println("Beans "+beans[i]);	
		    	}
		    }   
		  
		  return CONTEXT_DB.getBean(beanName);
		
	  }

}
