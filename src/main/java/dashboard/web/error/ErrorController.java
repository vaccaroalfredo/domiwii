package dashboard.web.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ErrorController {
	@RequestMapping("404")
	public String handlePageNotFound(HttpServletRequest request) {
	    //this will return you the original URL for which this 404 happened     
	   String originalUri = (String) request
	                    .getAttribute("javax.servlet.error.request_uri");
	   System.out.println("handlePageNotFound"+originalUri);
	   return "error404";

	}
	
	@RequestMapping("500")
	public String handleInternalServerError(HttpServletRequest request) {
	    //this will return you the original URL for which this 404 happened     
	   String originalUri = (String) request
	                    .getAttribute("javax.servlet.error.request_uri");
	   System.out.println("handleInternalServerError"+originalUri);
	   return "error500";

	}
	
	
}
