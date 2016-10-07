package dashboard.db.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import dashboard.db.dao.ConditionerDao;
import dashboard.db.dao.DeviceDao;
import dashboard.db.dao.UserDao;
import dashboard.db.daoimpl.ConditionerDaoImpl;
import dashboard.db.daoimpl.DeviceDaoImpl;
import dashboard.db.daoimpl.UserDaoImpl;
import dashboard.db.jpa.Conditioner;
import dashboard.db.jpa.Device;

@Service("conditionerService")
public class ConditionerService {
	private static final Logger logger = Logger.getLogger(ConditionerService.class);

	public ConditionerService() {
		super();
	}

	public List<Conditioner> getConditioners() {

		List<Conditioner> condList = null;
		ConditionerDao dao = new ConditionerDaoImpl();

		condList = dao.getAllConditioner();

		if (condList == null) {
			logger.error("getConditioner Conditioner  not found");
		}

		return condList;
	}

	public boolean addConditioner(Conditioner cond) {

		logger.debug("DEBUG--SERVICE--storeDevice");
		ConditionerDao dao = new ConditionerDaoImpl();
		try {
			dao.addConditioner(cond);
			return true;
		} catch (ConstraintViolationException e) {
			logger.error("storeDevice", e);
			return false;
		} catch (Exception e) {
			logger.error("storeDevice", e);
		}
		return false;

	}

	
}
