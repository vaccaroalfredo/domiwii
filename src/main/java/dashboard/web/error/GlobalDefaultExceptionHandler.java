package dashboard.web.error;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import dashboard.web.controller.LoggerUtils;



//Controllo globale [prima di andare nel web.xml], al momento potrebbe essere tolto tanto qualsiasi errore è gestito dal web.xml
//da utilizzare solo se si vuole particolarizzare la pagina da mostrare a secondo dell'errore
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends LoggerUtils{

		private static final Logger logger = Logger.getLogger(GlobalDefaultExceptionHandler.class);
		
//		@ExceptionHandler(value = HttpSessionRequiredException.class)
//	    public ModelAndView goToLogin(){
//			ModelAndView model = new ModelAndView("login");
//			return model.addObject("message", "Sessione scaduta!");
//		}
//		
	    @ExceptionHandler(value = Exception.class)
	    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	        // If the exception is annotated with @ResponseStatus rethrow it and let
	        // the framework handle it - like the OrderNotFoundException example
	        // at the start of this post.
	        // AnnotationUtils is a Spring Framework utility class.
//	        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
//	            throw e;
//
//	        // Otherwise setup and send the user to a default error-view.
//	        ModelAndView mav = new ModelAndView();
//	        mav.addObject("exception", e);
//	        mav.addObject("url", req.getRequestURL());
//	        mav.setViewName("");
	    	logger.error("GlobalDefaultExceptionHandler:defaultErrorHandler",e);
	        return "error500";
	    }
}
