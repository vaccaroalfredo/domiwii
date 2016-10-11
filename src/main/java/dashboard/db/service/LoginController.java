package dashboard.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dashboard.db.jpa.Device;
import dashboard.db.jpa.User;
import dashboard.db.service.DeviceService;
import dashboard.db.service.UserService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.session.UserSessionInfo;
import dashboard.web.view.LoginFormView;

/**
 * Controllers in Spring MVC 3.x are singleton scoped. 
 * There’s only one instance of the Controller shared by all the requests. So it’s bad idea to have member variables 
 * in a Controller class. 
 *
 */
@Controller //Per default as singleton scope.
@SessionAttributes("userSessionInfo") ///@Scope("request")
public class LoginController extends LoggerUtils{

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * String autowired instance automatically if it's present in context:component-scan
	 * @Autowired
	 * private UserSessionInfo userInfo;
	 * 
	 * If UserSessionInfo is on session ,you're injecting a session scoped bean into a singleton scope bean. 
	 * What Spring will do is inject a proxy bean, that, internally, will be able to generate a real UserSessionInfo object 
	 * per user and store it in the HttpSession.
	 *  @Autowired
	 * private UserSessionInfo userInfo; con @Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)on UserSessionInfo
	 * al momento questa soluzione non funziona bene ... non ho capito il perchè
	 */
	
	 
	
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView goToHome(HttpServletRequest request, HttpServletResponse response)
	{
		//Working with server: <%=application.getServerInfo()%><br>
		//Servlet Specification: <%=application.getMajorVersion()%>.<%=application.getMinorVersion()%><br>
		//JSP version: <%=JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion()%><br>
		//Java Version: <%=System.getProperty("java.version")%><br>
		
		//Prove di log4j
//		logger.debug("debug goToHome is executed!");
//		logger.info("info goToHome is executed!");
//		logger.warn("warn goToHome is executed!");
//		logger.fatal("fatal goToHome is executed!");
	
		this.debugMessage(logger,"Url match with function goToHome!!! ");
        return  new ModelAndView(new RedirectView("login",true));
	
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("loginBean")LoginFormView loginFormBean, SessionStatus status,WebRequest request)
	{
		UserService userService = (UserService) SpringApplicationContext.getServiceBean("userService");
		userService.addUser(new User(loginFormBean.getUsername(),loginFormBean.getPassword(),User.ProfileAccess.Administration));
		
		//DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");
		//deviceService.addDevice(new Device("provauiid"));
		
		this.debugMessage(logger,"Url match with function registerUser!!! ");
		ModelAndView model=new ModelAndView("login");//new RedirectView("login",true)
		LoginFormView loginBean = new LoginFormView();
		model.addObject("loginBean", loginBean);
		model.addObject("message","Utente registrato con successo");
		status.setComplete();
	    request.removeAttribute("userSessionInfo", WebRequest.SCOPE_SESSION);
        return  model;
	
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(WebRequest request, SessionStatus status,ModelAndView modelmessageregister)
	{	this.debugMessage(logger,"Url match with function displayLogin!!! ");
		UserSessionInfo info= (UserSessionInfo) request.getAttribute("userSessionInfo",WebRequest.SCOPE_SESSION);
		ModelAndView model =null;
		if ( info==null){	
			 model = new ModelAndView("login");
			status.setComplete();
		    request.removeAttribute("userSessionInfo", WebRequest.SCOPE_SESSION);
	        LoginFormView loginBean = new LoginFormView();
	        UserService userService = (UserService) SpringApplicationContext.getServiceBean("userService");
	        loginBean.setNoapplicationuser(!userService.existsUser());
	        model.addObject("loginBean", loginBean);
		}else{
			model = new ModelAndView(new RedirectView("home",true));
		}
//        if ( request.getParameter("message")!= null){
//        	 model.addObject("message", request.getParameter("message"));
//        }
        	
        
      
        
        return model;
	
	}
	
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginFormView loginFormBean)
	{		this.debugMessage(logger,"Url match with function executeLogin!!! ");
			ModelAndView model= null;
			
			User user=null;
//			GenericService userService = (GenericService) SpringApplicationContext.getServiceBean("genericService");
//		    userService.provaTransaction();
			UserService userService = (UserService) SpringApplicationContext.getServiceBean("userService");
		    user=userService.isValidUser(loginFormBean.getUsername(), loginFormBean.getPassword());
			if (user==null){
				model = new ModelAndView("login");
				model.addObject("message", "Credenziali non valide!");
				LoginFormView loginBean = new LoginFormView();
				loginBean.setNoapplicationuser(!userService.existsUser());
				model.addObject("loginBean", loginBean);
			}else{
				//userInfo.setUserData(user);
				model = new ModelAndView(new RedirectView("home",true));
				UserSessionInfo sessionInfo=new UserSessionInfo(user);
				model.addObject("userSessionInfo", sessionInfo);
			}
			
			return model;
			
	}
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public ModelAndView displayHome(HttpServletRequest request, HttpServletResponse response)
	{		this.debugMessage(logger,"displayHome!!! ");
			//ModelAndView model = new ModelAndView( new RedirectView("/home/activity",true));
			//model.addObject("userSessionInfo", loginFormBean);
			ModelAndView model = new ModelAndView("home");	
			return model;//new ModelAndView(new RedirectView("login"));
			//return model;
			
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logout(WebRequest request, SessionStatus status,ModelAndView modelmessageregister)
	{	this.debugMessage(logger,"Url match with function logout!!! ");
        
		status.setComplete();
	    request.removeAttribute("userSessionInfo", WebRequest.SCOPE_SESSION);
	    return new ModelAndView(new RedirectView("login",true));
	
	}
//	@ResponseBody
//    @RequestMapping("/")
//	 public String helloWorld()
//    {   System.out.println("HERE!!!");
//        return "Hello, World!";
//    }
}
