package dashboard.db.service;



public interface BaseService {
		public void save(Object object);
		public Object find(Long id);
	    public void delete(Object object);
	    public void update(Object object);
}
