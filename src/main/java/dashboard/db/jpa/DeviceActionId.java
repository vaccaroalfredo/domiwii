package dashboard.db.jpa;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class DeviceActionId  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Action action;
	@ManyToOne
	private Device device;
	
	
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceActionId that = (DeviceActionId) o;

        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (device != null ? !device.equals(that.device) : that.device != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (action != null ? action.hashCode() : 0);
        result = 31 * result + (device != null ? device.hashCode() : 0);
        return result;
    }
	
	
	

}
