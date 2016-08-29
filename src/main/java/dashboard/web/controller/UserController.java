package dashboard.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dashboard.db.ASDataFormat;
import dashboard.db.jpa.User;
import dashboard.db.service.UserService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.view.ErrorFormView;
import dashboard.web.view.ActivityFilterView;
import dashboard.web.view.UserFilterView;

@Controller
@RequestMapping(value="/home/user")
public class UserController extends LoggerUtils{
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/save")
	public @ResponseBody  ErrorFormView save(@RequestBody User jsonString)
	{	this.debugMessage(logger,"Url match with function save!!! ");
		
		jsonString.logInfo();
		ErrorFormView error=new ErrorFormView();
		UserService activityService = (UserService) SpringApplicationContext.getServiceBean("userService");
		int status=activityService.storeUser(jsonString);
		if (  status != UserService.NoError ){
			if (status==UserService.SQLUserNameUniqueViolation){
				error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Lo username "+jsonString.getUsername()+" non è più utilizzabile!");
			}else{
			
				if (jsonString.getId()!=null)
					error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Modifica utente n. "+jsonString.getId()+" non riuscita!");
				else
					error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Inserimento utente "+jsonString.getUsername()+" non riuscita!");	
			}
		}	
		return error;
	
	}
	
	@RequestMapping(value="/add")
	public @ResponseBody  ErrorFormView add(@RequestBody User jsonString)
	{	this.debugMessage(logger,"Url match with function add!!! ");
		
		jsonString.logInfo();
		ErrorFormView error=new ErrorFormView();
		UserService activityService = (UserService) SpringApplicationContext.getServiceBean("userService");
		Long newidactivity=activityService.addUser(jsonString);
		if (newidactivity < 0)   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Aggiunta nuovo utente non riuscita!");
		else
			error.setStatus(newidactivity);
		
		return error;
	
	}
	
	@RequestMapping(value="/del")
	public @ResponseBody  ErrorFormView del(HttpServletRequest request)
	{	this.debugMessage(logger,"Url match with function delete!!! ");
		
		String idactivity=request.getParameter("id");
		System.out.println("UTENTE DA CANCELLARE : "+idactivity);
		ErrorFormView error=new ErrorFormView();
		UserService activityService = (UserService) SpringApplicationContext.getServiceBean("userService");
		
	
		if (activityService.deleteUser(Long.parseLong(idactivity))== false)   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Cancellazione utente non riuscita!");
		
		
		return error;
	
	}
	  
	@RequestMapping(value="/get",method = RequestMethod.POST)
	public @ResponseBody  List<User> get(@RequestBody UserFilterView filter)
	{	this.debugMessage(logger,"Url match with function get!!! ");
		
		
		UserService activityService = (UserService) SpringApplicationContext.getServiceBean("userService");
	
		return activityService.findUser(filter.getUser());
		
	
	}
	
	@RequestMapping(value="/getuser",method = RequestMethod.GET)
	public @ResponseBody  List<String> getUserInfo(@WebParam(name="term") String term)
	{	this.debugMessage(logger,"Url match with function get!!! ");
		
		UserService activityService = (UserService) SpringApplicationContext.getServiceBean("userService");
		return activityService.findUserInfo(term);
//		List<String> names=new ArrayList<>();
//		names.add("Maria");
//		names.add("Gennaro");
//		names.add("Salvatore");
//		names.add("Roberto");
		
		
	
	}
}
