package dashboard.web.controller;

import org.apache.log4j.Logger;

public class LoggerUtils {
	protected void debugMessage(Logger logger,String message){
		if ( logger.isDebugEnabled())
		logger.debug(message);
		
	}
	
	protected void errorMessage(Logger logger,String message,Exception e){
		
		logger.error(message,e);
		
	}
	
	protected void infoMessage(Logger logger,String message){
		if ( logger.isInfoEnabled())
		logger.info(message);
		
	}
}
