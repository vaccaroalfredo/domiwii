package dashboard.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.filter.OncePerRequestFilter;

import dashboard.web.session.UserSessionInfo;



public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
	
		UserSessionInfo info= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		System.out.println("Session id:"+ request.getRequestedSessionId());
		 if ( info!=null){	
			 String origin = request.getHeader("origin");
	         origin = (origin == null || origin.equals("")) ? "null" : origin;
	         response.addHeader("Access-Control-Allow-Origin", origin);
	         response.addHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, DELETE, OPTIONS");
	         response.addHeader("Access-Control-Allow-Credentials", "true");
	         response.addHeader("Access-Control-Allow-Headers","Authorization, origin, content-type, accept, x-requested-with");
		 	chain.doFilter(request, response);
		 }else{
			//Filtro per sessione scaduta/accesso non autorizzato
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("Send redirect to "+request.getContextPath()+"/login");
		 }
		
	}
	
	
	

}
